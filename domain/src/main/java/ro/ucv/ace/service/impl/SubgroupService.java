package ro.ucv.ace.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.builder.ISubgroupBuilder;
import ro.ucv.ace.dto.subgroup.ESSubgroupDto;
import ro.ucv.ace.dto.subgroup.SubgroupDto;
import ro.ucv.ace.model.ISubgroup;
import ro.ucv.ace.repository.ISubgroupRepository;
import ro.ucv.ace.service.ISubgroupService;
import ro.ucv.ace.visitor.SubgroupVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geo on 19.11.2016.
 */
@Service("subgroupService")
@Transactional(rollbackFor = Exception.class)
public class SubgroupService implements ISubgroupService {

    @Autowired
    private ISubgroupRepository subgroupRepository;

    @Autowired
    private ISubgroupBuilder subgroupBuilder;

    @Autowired
    private SubgroupVisitor visitor;

    @Override
    public SubgroupDto save(ESSubgroupDto subgroupDto) {
        ISubgroup subgroup = subgroupRepository.save(subgroupBuilder.build(subgroupDto));
        subgroup.accept(visitor);

        return visitor.getSubgroupDto();
    }

    @Override
    public List<SubgroupDto> getAll() {
        List<ISubgroup> subgroups = subgroupRepository.findAll();
        List<SubgroupDto> subgroupDtos = new ArrayList<>();
        subgroups.forEach(s -> {
            s.accept(visitor);
            subgroupDtos.add(visitor.getSubgroupDto());
        });

        return subgroupDtos;
    }

    @Override
    public SubgroupDto delete(int id) {
        ISubgroup subgroup = subgroupRepository.delete(id);

        subgroup.accept(visitor);

        return visitor.getSubgroupDto();
    }

    @Override
    public SubgroupDto edit(int id, ESSubgroupDto subgroupDto) {
        ISubgroup subgroup = subgroupRepository.findOne(id);
        subgroup.update(subgroupDto.getName());
        subgroup.accept(visitor);

        return visitor.getSubgroupDto();
    }
}
