package ro.ucv.ace.repository.components;

/**
 * This class implements the Page interface.
 */
public class PageRequest implements Page {

    private Integer page;

    private Integer limit;

    public PageRequest(Integer page, Integer limit) {
        this.page = page;
        this.limit = limit;
    }

    @Override
    public Integer getLimit() {
        return limit;
    }

    @Override
    public Integer getSkip() {
        return (page - 1) * limit;
    }
}
