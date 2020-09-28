package de.marhan.rabbitmq.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessageProducer {

    private final AmqpTemplate amqpTemplate;
    private final PlaygroundProperties properties;

    public void sendMessage(String message) {
        log.debug("About to send message: {}", message);
        amqpTemplate.convertAndSend(properties.getExchangeName(), properties.getRoutingKey(), message);
    }

    @Scheduled(fixedRate = 5000)
    public void sendMessageScheduled() {
        this.sendMessage("Run by scheduler of instance " + properties.getInstanceName() + " at " + LocalDateTime.now());
    }

    @PostConstruct
    private void printInstanceName() {
        log.debug("INSTANCE NAME: {}", properties.getInstanceName());
    }

}
