package Steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Hooks {

	@Before()
	public void beforeScenario(){
		log.info("Before Scenario Called");
	}

    @After
	public void afterScenario(){
		log.info("After Scenario Called");
    }

}
