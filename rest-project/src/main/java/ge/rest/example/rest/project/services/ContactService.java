/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.model.ContactDTO;
import ge.rest.example.rest.project.model.ContactReq;
import ge.rest.example.rest.project.model.ContactResponseDTO;
import java.util.List;

/**
 *
 * @author vako
 */
public interface ContactService {

    List<ContactResponseDTO> getAllContact();

    ContactResponseDTO getContactsById(Long id);
    
    ContactResponseDTO createNewContact(ContactReq contactReq);

    ContactResponseDTO updateContactByDTO(Long id, ContactReq contactReq);

    void deleteContactById(Long id);
}
