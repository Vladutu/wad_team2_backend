package ro.ucv.ace.builder;

import ro.ucv.ace.dto.student.ESStudentDto;
import ro.ucv.ace.model.IStudent;
import ro.ucv.ace.model.ISubgroup;

/**
 * Created by tzapt on 11/20/2016.
 */
public interface IStudentBuilder {

    IStudent build(ESStudentDto studentDto, String username, String password, ISubgroup subgroup);
}
