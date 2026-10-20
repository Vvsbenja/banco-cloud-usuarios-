package cl.bancocloud.sucursales.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue sucursalesQueue(@Value("${app.messaging.queue}") String name) {
        return new Queue(name, true);
    }
    @Bean
    public DirectExchange sucursalesExchange(@Value("${app.messaging.exchange}") String name) {
        return new DirectExchange(name, true, false);
    }
    @Bean
    public Binding sucursalesBinding(Queue sucursalesQueue, DirectExchange sucursalesExchange,
                                    @Value("${app.messaging.routing-key}") String key) {
        return BindingBuilder.bind(sucursalesQueue).to(sucursalesExchange).with(key);
    }
}
