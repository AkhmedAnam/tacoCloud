package ru.ahmed.anam.taco;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Component
@ConfigurationProperties(prefix = "taco.order")
@Data
@Validated
public class OrderConfig {
    @Min(value = 5, message = "'lastOrderSize must be in range [5, 25]'")
    @Max(value = 25, message = "'lastOrderSize must be in range [5, 25]'")
    private int lastOrderSize;
}
