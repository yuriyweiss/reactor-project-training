package yuriy.weiss.reactor.training;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

@Configuration
public class ApplicationConfiguration {

    @Primary
    @Bean( name = "customObjectMapper" )
    public ObjectMapper customObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer( LocalDate.class,
                new LocalDateDeserializer( DateTimeFormatter.ofPattern( "yyyy.MM.dd" ) ) );
        javaTimeModule.addSerializer( LocalDate.class,
                new LocalDateSerializer( DateTimeFormatter.ofPattern( "yyyy.MM.dd" ) ) );
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern( "yyyy.MM.dd'T'HH:mm:ss" )
                .appendFraction( ChronoField.NANO_OF_SECOND, 1, 9, true )
                .toFormatter();
        javaTimeModule.addDeserializer( LocalDateTime.class, new LocalDateTimeDeserializer( formatter ) );
        javaTimeModule.addSerializer( LocalDateTime.class, new LocalDateTimeSerializer( formatter ) );
        mapper.registerModule( javaTimeModule );
        return mapper;
    }
}
