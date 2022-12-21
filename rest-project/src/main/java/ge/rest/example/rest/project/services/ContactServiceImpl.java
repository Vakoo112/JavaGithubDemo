/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.domain.Contact;
import ge.rest.example.rest.project.mapper.ContactMapper;
import ge.rest.example.rest.project.model.ContactDTO;
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
   public     List<ContactDTO> getAllContact() {
        return contactRepository
                .findAll()
                .stream()
                .map(contact -> {
                   ContactDTO contactDTO = contactMapper.contactToContactDTO(contact);
                   return contactDTO;
                })
                .collect(Collectors.toList());
    }
    @Override
    public ContactDTO getContactsById(Long id) {

        return contactRepository.findById(id)
                .map(contactMapper::contactToContactDTO)
                .map(contactDTO -> {
                   
                    return contactDTO;
                })
                .orElseThrow(RuntimeException::new);
        
    }
    
      @Override
    public ContactDTO createNewContact(ContactDTO contactDTO) {

        return saveAndReturnDTO(contactMapper.contactDtoTcontact(contactDTO));
    }
    
    private ContactDTO saveAndReturnDTO(Contact contact) {
        Contact savedContact = contactRepository.save(contact);

        ContactDTO returnDto = contactMapper.contactToContactDTO(savedContact);


        return returnDto;
    }
    
       @Override
    public ContactDTO saveContactByDTO(Long id, ContactDTO contactDTO) {
        Contact contact = contactMapper.contactDtoTcontact(contactDTO);
        contact.setId(id);
        return saveAndReturnDTO(contact);
    }
       @Override
    public void deleteContactById(Long id) {
        contactRepository.deleteById(id);
    }
}
