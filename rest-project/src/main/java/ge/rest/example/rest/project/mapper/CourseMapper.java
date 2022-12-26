/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ge.rest.example.rest.project.mapper;

import ge.rest.example.rest.project.domain.Course;
import ge.rest.example.rest.project.domain.Returntype;
import ge.rest.example.rest.project.domain.Team;
import ge.rest.example.rest.project.model.CourseDTO;
import ge.rest.example.rest.project.model.ReturnTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author vako
 */
@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    CourseDTO courseToCourseDTO(Course course);

    Course courseDtoTocourse(CourseDTO courseDTO);

    ReturnTypeDTO teamToDTO(Team team);

    ReturnTypeDTO courseTOdto(Course course);

    ReturnTypeDTO alltoall(Team team, Course course);

    Returntype dtoToType(ReturnTypeDTO returnTypeDTO);

    ReturnTypeDTO change1(Returntype returnType);

}
