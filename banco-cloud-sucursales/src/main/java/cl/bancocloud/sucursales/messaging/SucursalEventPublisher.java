package cl.bancocloud.sucursales.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SucursalEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String exchange;
    private final String routingKey;
    public SucursalEventPublisher(RabbitTemplate rabbitTemplate,
            @Value("${app.messaging.exchange}") String exchange,
            @Value("${app.messaging.routing-key}") String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }
    public void publicarCreacion(Long sucursalId) {
        rabbitTemplate.convertAndSend(exchange, routingKey, "SUCURSAL_CREADA:" + sucursalId);
    }
}
