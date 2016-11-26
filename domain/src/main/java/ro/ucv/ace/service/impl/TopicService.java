package ro.ucv.ace.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.builder.ITopicBuilder;
import ro.ucv.ace.dto.topic.ESTopicDto;
import ro.ucv.ace.dto.topic.TopicDto;
import ro.ucv.ace.model.Topic;
import ro.ucv.ace.repository.ITopicRepository;
import ro.ucv.ace.service.ITopicService;
import ro.ucv.ace.visitor.TopicVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tzapt on 11/26/2016.
 */
@Service("topicService")
@Transactional(rollbackFor = Exception.class)
public class TopicService implements ITopicService {

    @Autowired
    private ITopicRepository topicRepository;

    @Autowired
    private TopicVisitor visitor;

    @Autowired
    private ITopicBuilder topicBuilder;

    @Override
    public TopicDto save(ESTopicDto topicDto) {
        Topic topic = topicRepository.save(topicBuilder.build(topicDto));
        topic.accept(visitor);

        return visitor.getTopicDto();
    }

    @Override
    public List<TopicDto> getAll() {
        List<Topic> subgroups = topicRepository.findAll();
        List<TopicDto> topicDtos = new ArrayList<>();

        subgroups.forEach(s -> {
            s.accept(visitor);
            topicDtos.add(visitor.getTopicDto());
        });

        return topicDtos;
    }

    @Override
    public TopicDto delete(int id) {
        Topic topic = topicRepository.delete(id);
        topic.accept(visitor);

        return visitor.getTopicDto();
    }

    @Override
    public TopicDto edit(int id, ESTopicDto topicDto) {
        Topic topic = topicRepository.findOne(id);
        topic.update(topicDto.getName());
        topic = topicRepository.save(topic);
        topic.accept(visitor);

        return visitor.getTopicDto();
    }
}
