package de.marhan.rabbitmq.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
//@Configuration
public class RabbitMQConfiguration {

    private final PlaygroundProperties properties;

    @Bean
    Queue queue() {
        return new Queue(properties.getQueueName(), false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(properties.getExchangeName());
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(properties.getQueueName());
    }
}
