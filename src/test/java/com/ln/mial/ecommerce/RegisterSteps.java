package com.ln.mial.ecommerce;

import com.ln.mial.ecommerce.app.repository.UsuariosRepository;
import com.ln.mial.ecommerce.infraestructure.adapter.UsuariosCrudRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RegisterSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    // Inyectamos el repositorio CRUD directamente para limpiar los datos de prueba
    @Autowired
    private UsuariosCrudRepository usuariosCrudRepository;

    private ResponseEntity<String> response;

    private final String baseUrl = "http://localhost:8082/register";

    /**
     * Este Hook se ejecuta ANTES de cada escenario de Cucumber.
     * Eliminará al usuario de prueba si ya existe para evitar conflictos de email duplicado.
     */
    @Before
    public void cleanUpBeforeScenario() {
        usuariosCrudRepository.findByEmail("juan_cucumber@gmail.com")
                .ifPresent(usuario -> usuariosCrudRepository.delete(usuario));
    }

    @Given("the register application is up")
    public void the_register_application_is_up() {
        assertNotNull(restTemplate);
    }

    @When("I submit the registration form with name {string}, email {string}, password {string} and cellphone {string}")
    public void i_submit_the_registration_form(String name, String email, String password, String cellphone) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("firstName", name);
        map.add("email", email);
        map.add("password", password);
        map.add("cellphone", cellphone);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        response = restTemplate.postForEntity(baseUrl, request, String.class);
    }

    @Then("the response should redirect to {string}")
    public void the_response_should_redirect_to(String expectedRedirectPath) {
        assertEquals(HttpStatus.FOUND, response.getStatusCode());

        URI location = response.getHeaders().getLocation();
        assertNotNull(location);
        assertEquals(expectedRedirectPath, location.getPath());
    }
}