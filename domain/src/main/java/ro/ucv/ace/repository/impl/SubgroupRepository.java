package ro.ucv.ace.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.ucv.ace.model.ISubgroup;
import ro.ucv.ace.model.impl.Subgroup;
import ro.ucv.ace.repository.IJpaRepository;
import ro.ucv.ace.repository.ISubgroupRepository;

import java.util.List;

/**
 * Created by Geo on 19.11.2016.
 */
@Repository("subgroupRepository")
public class SubgroupRepository implements ISubgroupRepository {

    @Autowired
    private IJpaRepository<ISubgroup, Subgroup, Integer> innerSubgroupRepository;

    @Override
    public ISubgroup save(ISubgroup subgroup) {
        return innerSubgroupRepository.save(subgroup);
    }

    @Override
    public List<ISubgroup> findAll() {
        return innerSubgroupRepository.findAll();
    }

    @Override
    public ISubgroup delete(int id) {
        return innerSubgroupRepository.delete(id);
    }

    @Override
    public ISubgroup findOne(int id) {
        return innerSubgroupRepository.findOne(id);
    }
}
