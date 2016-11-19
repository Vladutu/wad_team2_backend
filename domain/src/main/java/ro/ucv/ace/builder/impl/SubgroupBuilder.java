package ro.ucv.ace.builder.impl;

import org.springframework.stereotype.Component;
import ro.ucv.ace.builder.ISubgroupBuilder;
import ro.ucv.ace.dto.subgroup.ESSubgroupDto;
import ro.ucv.ace.model.ISubgroup;
import ro.ucv.ace.model.impl.Subgroup;

/**
 * Created by Geo on 19.11.2016.
 */
@Component
public class SubgroupBuilder implements ISubgroupBuilder {

    @Override
    public ISubgroup build(ESSubgroupDto subgroupDto) {
        return new Subgroup(subgroupDto.getName());
    }
}
