/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.controllers.ContactController;
import ge.rest.example.rest.project.domain.Contact;
import ge.rest.example.rest.project.mapper.ContactMapper;
import ge.rest.example.rest.project.model.ContactReq;
import ge.rest.example.rest.project.repositories.ContactRepository;
import java.util.List;
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

    public ContactServiceImpl(ContactMapper contactMapper, ContactRepository contactRepository) {
        this.contactMapper = contactMapper;
        this.contactRepository = contactRepository;
    }
      @Override
   public     List<ContactReq> getAllContact() {
        return contactRepository
                .findAll()
                .stream()
                .map(contact -> {
                   ContactReq contactReq = contactMapper.contactToContactres(contact);
                   contactReq.setContactUrl(getContactUrl(contact.getId()));
                   return contactReq;
                })
                .collect(Collectors.toList());
    }
    @Override
    public ContactReq getContactsById(Long id) {

        return contactRepository.findById(id)
                .map(contactMapper::contactToContactres)
                .map(contactReq -> {
                    contactReq.setContactUrl(getContactUrl(id));
                    return contactReq;
                })
                .orElseThrow(RuntimeException::new);
        
    }
    
      @Override
    public ContactReq createNewContact(ContactReq contactReq) {

        return saveAndReturnDTO(contactMapper.contactDtoTcontact(contactReq));
    }
    
    private ContactReq saveAndReturnDTO(Contact contact) {
        Contact savedReq = contactRepository.save(contact);

        ContactReq returnDto = contactMapper.contactToContactres(savedReq);

        returnDto.setContactUrl(getContactUrl(savedReq.getId()));
        return returnDto;
    }
    
       @Override
    public ContactReq saveContactByDTO(Long id, ContactReq contactReq) {
        Contact contact = contactMapper.contactDtoTcontact(contactReq);
        contact.setId(id);
        return saveAndReturnDTO(contact);
    }
    
     private String getContactUrl(Long id) {
        return ContactController.BASE_URL + "/" + id;
    }
       @Override
    public void deleteContactById(Long id) {
        contactRepository.deleteById(id);
    }
}
