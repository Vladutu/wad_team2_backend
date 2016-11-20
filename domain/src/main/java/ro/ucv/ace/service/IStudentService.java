package ro.ucv.ace.service;

import ro.ucv.ace.dto.student.ESStudentDto;
import ro.ucv.ace.dto.student.StudentDto;

import java.util.List;

/**
 * Created by tzapt on 11/20/2016.
 */
public interface IStudentService {

    StudentDto save(ESStudentDto studentDto);

    List<StudentDto> getAll();

    StudentDto delete(int id);

    StudentDto edit(int id, ESStudentDto studentDto);
}
