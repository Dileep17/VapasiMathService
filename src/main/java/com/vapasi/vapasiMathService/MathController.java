    package com.vapasi.vapasiMathService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

    @RestController
    public class MathController {
        @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
        public Map<String, Double> add(@RequestBody Map<String, String> entriesToAdd){
            Double sum = 0.0;
            try {
                Collection<String> values = entriesToAdd.values();
                for (String val : values) {
                    sum = sum + Double.valueOf(val);
                }
            } catch (Exception e){
                return (Map<String, Double>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
            }
            Map<String, Double> response = new HashMap<>();
            response.put("sum", sum);
            return response;
        }
    }
