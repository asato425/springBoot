package com.example.springboot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class CalculationController {

    private final CalculationService calculationService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("calculationForm", new CalculationForm());
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@ModelAttribute CalculationForm calculationForm,
                            Model model) {
        int firstNumber = calculationForm.getFirstNumber();
        int secondNumber = calculationForm.getSecondNumber();
        int result = calculationService.add(firstNumber, secondNumber);
        model.addAttribute("firstNumber", firstNumber);
        model.addAttribute("secondNumber", secondNumber);
        model.addAttribute("result", result);
        return "result";
    }
}