package ro.ucv.ace.repository;

import ro.ucv.ace.model.IProfessor;

import java.util.List;

/**
 * Created by tzapt on 11/19/2016.
 */
public interface IProfessorRepository {
    IProfessor save(IProfessor professor);

    List<IProfessor> findAll();

    IProfessor delete(int id);

    IProfessor findOne(int id);
}
