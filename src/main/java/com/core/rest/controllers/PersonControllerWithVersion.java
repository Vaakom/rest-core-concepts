package com.core.rest.controllers;

import com.core.rest.domain.Name;
import com.core.rest.domain.PersonV1;
import com.core.rest.domain.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonControllerWithVersion {
//URL versioning
    @GetMapping(path = "/v1/person")
    public PersonV1 personV1(){
        return new PersonV1("Alpha Bravo");
    }

    @GetMapping(path = "/v2/person")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Alpha", "Bravo"));
    }
//Request param versioning
    @GetMapping(path = "/person/param", params = "version=1")
    public PersonV1 paramV1(){
        return new PersonV1("Alpha Bravo");
    }

    @GetMapping(path = "/person/param", params = "version=2")
    public PersonV2 paramV2(){
        return new PersonV2(new Name("Alpha", "Bravo"));
    }
//Header versioning
    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 headerV1(){
        return new PersonV1("Alpha Bravo");
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 headerV2(){
        return new PersonV2(new Name("Alpha", "Bravo"));
    }
//MIME type versioning
    @GetMapping(path = "/person/producer", produces = "application/rest.cheatsheet-v1+json")
    public PersonV1 producerV1(){
        return new PersonV1("Alpha Bravo");
    }

    @GetMapping(path = "/person/producer", produces = "application/rest.cheatsheet-v2+json")
    public PersonV2 producerV2(){
        return new PersonV2(new Name("Alpha", "Bravo"));
    }
}
