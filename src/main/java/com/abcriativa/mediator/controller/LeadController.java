package com.abcriativa.mediator.controller;

import com.abcriativa.mediator.domain.Lead;
import com.abcriativa.mediator.repository.LeadRepository;
import com.abcriativa.mediator.serviveapi.LeadServiceAPI;
import com.abcriativa.mediator.serviveapi.LeadServiceAPIEncharge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/leads")
public class LeadController {

    @Autowired
    private LeadRepository leadRepository;

    LeadServiceAPI leadServiceAPI;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    /**
     * Process Form to Encharge creating a new lead.
     *
     * return see https://httpstatuses.com/
     */
    public ResponseEntity<Object> newLead(@RequestParam MultiValueMap body,
                                  UriComponentsBuilder uriBuilder) {

        // Validating the fields
        String email = (String) body.getFirst("email");
        String firstName = (String) body.getFirst("firstName");
        String phone = (String) body.getFirst("phone");

        if(email == null || email.isEmpty()
               || firstName == null || firstName.isEmpty()
                  || phone == null || phone.isEmpty()) {
            return new ResponseEntity<>(
                    "Field Error: email, firstName and phone cannot be null, empty or blank.",
                    HttpStatus.BAD_REQUEST);
        }

        //Saving data on local database
        Lead lead = new Lead(email, firstName, phone);
        Lead leadSavedOnDB = this.leadRepository.save(lead);

        //Access the rest API service to create a new leads.
        this.leadServiceAPI = new LeadServiceAPIEncharge();
        ResponseEntity<String> response = leadServiceAPI.newLead(email, firstName, phone);
        if(response.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>(
                    "Autoresponder API with some Error...",
                    HttpStatus.BAD_REQUEST);
        }

        // Redirect to thank you page if all is ok with API call
        // Reference: https://fullstackdeveloper.guru/2021/03/12/how-to-redirect-to-an-external-url-from-spring-boot-rest-controller/
        String thankYouPage = "https://vemplantar.com/brunopalma/obrigado";
        if(response.getStatusCode() == HttpStatus.OK) {
            // REDIRECT JUST WORK IF WE USE HttpStatus.FOUND
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(thankYouPage)).build();
        }

        // Redirect to error page if something is not ok with API call
        String errorPage = "https://vemplantar.com/brunopalma/please-try-again";
        return ResponseEntity.status(HttpStatus.SEE_OTHER).location(URI.create(errorPage)).build();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Lead> list(String email){

        if(email == null) {
            return leadRepository.findAll();
        }
        return leadRepository.findByEmail(email);
    }

    @RequestMapping(value = "/redirect", method = RequestMethod.POST)
    public ResponseEntity<Object> redirectToAnotherPage() {

      String page = "https://vemplantar.com/brunopalma/obrigado";

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(page)).build();
    }


    @RequestMapping(value = "/new-with-api", method = RequestMethod.POST)
    /**
     * Process Form to Encharge creating a new lead.
     *
     * return see https://httpstatuses.com/
     */
    public ResponseEntity<Object> newLeadWithAPI(@RequestParam MultiValueMap body,
                                          UriComponentsBuilder uriBuilder) {

        // Validating the fields
        String email = (String) body.getFirst("email");
        String firstName = (String) body.getFirst("firstName");
        String phone = (String) body.getFirst("phone");
        String api = (String) body.getFirst("api");
        String platform = (String) body.getFirst("platform");
        String redirectOk = (String) body.getFirst("redirectOk");
        String redirectError = (String) body.getFirst("redirectError");
        String tags = (String) body.getFirst("tags");

        if(email == null || email.isEmpty()
                || firstName == null || firstName.isEmpty()
                || phone == null || phone.isEmpty()
                || api == null || api.isEmpty()
                || platform == null || platform.isEmpty()
                || redirectOk == null || redirectOk.isEmpty()
                || redirectError == null || redirectError.isEmpty()
                || tags == null || tags.isEmpty() ) {
            return new ResponseEntity<>(
                    "Field Error: email, firstName, phone, api, platform, " +
                            "redirectOk, redirectError, tags cannot be null, empty.",
                    HttpStatus.BAD_REQUEST);
        }

        //Saving data on local database
        Lead lead = new Lead(email, firstName, phone, api, platform, tags);
        Lead leadSavedOnDB = this.leadRepository.save(lead);

        //Access the rest API service to create a new leads.
        if(platform.equalsIgnoreCase("encharge")) {
            this.leadServiceAPI = new LeadServiceAPIEncharge();
        }
        ResponseEntity<String> response = leadServiceAPI.newLeadWithAPI(email, firstName, phone, api, tags);
        if(response.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>(
                    "Autoresponder API with some Error...",
                    HttpStatus.BAD_REQUEST);
        }

        // Redirect to thank you page if all is ok with API call
        // Reference: https://fullstackdeveloper.guru/2021/03/12/how-to-redirect-to-an-external-url-from-spring-boot-rest-controller/
        String thankYouPage = redirectOk;
        if(response.getStatusCode() == HttpStatus.OK) {
            // REDIRECT JUST WORK IF WE USE HttpStatus.FOUND
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(thankYouPage)).build();
        }

        // Redirect to error page if something is not ok with API call
        String errorPage = redirectError;
        return ResponseEntity.status(HttpStatus.SEE_OTHER).location(URI.create(errorPage)).build();
    }
}
