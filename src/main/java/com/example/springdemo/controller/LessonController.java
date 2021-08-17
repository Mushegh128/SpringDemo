package com.example.springdemo.controller;

import com.example.springdemo.model.Lesson;
import com.example.springdemo.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LessonController {
    @Autowired
    private LessonRepository lessonRepository;

    @GetMapping("/lessons")
    public String lesson(ModelMap modelMap) {

        List<Lesson> all = lessonRepository.findAll();
        modelMap.addAttribute("lessons",all);
    return "lesson";
    }

    @GetMapping("/addLesson")
    public String addLesson(){
        return "addLesson";
    }


    @PostMapping("/addLesson")
    public String addLessonPost(@ModelAttribute Lesson lesson){
        lessonRepository.save(lesson);
        return "redirect:/lessons";
    }

}
