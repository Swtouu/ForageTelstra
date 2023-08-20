package stepDefinitions;

import au.com.telstra.simcardactivator.SimCardActivator;
import au.com.telstra.simcardactivator.app.dto.SimCardActivateRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SimCardActivator.class, loader = SpringBootContextLoader.class)
public class SimCardActivatorStepDefinitions {
    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;

    private final String BASE_URL = "http://localhost:8080";

    @Given("a SIM card with ICCID {string}")
    public void a_SIM_card_with_ICCID(String iccid) {
        String storedICCID = iccid;
    }

    @When("the activation request is submitted")
    public void the_activation_request_is_submitted() {
        String iccid = "1255789453849037777";

        String requestUrl = BASE_URL + "/activate-sim";
        SimCardActivateRequest request = SimCardActivateRequest.builder()
                .iccid(iccid)
                .customerEmail("customer@example.com")
                .build();

        response = restTemplate.postForEntity(requestUrl, request, String.class);

    }

    @Then("the activation status should be successful")
    public void the_activation_status_should_be_successful() {
        // Assert that the response indicates successful activation
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("successful"));
    }

    @Then("the activation status should be failed")
    public void the_activation_status_should_be_failed() {
        // Assert that the response indicates failed activation
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("failed"));
    }

}