package com.example.springboot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class CalculationController {

    private final CalculationService calculationService;

    @GetMapping("/")
    public ModelAndView showCalculationForm(ModelAndView mv) {
        mv.addObject("calculationForm", new CalculationForm());
        mv.setViewName("index");
        return mv;
    }

    @PostMapping("/calculate")
    public ModelAndView calculate(@ModelAttribute @Valid CalculationForm calculationForm,
            BindingResult bindingResult,
            ModelAndView mv,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            mv.setViewName("index");
            return mv;
        }

        int firstNumber = calculationForm.getFirstNumber();
        int secondNumber = calculationForm.getSecondNumber();
        int result = calculationService.add(firstNumber, secondNumber);
        redirectAttributes.addAttribute("firstNumber", firstNumber);
        redirectAttributes.addAttribute("secondNumber", secondNumber);
        redirectAttributes.addAttribute("result", result);
        mv.setViewName("redirect:/result");
        return mv;
    }

    @GetMapping("/result")
    public ModelAndView showResult(ModelAndView mv,
            @RequestParam("firstNumber") int firstNumber,
            @RequestParam("secondNumber") int secondNumber,
            @RequestParam("result") int result) {
        mv.addObject("firstNumber", firstNumber);
        mv.addObject("secondNumber", secondNumber);
        mv.addObject("result", result);
        mv.setViewName("result");
        return mv;
    }
}