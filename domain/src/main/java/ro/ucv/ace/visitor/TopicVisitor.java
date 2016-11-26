package ro.ucv.ace.visitor;

import org.springframework.stereotype.Component;
import ro.ucv.ace.dto.topic.TopicDto;
import ro.ucv.ace.model.Topic;

/**
 * Created by tzapt on 11/26/2016.
 */
@Component
public class TopicVisitor {

    private TopicDto topicDto;

    public void visit(Topic topic) {
        topicDto = new TopicDto(topic.getId(), topic.getName());
    }

    public TopicDto getTopicDto() {
        return topicDto;
    }
}
