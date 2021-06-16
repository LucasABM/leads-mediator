package com.abcriativa.mediator.serviveapi;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Used to access the rest API service Encharge.io to manipulates leads.
 */
public class LeadServiceAPIEncharge implements LeadServiceAPI {

    /**
     * Rest API service to create a new leads on Encharge API.
     */
    @Override
    public ResponseEntity<String> newLead(String email, String name, String phone) {
        RestTemplate restTemplate = new RestTemplate();
        String URL = "https://api.encharge.io/v1/people?api_key=mCML1GdrN4vBeFn6o2Gkq44ro";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("email", email);
        map.add("name", name);
        map.add("phone", phone);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(URL, request , String.class);
        System.out.println("leadSavedOnAPI: " + response);
        return response;
    }

    /**
     * Rest API service to create a new leads with a specific API.
     */
    @Override
    public ResponseEntity<String> newLeadWithAPI(String email,
                                                 String name,
                                                 String phone,
                                                 String api,
                                                 String tags) {

        //TODO: 15/06/21 Persist tags on encharge
        RestTemplate restTemplate = new RestTemplate();
        // api vem plantar >> mCML1GdrN4vBeFn6o2Gkq44ro
        String URL = "https://api.encharge.io/v1/people?api_key=" + api;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("email", email);
        map.add("name", name);
        map.add("phone", phone);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(URL, request , String.class);
        System.out.println("leadSavedOnAPI: " + response);
        return response;
    }

}
