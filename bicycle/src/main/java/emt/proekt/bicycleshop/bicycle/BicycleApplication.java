package emt.proekt.bicycleshop.bicycle;

import emt.proekt.bicycleshop.bicycle.port.client.RemoteEventLogServiceClient;
import emt.proekt.bicycleshop.sharedkernel.SharedConfiguration;
import emt.proekt.bicycleshop.sharedkernel.infra.eventlog.RemoteEventLogService;
import lombok.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
@Import(SharedConfiguration.class)
public class BicycleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BicycleApplication.class, args);
    }


    @Bean
    public RemoteEventLogService orderEvents(@Value("${app.orders.url}") String serverURL,
                                             @Value("${app.orders.connectionTimeout}")int connectionTimeout,
                                             @Value("${app.orders.readTimeout}")int readTimeout){
        return new RemoteEventLogServiceClient(serverURL,connectionTimeout,readTimeout);
    }
}
