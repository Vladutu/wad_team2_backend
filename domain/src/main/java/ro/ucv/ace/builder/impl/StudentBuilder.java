package ro.ucv.ace.builder.impl;

import org.springframework.stereotype.Component;
import ro.ucv.ace.builder.IStudentBuilder;
import ro.ucv.ace.dto.student.ESStudentDto;
import ro.ucv.ace.model.IStudent;
import ro.ucv.ace.model.ISubgroup;
import ro.ucv.ace.model.impl.Student;

/**
 * Created by tzapt on 11/20/2016.
 */
@Component
public class StudentBuilder implements IStudentBuilder {

    @Override
    public IStudent build(ESStudentDto studentDto, String username, String password, ISubgroup subgroup) {
        return new Student(studentDto.getFirstName(), studentDto.getLastName(), studentDto.getEmail(), studentDto.getSsn(),
                studentDto.getGender(), username, password, subgroup);
    }
}
