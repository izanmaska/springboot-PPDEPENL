//package com.MDL30MBDEPENL.controller;
//
//import org.springframework.web.bind.annotation.RestController;
//import java.util.concurrent.atomic.AtomicLong;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import com.MDL30MBDEPENL.model.HelloWorld;
//
//
//
//@RestController
//public class HelloWorldController {
//    private static final String template = "Hello, %s!";
//    private final AtomicLong counter = new AtomicLong();
//
//    @GetMapping("/greeting")
//    public HelloWorld greeting(@RequestParam(value = "userName", defaultValue = "World") String userName) {
//        return new HelloWorld(counter.incrementAndGet(), String.format(template, userName));
//    }
//}
