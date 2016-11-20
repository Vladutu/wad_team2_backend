package ro.ucv.ace.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.ucv.ace.model.IStudent;
import ro.ucv.ace.model.impl.Student;
import ro.ucv.ace.repository.IJpaRepository;
import ro.ucv.ace.repository.IStudentRepository;

import java.util.List;

/**
 * Created by tzapt on 11/20/2016.
 */
@Repository("studentRepository")
public class StudentRepository implements IStudentRepository {

    @Autowired
    private IJpaRepository<IStudent, Student, Integer> innerStudentRepository;


    @Override
    public IStudent save(IStudent student) {
        return innerStudentRepository.save(student);
    }

    @Override
    public List<IStudent> findAll() {
        return innerStudentRepository.findAll();
    }

    @Override
    public IStudent delete(int id) {
        return innerStudentRepository.delete(id);
    }

    @Override
    public IStudent findOne(int id) {
        return innerStudentRepository.findOne(id);
    }
}
