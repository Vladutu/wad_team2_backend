package ro.ucv.ace.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.builder.ITopicBuilder;
import ro.ucv.ace.dto.topic.ESTopicDto;
import ro.ucv.ace.dto.topic.TopicDto;
import ro.ucv.ace.exception.DuplicateEntryException;
import ro.ucv.ace.model.Professor;
import ro.ucv.ace.model.Topic;
import ro.ucv.ace.repository.ITopicRepository;
import ro.ucv.ace.repository.impl.ProfessorRepository;
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
    private ProfessorRepository professorRepository;

    @Autowired
    private TopicVisitor visitor;

    @Autowired
    private ITopicBuilder topicBuilder;

    @Override
    public TopicDto save(int professorId, ESTopicDto topicDto) {
        Professor professor = professorRepository.findOne(professorId);

        if (professor.hasTopicWithName(topicDto.getName())) {
            throw new DuplicateEntryException("Task: Duplicate entry '" + topicDto.getName() + "' for Professor with id " + professorId);
        }

        Topic topic = topicRepository.save(topicBuilder.build(topicDto, professorId));
        topic.accept(visitor);

        return visitor.getTopicDto();

    }

    @Override
    public List<TopicDto> getAll(int professorId) {
        List<Topic> topics = topicRepository.findByProfessor(professorId);
        List<TopicDto> topicDtos = new ArrayList<>();

        topics.forEach(s -> {
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
