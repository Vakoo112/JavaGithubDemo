/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.controllers;

import ge.rest.example.rest.project.model.ContactListDTO;
import ge.rest.example.rest.project.model.ContactReq;
import ge.rest.example.rest.project.model.ContactResponseDTO;
import ge.rest.example.rest.project.model.ContactUpdateReq;
import ge.rest.example.rest.project.services.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vako
 */
@Tag(name="This is my Contact Controller")
@RestController
@RequestMapping(ContactController.BASE_URL)
public class ContactController {
    public static final String BASE_URL = "/api/v1/contacts";
     private final ContactService contactService;      

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }
     @Operation(summary = "Get Contact ", description = "Contact  shesaxeb ")
     @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ContactListDTO getAllContact(){
        
        return new ContactListDTO
                 (contactService.getAllContact());
    }
    @Operation(summary = "Get Contact by id ", description = "Contact id  shesaxeb ")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ContactResponseDTO getContactById(@PathVariable Long id){
        return contactService.getContactsById(id);
    }
    
  @Operation(summary = "Create Contact  ", description = "Contact sheqmnis  shesaxeb ")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactResponseDTO createNewContact(@Valid @RequestBody ContactReq contactReq){
        return contactService.createNewContact(contactReq);
    }
  @Operation(summary = "update Contact ", description = "Contact update  shesaxeb ")
     @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ContactResponseDTO updateContact(@PathVariable Long id,@Valid @RequestBody ContactUpdateReq contactUpdateReq){

        return contactService.updateContactByDTO(id, contactUpdateReq);
    }
     @Operation(summary = "Delete Contact by id ", description = "Contact delete  shesaxeb ")
        @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteTeam(@PathVariable Long id){
        contactService.deleteContactById(id);
    }
}
