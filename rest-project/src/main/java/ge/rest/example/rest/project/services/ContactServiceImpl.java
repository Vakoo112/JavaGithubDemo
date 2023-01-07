/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.controllers.ContactController;
import ge.rest.example.rest.project.domain.Contact;
import ge.rest.example.rest.project.domain.Student;
import ge.rest.example.rest.project.mapper.ContactMapper;
import ge.rest.example.rest.project.model.ContactReq;
import ge.rest.example.rest.project.model.ContactResponseDTO;
import ge.rest.example.rest.project.model.StudentDTO;
import ge.rest.example.rest.project.repositories.ContactRepository;
import ge.rest.example.rest.project.repositories.StudentRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author vako
 */
@Service
public class ContactServiceImpl implements ContactService {
    private final ContactMapper contactMapper;
    private final ContactRepository contactRepository;
    private final StudentRepository studentRepository;

    public ContactServiceImpl(ContactMapper contactMapper, ContactRepository contactRepository,
    StudentRepository  studentRepository ) {
        this.contactMapper = contactMapper;
        this.contactRepository = contactRepository;
        this.studentRepository = studentRepository;
    }
      @Override
   public     List<ContactResponseDTO> getAllContact() {
        return contactRepository
                .findAll()
                .stream()
                .map(contact -> {
                   ContactResponseDTO contactResponseDTO = contactMapper.contactToDto(contact);
                   return contactResponseDTO;
                })
                .collect(Collectors.toList());
    }
    @Override
    public ContactResponseDTO  getContactsById(Long id) {
      Optional<Contact> contactOPT = contactRepository.findById(id);
      Contact contact1 = contactOPT.get();
        Optional<Student> student = studentRepository.findById(contact1.getId());
        Student student1 = student.get();
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setAdress(student1.getAdress());
        studentDTO.setFirstname(student1.getFirstname());
        studentDTO.setLastname(student1.getLastname());
        studentDTO.setIdnumber(student1.getIdnumber());
        studentDTO.setContactId(student1.getId());
        ContactResponseDTO contactResponse = new ContactResponseDTO();
        contactResponse.setContacttype(contact1.getContacttype());
        contactResponse.setContactvalue(contact1.getContactvalue());
        contactResponse.setStudent(studentDTO);
        return contactResponse;
     
    }
    
      @Override
    public ContactResponseDTO createNewContact(ContactReq contactReq) {
         Student student = studentRepository.findById(contactReq.getStudentId()).get();
         Contact contact = new Contact();
         contact.setContacttype(contactReq.getContacttype());
         contact.setContactvalue(contactReq.getContactvalue());
         contact.setStudent(student);
         
         contact = contactRepository.save(contact);
         StudentDTO studentDTO = new StudentDTO();
         studentDTO.setAdress(student.getAdress());
         studentDTO.setContactId(student.getId());
         studentDTO.setFirstname(student.getFirstname());
         studentDTO.setLastname(student.getLastname());
         studentDTO.setIdnumber(student.getIdnumber());
         ContactResponseDTO contact1 = new ContactResponseDTO();
         contact1.setContacttype(contact.getContacttype());
         contact1.setContactvalue(contact.getContactvalue());
         contact1.setStudent(studentDTO);
         return contact1;
    }
    
    
      @Override
    public  ContactResponseDTO updateContactByDTO(Long id, ContactReq contactReq) {
       Student student = studentRepository.findById(contactReq.getStudentId()).get();
       Optional<Contact> contactOPT = contactRepository.findById(id);
       Contact contact = contactOPT.get();
       contact.setContacttype(contactReq.getContacttype());
       contact.setContactvalue(contactReq.getContactvalue());
       contact.setStudent(student);
       contact = contactRepository.save(contact);
       StudentDTO studentDTO = new StudentDTO();
       studentDTO.setAdress(student.getAdress());
       studentDTO.setFirstname(student.getFirstname());
       studentDTO.setLastname(student.getLastname());
       studentDTO.setIdnumber(student.getIdnumber());
       studentDTO.setContactId(student.getId());
       ContactResponseDTO contactResponse = new ContactResponseDTO();
       contactResponse.setContacttype(contact.getContacttype());
       contactResponse.setContactvalue(contact.getContactvalue());
       contactResponse.setStudent(studentDTO);
       return contactResponse;
       
   }
    
     private String getContactUrl(Long id) {
        return ContactController.BASE_URL + "/" + id;
    }
       @Override
    public void deleteContactById(Long id) {
        contactRepository.deleteById(id);
    }
}
