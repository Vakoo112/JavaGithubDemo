/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ge.rest.example.rest.project.mapper;

import ge.rest.example.rest.project.domain.Contact;
import ge.rest.example.rest.project.model.ContactDTO;
import ge.rest.example.rest.project.model.ContactReq;
import ge.rest.example.rest.project.model.ContactResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author vako
 */
@Mapper
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    ContactDTO contactToContactDTO(Contact contact);

    Contact contactDtoTcontact(ContactReq contactReq);

    ContactReq contactToContactres(Contact contact);

    ContactResponseDTO contactToDto(Contact contact);

}
