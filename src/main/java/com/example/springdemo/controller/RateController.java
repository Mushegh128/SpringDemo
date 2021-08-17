package com.example.springdemo.controller;

import com.example.springdemo.model.Rate;
import com.example.springdemo.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class RateController {
    @Autowired
    RateRepository repository;

    @GetMapping("/rates")
    public String showRates(ModelMap modelMap) {
        try {
            List<Rate> all = repository.findAll();
            if (all != null) {
                for (Rate rate : all) {
                    System.out.println(rate);
                }
            }
            modelMap.addAttribute("rates", all);
            return "rate";
        }catch (Exception e){
            return "/lesson" ;
        }
    }

    @GetMapping("/addRate")
    public String addRate() {
        return "addRate";
    }

    @PostMapping("/addRate")
    public String addRatePost(@ModelAttribute Rate rate) {
        repository.save(rate);
        return "rate";
    }

    @GetMapping("/rates/{id}")
    public String showRate(@PathVariable("id") int id, ModelMap modelMap) {
        Optional<Rate> rate = repository.findById(id);
        if (rate.isPresent()) {
            Rate ratePresent = rate.get();
            modelMap.addAttribute("rate", ratePresent);
            return "/oneRate";
        } else {
            return "redirect:/";
        }


    }


}
