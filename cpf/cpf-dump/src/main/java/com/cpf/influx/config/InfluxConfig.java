package com.cpf.influx.config;

import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.influxdb.DefaultInfluxDBTemplate;
import org.springframework.data.influxdb.InfluxDBConnectionFactory;
import org.springframework.data.influxdb.InfluxDBProperties;

/**
 * @author jieping
 * @create 2018-04-17
 * @desc influxdb 配置
 **/
@Configuration
@EnableConfigurationProperties(InfluxDBProperties.class)
public class InfluxConfig {
    @Bean
    public InfluxDBConnectionFactory connectionFactory(final InfluxDBProperties properties) {

        return new InfluxDBConnectionFactory(properties);
    }
    @Bean
    public DefaultInfluxDBTemplate defaultInfluxDBTemplate(final InfluxDBConnectionFactory connectionFactory) {
        return new DefaultInfluxDBTemplate(connectionFactory);
    }
    @Bean
    public InfluxDBResultMapper mapper() {
        return new InfluxDBResultMapper();
    }

}