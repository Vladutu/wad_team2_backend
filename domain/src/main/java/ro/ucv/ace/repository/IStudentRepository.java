package ro.ucv.ace.repository;

import ro.ucv.ace.model.IStudent;

import java.util.List;

/**
 * Created by tzapt on 11/20/2016.
 */
public interface IStudentRepository {

    IStudent save(IStudent student);

    List<IStudent> findAll();

    IStudent delete(int id);

    IStudent findOne(int id);
}
