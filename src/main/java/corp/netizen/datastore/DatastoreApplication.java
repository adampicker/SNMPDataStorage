package corp.netizen.datastore;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import corp.netizen.datastore.listener.StatusMessageListener;

@ComponentScan("corp.netizen.datastore")
@SpringBootApplication
public class DatastoreApplication {

	public static String QUEUE_NAME = "msg_gueue";	
	
	@Bean
	Queue queue() {
		return new Queue(QUEUE_NAME, false);
	}
        
        @Bean
	TopicExchange exchange() {
		return new TopicExchange("spring-boot-exchange");
	}
	
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(QUEUE_NAME);
	}
	
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
	MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(QUEUE_NAME);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(StatusMessageListener receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}
        
        public static void main(String[] args) {
		SpringApplication.run(DatastoreApplication.class, args);
	}
	

}
