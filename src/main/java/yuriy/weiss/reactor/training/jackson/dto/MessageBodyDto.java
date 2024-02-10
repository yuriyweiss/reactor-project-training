package yuriy.weiss.reactor.training.jackson.dto;

import java.time.LocalDate;

public record MessageBodyDto(String message, LocalDate operDate) {
}
