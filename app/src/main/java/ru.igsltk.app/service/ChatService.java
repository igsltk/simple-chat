package ru.igsltk.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.core.ParameterizedTypeReference;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

	@Autowired
	private DiscoveryClient discoveryClient;

	public List<String> findAllMessages() {
		List<ServiceInstance> instances = discoveryClient.getInstances("chats");
		if (!instances.isEmpty()) {
			String serviceUrl = instances.get((int)Math.random()*instances.size()).getUri().toString();
			String url = serviceUrl + "/chats/messages";

			WebClient client = WebClient.create(url);
			return client.get()
						 .retrieve()
						 .bodyToMono(new ParameterizedTypeReference<List<String>>() {})
						 .block();
		}
		return new ArrayList<String>();
	}
	
}