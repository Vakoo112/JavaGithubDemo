/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ge.rest.example.rest.project.mapper;

import ge.rest.example.rest.project.domain.Student;
import ge.rest.example.rest.project.model.StudentDTO;
import ge.rest.example.rest.project.model.StudentResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author vako
 */
@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
    
    StudentDTO studentToDto(Student student);
  
    Student studentDtoTostudent(StudentDTO studentDTO);

    StudentResponseDTO studentToREsponse(Student student);

}
