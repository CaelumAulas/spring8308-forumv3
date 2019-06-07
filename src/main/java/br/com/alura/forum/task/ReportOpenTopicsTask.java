package br.com.alura.forum.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.alura.forum.model.OpenTopicByCategory;
import br.com.alura.forum.repository.OpenTopicByCategoryRepository;
import br.com.alura.forum.repository.TopicRepository;

@Component
public class ReportOpenTopicsTask {
	@Autowired
	private OpenTopicByCategoryRepository openTopicsRepository;
	@Autowired
	private TopicRepository topicRepository;
	
	@Scheduled(cron = "0 0 20 * * *")
	public void execute() {
		List<OpenTopicByCategory> openTopics =
				topicRepository.findOpenTopicsByCategory();
		openTopicsRepository.saveAll(openTopics);
	}
}
