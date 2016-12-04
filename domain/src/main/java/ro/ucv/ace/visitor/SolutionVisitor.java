package ro.ucv.ace.visitor;

import ro.ucv.ace.dto.solution.SolutionDto;
import ro.ucv.ace.model.Solution;

/**
 * Created by tzapt on 12/4/2016.
 */
public class SolutionVisitor {

    private SolutionDto solutionDto;

    public void visit(Solution solution) {
        solutionDto = new SolutionDto(solution.getId(), solution.getMark(), solution.getDirectoryPath());
    }

    public SolutionDto getSolutionDto() {
        return solutionDto;
    }
}
