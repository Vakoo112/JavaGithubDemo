/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.services;

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
import org.springframework.transaction.annotation.Transactional;

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
            StudentRepository studentRepository) {
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
        Optional<Contact> contactOPT = contactRepository.findById(id);
        Contact contact1 = contactOPT.get();
        Optional<Student> student = studentRepository.findById(contact1.getStudent().getId());
        Student student1 = student.get();
        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setAdress(student1.getAdress());
        studentDTO.setFirstname(student1.getFirstname());
        studentDTO.setLastname(student1.getLastname());
        studentDTO.setIdnumber(student1.getIdnumber());
        ContactResponseDTO contactResponse = new ContactResponseDTO();
        contactResponse.setContacttype(contact1.getContacttype());
        contactResponse.setContactvalue(contact1.getContactvalue());
        contactResponse.setStudent(studentDTO);
        contactResponse.setContactId(contact1.getId());
        return contactResponse;

    }

    @Override
    @Transactional
    public ContactResponseDTO createNewContact(ContactReq contactReq) {

        Student student = studentRepository.findById(contactReq.getStudentId()).get();
        Contact contact = new Contact();
        contact.setContacttype(contactReq.getContacttype());
        contact.setContactvalue(contactReq.getContactvalue());
        contact.setStudent(student);

        contact = contactRepository.save(contact);

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setAdress(student.getAdress());
        studentDTO.setFirstname(student.getFirstname());
        studentDTO.setLastname(student.getLastname());
        studentDTO.setIdnumber(student.getIdnumber());
        ContactResponseDTO contact1 = new ContactResponseDTO();
        contact1.setContacttype(contact.getContacttype());
        contact1.setContactvalue(contact.getContactvalue());
        contact1.setStudent(studentDTO);
        contact1.setStudentId(student.getId());
        contact1.setContactId(contact.getId());

        return contact1;

    }

    @Override
    @Transactional
    public ContactResponseDTO updateContactByDTO(Long id, ContactReq contactReq) {
        Student student = studentRepository.findById(contactReq.getStudentId()).get();
        Optional<Contact> contactOPT = contactRepository.findById(id);
        Contact contact = contactOPT.get();
        contact.setContacttype(contactReq.getContacttype());
        contact.setContactvalue(contactReq.getContactvalue());
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
