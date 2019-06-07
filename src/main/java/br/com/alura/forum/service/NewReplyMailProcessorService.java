package br.com.alura.forum.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.alura.forum.model.Answer;
import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.service.factory.EmailTemplateFactory;

@Service
public class NewReplyMailProcessorService {
	private Logger logger = LoggerFactory.getLogger(NewReplyMailProcessorService.class);
	
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmailTemplateFactory factory;
	
	@Async
	public void sendNewReplyMailAsync(Answer answer) {
		Topic answeredTopic = answer.getTopic();
		
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
				helper.setTo(answeredTopic.getOwnerEmail());
				helper.setSubject("Responderam a duvida: "+ answeredTopic.getShortDescription());
				helper.setText(factory.getContent(answer), true);
			}
		};
		try {
			mailSender.send(messagePreparator);
		} catch (MailException e) {
			logger.error("Deu erro no envio!");
		}
	}
}
