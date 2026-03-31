package com.ogulcanonder.chef_mind.ai;

import com.ogulcanonder.chef_mind.dto.request.DtoOllamaRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoOllamaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class OllamaClient {
    private final RestTemplate restTemplate=new RestTemplate();
    @Value("${ollama.url}")
    private String ollamaUrl;

    @Value("${ollama.model}")
    private String model;

    public String generate(String prompt) {

        DtoOllamaRequest request = new DtoOllamaRequest();
        request.setModel(model);
        request.setPrompt(prompt);
        request.setStream(false);

        HttpEntity<DtoOllamaRequest> entity = new HttpEntity<>(request);

        ResponseEntity<DtoOllamaResponse> response =
                restTemplate.postForEntity(
                        ollamaUrl,
                        entity,
                        DtoOllamaResponse.class
                );

        return response.getBody().getResponse();
    }
}
