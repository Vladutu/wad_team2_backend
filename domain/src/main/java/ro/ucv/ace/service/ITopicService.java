package ro.ucv.ace.service;

import ro.ucv.ace.dto.topic.ESTopicDto;
import ro.ucv.ace.dto.topic.TopicDto;

import java.util.List;

/**
 * Created by tzapt on 11/26/2016.
 */
public interface ITopicService {

    TopicDto save(ESTopicDto topicDto);

    List<TopicDto> getAll();

    TopicDto delete(int id);

    TopicDto edit(int id, ESTopicDto topicDto);
}
