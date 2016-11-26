package ro.ucv.ace.dto.topic;

/**
 * Created by tzapt on 11/26/2016.
 */
public class TopicDto {

    private Integer id;

    private String name;

    public TopicDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
