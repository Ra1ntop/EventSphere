package com.ra1n.top.apigateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Travkin Andrii
 * @version 18.05.2025
 */
@RestController
@RequestMapping("/api")
public class ApiGatewayController {
    private final RestTemplate restTemplate;
    private final String eventServiceUrl;

    public ApiGatewayController(RestTemplate restTemplate,
                                @Value("${event-service.url}") String eventServiceUrl) {
        this.restTemplate = restTemplate;
        this.eventServiceUrl = eventServiceUrl;
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
}