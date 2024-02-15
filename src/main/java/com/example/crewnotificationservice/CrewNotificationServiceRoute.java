package com.example.crewnotificationservice;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CrewNotificationServiceRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        restConfiguration()
                .enableCORS(true)
                .component("jetty")
                .host("0.0.0.0")
                .port(8988);
        // .bindingMode(RestBindingMode.json);

        rest()
                .get("/hello-kafka")
                .to("direct:hello-kafka");


        from("kafka:{{topic}}?brokers={{broker}}")
                .log("Message received from Kafka : ${body}")
                .log("    on the topic ${headers[kafka.TOPIC]}")
                .log("    on the partition ${headers[kafka.PARTITION]}")
                .log("    with the offset ${headers[kafka.OFFSET]}");

        // Kafka Consumer
        /*from("kafka:{{topic}}?brokers={{broker}}&sslKeystoreLocation=/home/jboss/user.jks" +
                "&sslKeystorePassword=password" +
                "&sslKeyPassword=password" +
                "&sslTruststoreLocation=/home/jboss/ca.jks" +
                "&sslTruststorePassword=password" +
                "&securityProtocol=SSL")
                .log("Message received from Kafka : ${body}")
                .log("    on the topic ${headers[kafka.TOPIC]}")
                .log("    on the partition ${headers[kafka.PARTITION]}")
                .log("    with the offset ${headers[kafka.OFFSET]}");*/
    }
}
