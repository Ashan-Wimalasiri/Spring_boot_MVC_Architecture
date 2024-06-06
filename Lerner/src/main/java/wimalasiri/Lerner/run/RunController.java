package wimalasiri.Lerner.run;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// this is controller in mvc approach
// This is interface for interating with view
@RestController
public class RunController {
    
    @GetMapping("/hello")
    String home(){
        return "Hello Runner";
    }
}
