package yuriy.weiss.reactor.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ReactorTrainingApplication {

    public static void main( String[] args ) {
        ConfigurableApplicationContext context = SpringApplication.run( ReactorTrainingApplication.class, args );

        MultithreadingFlux multithreadingFlux = context.getBean( MultithreadingFlux.class );
        multithreadingFlux.launch();
    }
}
