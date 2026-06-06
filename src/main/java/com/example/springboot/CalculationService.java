package com.example.springboot;

import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    public int add(int a, int b) {
        return a + b;
    }
}
