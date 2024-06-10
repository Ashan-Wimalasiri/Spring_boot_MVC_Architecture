package wimalasiri.Lerner.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

public class RunJSONDataLoader implements CommandLineRunner{
    private static final Logger log = LoggerFactory.getLogger(RunJSONDataLoader.class);
    private final RunRepository runRepository;

    public RunJSONDataLoader(RunRepository runRepository){
        this.runRepository = runRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (runRepository.count().orElse(0) == 0){
            try {
                log.info("Loading data from JSON file");
                loadData();
                log.info("Data loaded from JSON file");
            } catch (Exception e) {
                log.error("Error loading data", e);
            }
        }
    }

    private void loadData() {
        // TODO Auto-generated method stub
        
    }

}
