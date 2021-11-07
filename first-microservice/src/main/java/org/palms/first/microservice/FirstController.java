package org.palms.first.microservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first")
public class FirstController {

    @GetMapping
    public String firstService(){
        return "It's first micro-service";
    }

    @GetMapping("/more")
    public String firstServiceMore(){
        return "It's one more from first micro-service";
    }
}
