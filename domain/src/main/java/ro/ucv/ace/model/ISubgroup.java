package ro.ucv.ace.model;

import ro.ucv.ace.visitor.SubgroupVisitor;

/**
 * Created by Geo on 19.11.2016.
 */
public interface ISubgroup {

    void accept(SubgroupVisitor visitor);

    void update(String name);
}
