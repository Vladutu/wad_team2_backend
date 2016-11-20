package ro.ucv.ace.builder.impl;

import org.springframework.stereotype.Component;
import ro.ucv.ace.builder.IProfessorBuilder;
import ro.ucv.ace.dto.professor.ESProfessorDto;
import ro.ucv.ace.model.IProfessor;
import ro.ucv.ace.model.impl.Professor;

/**
 * Created by tzapt on 11/19/2016.
 */
@Component
public class ProfessorBuilder implements IProfessorBuilder {

    @Override
    public IProfessor build(ESProfessorDto professorDto, String username, String password) {
        return new Professor(professorDto.getFirstName(), professorDto.getGender(), professorDto.getLastName(),
                professorDto.getPosition(), professorDto.getSsn(), professorDto.getEmail(), username, password);
    }
}
