package de.marhan.rabbitmq.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageConsumer {

    @RabbitListener(queues = "playground-queue")
    public void processMessage(String content) {
        log.debug("Message Received:  {}", content);
    }

}
