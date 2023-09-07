package com.redhat.support;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;

@ApplicationScoped
public class MyRouteBuilder extends RouteBuilder {

    @Override
    public void configure() {
//        from("platform-http:/platform-http/exception")
//            .throwException(new WebApplicationException("Throwing webapp exception!"));


            rest("/")
                .clientRequestValidation(true)
                .post("/helloworld")
                .param().name("myparam").type(RestParamType.header).required(true).endParam()
                .to("direct:greet");

            from("direct:greet")
                .log("hello world");

// curl -X POST http://localhost:8080/hellow?myparam=value1
    }
}
