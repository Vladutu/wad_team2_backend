package ro.ucv.ace.builder;

import org.springframework.stereotype.Component;

/**
 * Created by Geo on 30.10.2016.
 */
@Component
public class TestBuilderImpl implements TestBuilder {
    @Override
    public String buildSomething() {
        return "This is a string";
    }
}
