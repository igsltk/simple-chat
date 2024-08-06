package ru.igsltk.chats.controller;

import ru.igsltk.chats.entity.Message;
import ru.igsltk.chats.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {

	@Autowired
	private MessageService messageService;

	@GetMapping("/messages")
	public List<String> getMessages() {
		return messageService.findAllPayloads();
	}
	
}