package ro.ucv.ace.repository;

import ro.ucv.ace.model.Solution;

/**
 * Created by tzapt on 12/4/2016.
 */
public interface ISolutionRepository {

    Solution save(Solution solution);

    Solution delete(int solutionId);

    Solution findOne(int solutionId);
}
