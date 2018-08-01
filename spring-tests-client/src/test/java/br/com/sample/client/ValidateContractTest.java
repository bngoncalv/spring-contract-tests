package br.com.sample.client;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureStubRunner(ids = {"com.javaee:spring-tests:+:stubs:8080"}, workOffline = true)
public class ValidateContractTest {

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void validate() throws Exception {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "http://127.0.0.1:8080/api/v1/customers", String.class);
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(responseEntity.getBody(), equalTo( "[{\"firstName\":\"Michale\",\"lastName\":\"Weston\",\"url\":\"/api/v1/customers/1\"},{\"firstName\":\"Sam\",\"lastName\":\"Axe\",\"url\":\"/api/v1/customers/2\"}]"));
    }
}
