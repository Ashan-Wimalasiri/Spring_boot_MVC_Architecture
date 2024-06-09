package wimalasiri.Lerner.run;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class RunRepository {
    
    private List<Run> runs = new ArrayList<>();


    List<Run> findAll() {
        return runs;
    }

    Run findById(Integer id) {
        return runs.stream().filter(run -> run.getId() == id).findFirst().orElse(null);
    }

    public void saveRun(Run run) {
        runs.add(run);
    }

    public void updateRun(Run run) {
        Run existingRun = findById(run.getId());
        if (existingRun == null) {
            return;
        }
        runs.remove(existingRun);
        runs.add(run);
    }

    public void deleteRun(Integer id) {
        Run existingRun = findById(id);
        if (existingRun == null) {
            return;
        }
        runs.remove(existingRun);
    }

    // When the class is created, this method is called
    @PostConstruct
    public void init(){
        runs.add(new Run(1, "Morning Run", null, null, 5, Location.OUTDOOR));
        runs.add(new Run(2, "Evening Run", null, null, 10, Location.OUTDOOR));
    }
}
