package wimalasiri.Lerner.run;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

// this is controller in mvc approach
// This is interface for interating with view
@RestController
@RequestMapping("/api/v1")

public class RunController {
    
    private final RunRepository runRepository;

    // Constructor Injection
    public RunController(RunRepository runRepository){
        this.runRepository = runRepository;
    }

    @GetMapping("/hello")
    String home(){
        return "Hello Runner";
    }

    @GetMapping("/runs")
    List<Run> runs(){
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id){
        Optional<Run> run = Optional.ofNullable(runRepository.findById(id));
        if (!run.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run not found");
        }
        return run.get();
    }
    
}
