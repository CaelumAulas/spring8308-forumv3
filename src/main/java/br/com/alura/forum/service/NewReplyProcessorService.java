package br.com.alura.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.forum.model.Answer;
import br.com.alura.forum.repository.AnswerRepository;

@Service
public class NewReplyProcessorService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
	private NewReplyMailProcessorService newReplyMailProcessor;
	
	public void execute(Answer answer) { 
		this.answerRepository.save(answer);
        this.newReplyMailProcessor.sendNewReplyMailAsync(answer);
        
	}
}
