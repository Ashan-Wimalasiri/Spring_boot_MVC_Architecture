package wimalasiri.Lerner.run;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Repository
public class RunRepository {

    private static final Logger log = LoggerFactory.getLogger(RunRepository.class);
    private final JdbcClient jdbcClient;

    //dependency injection
    public RunRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("SELECT * FROM Run")
        .query(Run.class)
        .list();
    }

    public void saveRun(Run run) {
        var updated = jdbcClient.sql("INSERT INTO Run(id, title, \"STARTED_ON\", \"COMPLETED_ON\", miles, location) VALUES (?, ?, ?, ?, ?, ?)")
            .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString()))
            .update();
    
        Assert.state(updated == 1, "Failed to insert run " + run.title());
    }
    

    public void updateRun(Run run, Integer id){
        var updated = jdbcClient.sql("UPDATE Run SET title = ?, \"STARTED_ON\" = ?, \"COMPLETED_ON\" = ?, miles = ?, location = ? WHERE id = ?")
        .params(List.of(run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString(), id))
        .update();

        Assert.state(updated == 1, "Failed to update run"+ run.title());

    }
    
    public void deleteRun(Integer id){
        var updated = jdbcClient.sql("DELETE FROM Run WHERE id = ?")
        .params(List.of(id))
        .update();

        Assert.state(updated == 1, "Failed to delete run"+ id);
    }

    public Optional<Run> findById(Integer id){
        return jdbcClient.sql("SELECT * FROM Run WHERE id = ?")
        .params(List.of(id))
        .query(Run.class)
        .optional();
    }

    public Optional<Integer> count(){
        log.debug("Executing count query");
        return jdbcClient.sql("SELECT COUNT(*) FROM Run")
        .query(Integer.class)
        .optional();
    }

    public void saveAll(List<Run> runs){
        runs.forEach(this::saveRun);
    }
    // private List<Run> runs = new ArrayList<>();


    // List<Run> findAll() {
    //     return runs;
    // }

    // Run findById(Integer id) {
    //     return runs.stream().filter(run -> run.getId() == id).findFirst().orElse(null);
    // }

    // public void saveRun(Run run) {
    //     runs.add(run);
    // }

    // public void updateRun(Run run) {
    //     Run existingRun = findById(run.getId());
    //     if (existingRun == null) {
    //         return;
    //     }
    //     runs.remove(existingRun);
    //     runs.add(run);
    // }

    // public void deleteRun(Integer id) {
    //     Run existingRun = findById(id);
    //     if (existingRun == null) {
    //         return;
    //     }
    //     runs.remove(existingRun);
    // }

    // // When the class is created, this method is called
    // @PostConstruct
    // public void init(){
    //     runs.add(new Run(1, "Morning Run", null, null, 5, Location.OUTDOOR));
    //     runs.add(new Run(2, "Evening Run", null, null, 10, Location.OUTDOOR));
    // }
}
