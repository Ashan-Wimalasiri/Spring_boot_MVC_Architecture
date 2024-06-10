package wimalasiri.Lerner.run;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

//import jakarta.validation.Valid;

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
        Optional<Run> run = runRepository.findById(id);
        if (!run.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run not found");
        }
        return run.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/saveRun")
    public void saveRun(@RequestBody Run run){
        runRepository.saveRun(run);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/updateRun/{id}")
    public void updateRun(@RequestBody Run run,@PathVariable Integer id){
        runRepository.updateRun(run, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/deleteRun/{id}")
    public void deleteRun(@PathVariable Integer id){
        runRepository.deleteRun(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/count")
    public Integer count(){
        return runRepository.count().orElse(0);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/saveAll")
    public void saveAll(@RequestBody List<Run> runs){
        runRepository.saveAll(runs);
    }
}
