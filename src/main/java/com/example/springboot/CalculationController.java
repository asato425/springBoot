package com.example.springboot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
@Slf4j
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
        // ログで受け取った値を確認
        log.info("calculate() - firstNumber={}, secondNumber={}", calculationForm.getFirstNumber(), calculationForm.getSecondNumber());
        calculationForm.setResult(calculationService.add(calculationForm.getFirstNumber(), calculationForm.getSecondNumber()));
        log.info("calculate() - result={}", calculationForm.getResult());
        redirectAttributes.addFlashAttribute("calculationForm", calculationForm);
        mv.setViewName("redirect:/result");
        return mv;
    }

    @GetMapping("/result")
    public ModelAndView showResult(ModelAndView mv,
            @ModelAttribute("calculationForm") CalculationForm calculationForm
            ) {
        log.info("showResult() - calculationForm: first={}, second={}, result={}", calculationForm.getFirstNumber(), calculationForm.getSecondNumber(), calculationForm.getResult());
        // mv.addObject("calculationForm", calculationForm);
        mv.setViewName("result");
        return mv;
    }
}