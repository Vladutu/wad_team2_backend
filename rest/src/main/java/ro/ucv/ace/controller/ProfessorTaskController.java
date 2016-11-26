package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ucv.ace.service.ITaskService;

/**
 * Created by tzapt on 11/26/2016.
 */
@RestController
@RequestMapping(value = "/professor")
public class ProfessorTaskController {

    @Autowired
    private ITaskService taskService;
}
