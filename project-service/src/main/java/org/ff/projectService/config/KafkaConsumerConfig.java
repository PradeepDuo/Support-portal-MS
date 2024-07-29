package org.ff.projectService.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.ff.configModule.model.Project;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
public class KafkaConsumerConfig {

    @Bean
    public Map<String, Object> consumerConfig(){
        HashMap<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"project-group");
        properties.put(JsonDeserializer.TRUSTED_PACKAGES,"*");
        return properties;
    }
    @Bean
    public ConsumerFactory<String,Project> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }
//    @Bean
//    public ConsumerFactory<String,Project> consumerFactory(){
//        JsonDeserializer<Project> deserializer = new JsonDeserializer<>(Project.class);
//        deserializer.addTrustedPackages("org.ff.configModule.model");
//        return new DefaultKafkaConsumerFactory<>(consumerConfig(),
//                new JsonDeserializer(),
//                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(Project.class)));
//    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,Project>> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, Project> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
