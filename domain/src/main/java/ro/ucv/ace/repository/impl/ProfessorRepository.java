package ro.ucv.ace.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.ucv.ace.model.IProfessor;
import ro.ucv.ace.model.impl.Professor;
import ro.ucv.ace.repository.IJpaRepository;
import ro.ucv.ace.repository.IProfessorRepository;

import java.util.List;

/**
 * Created by tzapt on 11/19/2016.
 */
@Repository("professorRepository")
public class ProfessorRepository implements IProfessorRepository {

    @Autowired
    private IJpaRepository<IProfessor, Professor, Integer> innerProfessorRepository;

    @Override
    public IProfessor save(IProfessor professor) {
        return innerProfessorRepository.save(professor);
    }

    @Override
    public List<IProfessor> findAll() {
        return innerProfessorRepository.findAll();
    }

    @Override
    public IProfessor delete(int id) {
        return innerProfessorRepository.delete(id);
    }

    @Override
    public IProfessor findOne(int id) {
        return innerProfessorRepository.findOne(id);
    }
}
