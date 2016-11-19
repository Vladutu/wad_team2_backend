package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ucv.ace.dto.professor.ESProfessorDto;
import ro.ucv.ace.dto.professor.ProfessorDto;
import ro.ucv.ace.exception.EntityBindingException;
import ro.ucv.ace.service.IProfessorService;

import javax.validation.Valid;

/**
 * Created by tzapt on 11/19/2016.
 */
@RestController
@RequestMapping(value = "/secretaries")
public class SecretaryProfessorController {

    @Autowired
    private IProfessorService professorService;

    @RequestMapping(value = "/professors", method = RequestMethod.POST)
    public ResponseEntity<ProfessorDto> saveProfessor(@Valid @RequestBody ESProfessorDto professorDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new EntityBindingException(bindingResult.getFieldErrors());
        }

        ProfessorDto saved = professorService.save(professorDto);

        return new ResponseEntity<>(saved, HttpStatus.OK);
    }
}
