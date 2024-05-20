package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("")
@Slf4j
public class PageController {

    @GetMapping("/list")
    public String subPage(Model model) {
        return "list";
    }
}
