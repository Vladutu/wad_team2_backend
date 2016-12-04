package ro.ucv.ace.service;

import ro.ucv.ace.dto.ResponseMessageDto;
import ro.ucv.ace.dto.solution.ESSolutionDto;
import ro.ucv.ace.dto.solution.SolutionDto;

/**
 * Created by tzapt on 12/4/2016.
 */
public interface ISolutionService {

    ResponseMessageDto save(int studentId, int taskId, ESSolutionDto solutionDto);

    SolutionDto delete(int solutionId, ESSolutionDto solutionDto);
}
