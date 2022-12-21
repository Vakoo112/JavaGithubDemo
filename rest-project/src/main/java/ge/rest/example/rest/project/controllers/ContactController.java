/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.controllers;

import ge.rest.example.rest.project.model.ContactDTO;
import ge.rest.example.rest.project.model.ContactListDTO;
import ge.rest.example.rest.project.services.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(description="This is my Contact Controller")
@RestController
@RequestMapping(ContactController.BASE_URL)
public class ContactController {
    public static final String BASE_URL = "/api/v1/contacts";
     private final ContactService contactService;      

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }
      @ApiOperation(value="Get all contacts", notes = "contact shesaxeb")
     @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ContactListDTO getAllContact(){
        
        return new ContactListDTO
                 (contactService.getAllContact());
    }
     @ApiOperation(value="Get contact by id ", notes = "contact id shesaxeb")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ContactDTO getContactById(@PathVariable Long id){
        return contactService.getContactsById(id);
    }
    
     @ApiOperation(value="This will create a new team", notes = "Api creates shesaxeb")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO createNewContact(@RequestBody ContactDTO contactDTO){
        return contactService.createNewContact(contactDTO);
    }
     @ApiOperation(value="Update contact", notes = " update shesaxeb")
     @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ContactDTO updateContact(@PathVariable Long id, @RequestBody ContactDTO contactDTO){

        return contactService.saveContactByDTO(id, contactDTO);
    }
     @ApiOperation(value="Delte contact", notes = "delete shesaxeb")
        @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteTeam(@PathVariable Long id){
        contactService.deleteContactById(id);
    }
}
