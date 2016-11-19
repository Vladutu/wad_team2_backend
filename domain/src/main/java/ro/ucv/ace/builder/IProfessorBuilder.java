package ro.ucv.ace.builder;

import ro.ucv.ace.dto.professor.ESProfessorDto;
import ro.ucv.ace.model.IProfessor;

/**
 * Created by tzapt on 11/19/2016.
 */
public interface IProfessorBuilder {

    IProfessor build(ESProfessorDto professorDto, String username, String password);
}
