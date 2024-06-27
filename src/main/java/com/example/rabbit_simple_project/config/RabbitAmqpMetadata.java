package com.example.rabbit_simple_project.config;

public class RabbitAmqpMetadata {

	public static final String AMQP_HOST = System.getenv("AMQP_HOST") != null
			? System.getenv("AMQP_HOST")
			: System.getProperty("AMQP_HOST", "amqp://guest:guest@localhost:5672");

	public static final String QUEUE_MESSAGES = "default_messages";
	public static final String QUEUE_MESSAGES_DLQ = QUEUE_MESSAGES + ".dlq";
	public static final String EXCHANGE_MESSAGES = QUEUE_MESSAGES + "_exchange";

	public static final String DLX_EXCHANGE_MESSAGES = QUEUE_MESSAGES + ".dlx";
	public static final String QUEUE_PARKING_LOT = QUEUE_MESSAGES + ".parking-lot";
	public static final String EXCHANGE_PARKING_LOT = QUEUE_MESSAGES + "_exchange.parking-lot";

}
