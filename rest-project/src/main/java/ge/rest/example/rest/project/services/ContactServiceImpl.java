/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.domain.Contact;
import ge.rest.example.rest.project.domain.Student;
import ge.rest.example.rest.project.mapper.ContactMapper;
import ge.rest.example.rest.project.mapper.StudentMapper;
import ge.rest.example.rest.project.model.ContactReq;
import ge.rest.example.rest.project.model.ContactResponseDTO;
import ge.rest.example.rest.project.model.ContactUpdateReq;
import ge.rest.example.rest.project.model.StudentDTO;
import ge.rest.example.rest.project.repositories.ContactRepository;
import ge.rest.example.rest.project.repositories.StudentRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vako
 */
@Service
public class ContactServiceImpl implements ContactService {

    private final StudentMapper studentMapper;
    private final ContactMapper contactMapper;
    private final ContactRepository contactRepository;
    private final StudentRepository studentRepository;

    public ContactServiceImpl(StudentMapper studentMapper, ContactMapper contactMapper, ContactRepository contactRepository,
            StudentRepository studentRepository) {
        this.studentMapper = studentMapper;
        this.contactMapper = contactMapper;
        this.contactRepository = contactRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<ContactResponseDTO> getAllContact() {
        return contactRepository
                .findAll()
                .stream()
                .map(contact -> {
                    ContactResponseDTO contactResponseDTO = contactMapper.contactToDto(contact);
                    contactResponseDTO.setContactId(contact.getId());
                    contactResponseDTO.setStudentId(contact.getStudent().getId());
                    return contactResponseDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ContactResponseDTO getContactsById(Long id) {
        Contact contact1 = contactRepository.findById(id).get();
        Student student1 = studentRepository.findById(contact1.getStudent().getId()).get();
        StudentDTO studentDTO = studentMapper.studentToDto(student1);
        ContactResponseDTO contactResponse = contactMapper.contactToDto(contact1);
        contactResponse.setStudentId(contact1.getStudent().getId());
        contactResponse.setContactId(contact1.getId());
        return contactResponse;

    }

    @Override
    @Transactional
    public ContactResponseDTO createNewContact(ContactReq contactReq) {
         Optional<Contact> contactOpt = contactRepository.findByContactvalue(contactReq.getContactvalue());
         if(contactOpt.isPresent()) {
           throw new RuntimeException ("contactvalue already exists");   
         } else {
        Student student = studentRepository.findById(contactReq.getStudentId()).get();
        Contact contact = contactMapper.contactDtoTcontact(contactReq);
        contact.setStudent(student);

        contact = contactRepository.save(contact);

        StudentDTO studentDTO = studentMapper.studentToDto(student);
        ContactResponseDTO contact1 = contactMapper.contactToDto(contact);
        contact1.setStudentId(student.getId());
        contact1.setContactId(contact.getId());

        return contact1;
         }
    }

    @Override
    @Transactional
    public ContactResponseDTO updateContactByDTO(Long id, ContactUpdateReq contactUpdateReq) {
        Contact contact = contactRepository.findById(id).get();
        Optional<Student> student1 = studentRepository.findById(contact.getStudent().getId());
        Student student = student1.get();
        contact.setContacttype(contactUpdateReq.getContacttype());
        contact.setContactvalue(contactUpdateReq.getContactvalue());
        contact.setStudent(student);
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setAdress(student.getAdress());
        studentDTO.setFirstname(student.getFirstname());
        studentDTO.setLastname(student.getLastname());
        studentDTO.setIdnumber(student.getIdnumber());
        ContactResponseDTO contactResponse = new ContactResponseDTO();
        contactResponse.setContacttype(contact.getContacttype());
        contactResponse.setContactvalue(contact.getContactvalue());
        contactResponse.setStudent(studentDTO);
        contactResponse.setStudentId(student.getId());
        contactResponse.setContactId(contact.getId());
        return contactResponse;

    }

    @Override
    @Transactional
    public void deleteContactById(Long id) {
        contactRepository.deleteById(id);
    }
}
