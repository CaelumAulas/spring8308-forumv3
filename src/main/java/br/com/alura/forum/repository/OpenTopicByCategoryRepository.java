package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.alura.forum.model.OpenTopicByCategory;

public interface OpenTopicByCategoryRepository extends Repository<OpenTopicByCategory, Long> {
	void saveAll(Iterable<OpenTopicByCategory> openTopics);

	List<OpenTopicByCategory> findAll();
}
