package com.dynamicemployeemanagementsystem;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class DynamicEmployeeManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicEmployeeManagementSystemApplication.class, args);
    }

    //@PostConstruct is used to mark a method that should be run after the bean is initialized.
    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+06:00"));
        System.out.println("==== Timezone set to Dhaka ====");
    }
}
