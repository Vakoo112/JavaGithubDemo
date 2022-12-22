/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ge.rest.example.rest.project.services;

import ge.rest.example.rest.project.model.ContactDTO;
import ge.rest.example.rest.project.model.ContactReq;
import java.util.List;

/**
 *
 * @author vako
 */
public interface ContactService {

    List<ContactReq> getAllContact();

    ContactReq getContactsById(Long id);
    
    ContactReq createNewContact(ContactReq contactReq);

    ContactReq saveContactByDTO(Long id, ContactReq contactReq);

    void deleteContactById(Long id);
}
