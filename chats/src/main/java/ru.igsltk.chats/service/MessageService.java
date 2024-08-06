package ru.igsltk.chats.service;

import ru.igsltk.chats.entity.Message;
import ru.igsltk.chats.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private RedisTemplate<String, Message> redisTemplate;

	private static final String REDIS_KEY = "messages";

	public Message save(Message message) {
		messageRepository.save(message);
		List<Message> messages = messageRepository.findAll();
		redisTemplate.delete(REDIS_KEY);
		for (Message msg : messages)
			redisTemplate.opsForList().rightPush(REDIS_KEY, msg);
		redisTemplate.expire(REDIS_KEY, 1, TimeUnit.MINUTES);
		return message;
	}

	public List<Message> findAll() {
		List<Message> messages = redisTemplate.opsForList().range(REDIS_KEY, 0, -1);
		if (messages == null || messages.isEmpty()) {
			messages = messageRepository.findAll();
			for (Message msg : messages)
				redisTemplate.opsForList().rightPush(REDIS_KEY, msg);
			redisTemplate.expire(REDIS_KEY, 1, TimeUnit.MINUTES);
		}
		return messages;
	}

	public List<String> findAllPayloads() {
		List<Message> messages = findAll();
		return messages.stream()
				.map(Message::getPayload)
				.collect(Collectors.toList());
	}

}