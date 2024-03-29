package br.com.alura.forum.actuator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import br.com.alura.forum.model.OpenTopicByCategory;
import br.com.alura.forum.repository.TopicRepository;

@Component
@Endpoint(id = "openTopics")
public class UnansweredTopicsActuatorEndpoint {
	@Autowired
    private TopicRepository topicRepository;
    
    @ReadOperation
    public List<OpenTopicByCategory> execute() {
        return topicRepository.findOpenTopicsByCategory();
    }

}
