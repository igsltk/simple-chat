package ru.igsltk.app.controller;

import ru.igsltk.app.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/app")
public class AppController {

	@Autowired
	private ChatService chatService;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	@Value("${eureka.instance.instance-id}")
	private String instanceId;


	@GetMapping
	public String showMainPage(Model model) {
		List<String> messagesList = chatService.findAllMessages();
		Collections.reverse(messagesList);
		model.addAttribute("instanceId", instanceId);
		model.addAttribute("messages", messagesList);
		return "main";
	}

	@MessageMapping("/chat")
	public void sendMessage(String message) {
		rabbitTemplate.convertAndSend("chat.exchange", "input.chat.message", message);
		messagingTemplate.convertAndSend("/app/receive", message);
	}

}