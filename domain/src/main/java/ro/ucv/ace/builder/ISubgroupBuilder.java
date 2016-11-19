package ro.ucv.ace.builder;

import ro.ucv.ace.dto.subgroup.ESSubgroupDto;
import ro.ucv.ace.model.ISubgroup;

/**
 * Created by Geo on 19.11.2016.
 */
public interface ISubgroupBuilder {
    ISubgroup build(ESSubgroupDto subgroupDto);
}
