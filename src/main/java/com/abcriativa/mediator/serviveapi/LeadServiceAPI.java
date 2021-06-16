package com.abcriativa.mediator.serviveapi;

import org.springframework.http.ResponseEntity;

/**
 * Used to access any rest API service to manipulates leads.
 */
public interface LeadServiceAPI {

    /**
     * Rest API service to create a new leads on any API.
     */
    ResponseEntity<String> newLead(String email, String name, String phone);

    /**
     * Rest API service to create a new leads with a specific API.
     */
    ResponseEntity<String> newLeadWithAPI(String email,
                                          String name,
                                          String phone,
                                          String api,
                                          String tags);

}
