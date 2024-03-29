package org.employee.kafka;

import org.employee.model.EmployeeHobby;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@EnableKafka
@Configuration
public class KafkaConfig {

  @Bean
  public ConsumerFactory<String, String> consumerFactory() {
    Map<String,Object> config=new HashMap<>();
    config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
    config.put(ConsumerConfig.GROUP_ID_CONFIG,"hobbies");
    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,String.class);
    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, String.class);
    return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(),new StringDeserializer());
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String,String> kafkaListener() {
    ConcurrentKafkaListenerContainerFactory factory=new ConcurrentKafkaListenerContainerFactory();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }
}
