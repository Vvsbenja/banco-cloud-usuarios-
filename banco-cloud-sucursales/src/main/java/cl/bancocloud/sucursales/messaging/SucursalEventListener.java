package cl.bancocloud.sucursales.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SucursalEventListener {
    private static final Logger log = LoggerFactory.getLogger(SucursalEventListener.class);
    @RabbitListener(queues = "${app.messaging.queue}")
    public void recibirEvento(String mensaje) {
        log.info("Evento RabbitMQ recibido: {}", mensaje);
    }
}
