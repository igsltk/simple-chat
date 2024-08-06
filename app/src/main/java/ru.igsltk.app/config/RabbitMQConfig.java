package ru.igsltk.app.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${spring.rabbitmq.exchange}")
	private String exchangeName;

	@Value("${spring.rabbitmq.queue}")
	private String queueName;

	@Value("${spring.rabbitmq.routingkey}")
	private String routingKey;

	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(exchangeName);
	}

	@Bean
	public Queue queue() {
		return new Queue(queueName);
	}

	@Bean
	public Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKey);
	}
}