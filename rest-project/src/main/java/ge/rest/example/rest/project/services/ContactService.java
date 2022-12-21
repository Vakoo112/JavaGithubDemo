/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.model.ContactDTO;
import java.util.List;

/**
 *
 * @author vako
 */
public interface ContactService {

    List<ContactDTO> getAllContact();

    ContactDTO getContactsById(Long id);
    
    ContactDTO createNewContact(ContactDTO contactDTO);

    ContactDTO saveContactByDTO(Long id, ContactDTO contactDTO);

    void deleteContactById(Long id);
}
