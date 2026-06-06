package com.example.springboot;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class CalculationForm {

    @NotNull(message = "First number is required")
    private Integer firstNumber;
    @NotNull(message = "Second number is required")
    private Integer secondNumber;
}
