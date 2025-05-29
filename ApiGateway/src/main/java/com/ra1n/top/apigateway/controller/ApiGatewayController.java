package com.ra1n.top.apigateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author Travkin Andrii
 * @version 18.05.2025
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class ApiGatewayController {
    private final RestTemplate restTemplate;
    private final String eventServiceUrl;
    private final String userServiceUrl;

    public ApiGatewayController(RestTemplate restTemplate,
                                @Value("${event-service.url}") String eventServiceUrl,
                                @Value("${user-service.url}") String userServiceUrl) {
        this.restTemplate = restTemplate;
        this.userServiceUrl = userServiceUrl;
        this.eventServiceUrl = eventServiceUrl;
    }

    @PostMapping("/register")
    public ResponseEntity<?> proxyRegister(@RequestBody Map<String, String> body) {
        log.info(body.toString());
        String url = userServiceUrl + "/api-v1/internal/register";
        return restTemplate.postForEntity(url, body, String.class);
    }

    @PostMapping("/login")
    public ResponseEntity<?> proxyLogin(@RequestBody Map<String, String> body) {
        log.info(body.toString());
        String url = userServiceUrl + "/api-v1/internal/login";
        return restTemplate.postForEntity(url, body, String.class);
    }

    @GetMapping("/events")
    public ResponseEntity<?> proxyGetById(@RequestParam("id") Long id) {
        String url = eventServiceUrl + "/events?id=" + id;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PostMapping("/events")
    public ResponseEntity<?> proxyCreate(@RequestBody Object eventRequestDto) {
        String url = eventServiceUrl + "/events";
        ResponseEntity<String> response = restTemplate.postForEntity(url, eventRequestDto, String.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PutMapping("/events")
    public ResponseEntity<?> proxyUpdate(@RequestParam("id") Long id,
                                         @RequestBody Object eventRequestDto) {
        String url = eventServiceUrl + "/events?id=" + id;
        restTemplate.put(url, eventRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/events")
    public ResponseEntity<?> proxyDelete(@RequestParam("id") Long id) {
        String url = eventServiceUrl + "/events?id=" + id;
        restTemplate.delete(url);
        return ResponseEntity.noContent().build();
    }
}