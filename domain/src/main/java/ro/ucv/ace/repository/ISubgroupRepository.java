package ro.ucv.ace.repository;

import ro.ucv.ace.model.ISubgroup;

import java.util.List;

/**
 * Created by Geo on 19.11.2016.
 */
public interface ISubgroupRepository {

    ISubgroup save(ISubgroup subgroup);

    List<ISubgroup> findAll();

    ISubgroup delete(int id);

    ISubgroup findOne(int id);

    ISubgroup findByName(String name);
}
