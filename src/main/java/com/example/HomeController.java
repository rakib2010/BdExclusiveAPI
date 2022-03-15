
package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Instructor
 */

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String helloWorld(){
        return "index";
    }
    
}
