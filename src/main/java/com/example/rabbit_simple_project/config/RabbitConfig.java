package com.example.rabbit_simple_project.config;

import static com.example.rabbit_simple_project.config.RabbitAmqpMetadata.*;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

	@Bean
	public static CachingConnectionFactory rabbitConnectionFactory(RabbitProperties config) {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setUri(AMQP_HOST);
		return connectionFactory;
	}

	@Bean
	public static RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		try (Connection c = connectionFactory.createConnection()) {
			return new RabbitAdmin(connectionFactory);
		}
	}

	@Bean
	Queue messagesQueue() {
		return QueueBuilder.durable(QUEUE_MESSAGES).build();
	}

	@Bean
	Queue deadLetterQueue() {
		return QueueBuilder.durable(QUEUE_MESSAGES_DLQ).build();
	}

	@Bean
	Queue parkingLotQueue() {
		return QueueBuilder.durable(QUEUE_PARKING_LOT).build();
	}

	@Bean
	DirectExchange messagesExchange() {
		return new DirectExchange(EXCHANGE_MESSAGES);
	}

	@Bean
	FanoutExchange deadLetterExchange() {
		return new FanoutExchange(DLX_EXCHANGE_MESSAGES);
	}

	@Bean
	FanoutExchange parkingLotExchange() {
		return new FanoutExchange(EXCHANGE_PARKING_LOT);
	}

	@Bean
	Binding bindingMessages() {
		return BindingBuilder.bind(messagesQueue()).to(messagesExchange()).with(QUEUE_MESSAGES);
	}

	@Bean
	Binding deadLetterBinding() {
		return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange());
	}

	@Bean
	Binding parkingLotBinding() {
		return BindingBuilder.bind(parkingLotQueue()).to(parkingLotExchange());
	}

}
