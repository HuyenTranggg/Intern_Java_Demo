package demo.annotation.createbean.controller;

import org.springframework.stereotype.Controller;

// @Controller: Dùng để đánh dấu các lớp ở tầng trình bày (Presentation Layer)
@Controller
public class MovieController {
    public MovieController() {
        System.out.println("Creating a bean with @Controller annotation");
    }
}