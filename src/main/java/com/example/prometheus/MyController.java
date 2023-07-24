package com.example.prometheus;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    private final Counter myCounter;

    public MyController(MeterRegistry registry) {
        this.myCounter = Counter.builder("my_counter")
                .description("A custom counter_metric")
                .register(registry);
    }

    @GetMapping("/hello")
    public String hello() {
        myCounter.increment();
        return "Hello World!";
    }
}
