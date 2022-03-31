package ru.invitro.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.oas.annotations.EnableOpenApi;
import co.elastic.apm.attach.ElasticApmAttacher;
import ru.invitro.logstash.EnableLogstash;


@SpringBootApplication
@EnableOpenApi
@EnableLogstash(application = "signature-adapter", version = "0.1.0")
@PropertySource("classpath:application-prod.properties")
public class AdapterServiceApplication {

    public static void main(String[] args) {
        ElasticApmAttacher.attach();
        SpringApplication.run(AdapterServiceApplication.class, args);
    }
}
