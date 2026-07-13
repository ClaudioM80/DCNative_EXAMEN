package cl.duoc.cloudnative.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.queue}")
    private String queue;

    @Value("${app.rabbitmq.dlx-exchange}")
    private String dlxExchange;

    @Value("${app.rabbitmq.dlq}")
    private String dlq;

    @Value("${app.rabbitmq.routing-key}")
    private String routingKey;

    @Bean
    public Queue pedidosQueue() {
        return QueueBuilder.durable(queue)
                .withArgument("x-dead-letter-exchange", dlxExchange)
                .build();
    }

    @Bean
    public Queue pedidosDlq() {
        return QueueBuilder.durable(dlq).build();
    }

    @Bean
    public DirectExchange pedidosExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public DirectExchange pedidosDlxExchange() {
        return new DirectExchange(dlxExchange);
    }

    @Bean
    public Binding pedidosBinding() {
        return BindingBuilder.bind(pedidosQueue())
                .to(pedidosExchange())
                .with(routingKey);
    }

    @Bean
    public Binding pedidosDlxBinding() {
        return BindingBuilder.bind(pedidosDlq())
                .to(pedidosDlxExchange())
                .with(routingKey);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}