package br.com.alura.forum;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.alura.forum.model.OpenTopicByCategory;
import br.com.alura.forum.repository.TopicRepository;
import br.com.alura.forum.setup.TopicRepositoryTestsSetup;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class TopicRepositoryTests {
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private TopicRepository topicRepository;

	@Test
	public void shouldSaveTopic() {
		TopicRepositoryTestsSetup setup = new TopicRepositoryTestsSetup(testEntityManager);
		setup.openTopicsByCategorySetup();
		
		List<OpenTopicByCategory> openTopics = topicRepository.findOpenTopicsByCategory();
		
		//checar se ocorreu como esperado
		assertThat(openTopics).isNotEmpty();
        assertThat(openTopics).hasSize(2);

        assertThat(openTopics.get(0).getCategoryName())
                .isEqualTo("Programação");
        assertThat(openTopics.get(0).getTopicCount()).isEqualTo(2);

        assertThat(openTopics.get(1).getCategoryName())
                .isEqualTo("Front-end");
        assertThat(openTopics.get(1).getTopicCount()).isEqualTo(1);

	}
}
