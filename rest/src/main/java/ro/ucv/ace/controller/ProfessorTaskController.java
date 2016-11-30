package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.ucv.ace.dto.task.ESTaskDto;
import ro.ucv.ace.dto.task.TaskDto;
import ro.ucv.ace.exception.EntityBindingException;
import ro.ucv.ace.service.ITaskService;

import javax.validation.Valid;

/**
 * Created by tzapt on 11/26/2016.
 */
@RestController
@RequestMapping(value = "/professor")
public class ProfessorTaskController {

    @Autowired
    private ITaskService taskService;

    @RequestMapping(value = "/{professorId}/topics/{topicId}/tasks", method = RequestMethod.POST)
    public ResponseEntity<TaskDto> saveTopic(@Valid @RequestBody ESTaskDto taskDto, BindingResult bindingResult,
                                             @PathVariable("professorId") int professorId, @PathVariable("topicId") int topicId) {
        if (bindingResult.hasErrors()) {
            throw new EntityBindingException(bindingResult.getFieldErrors());
        }

        TaskDto saved = taskService.save(professorId, topicId, taskDto);

        return new ResponseEntity<>(saved, HttpStatus.OK);
    }
}
