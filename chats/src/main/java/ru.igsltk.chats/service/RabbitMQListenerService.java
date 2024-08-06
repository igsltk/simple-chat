package ru.igsltk.chats.service;

import ru.igsltk.chats.entity.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListenerService {

	@Autowired
	private MessageService messageService;

	@RabbitListener(queues = "chat.queue")
	public void receiveMessage(String payload) {
		Message message = new Message();
		message.setPayload(payload);
		messageService.save(message);
	}
	
}