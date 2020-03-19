package com.java.controller;

import com.java.employee.Employee;
import java.util.Scanner;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
// This class will call the rest api
public class RestCalling {
        private static final String GET_ALL_URL = "http://localhost:8080/spring1/rest/emps";
        private static final String CREATE_EMP_URL = "http://localhost:8080/spring1/rest/emp/create";
        private Scanner sc = new Scanner(System.in);
        public  void callMethods(){
           while (true) {
               int option = 1;
               System.out.println("1.for EmployeeList\n2.For create Employee\n3.for break");
               option = sc.nextInt();
               switch (option) {
                   case 1:
                       getAllEmployee();
                       break;
                   case 2:
                       createEmployee();
                       break;
                   default:
                       return;
               }
           }
        }

        private void getAllEmployee() {
            RestTemplate restTemplate = new RestTemplate();
            System.out.println(restTemplate.getForObject(GET_ALL_URL, String.class));
        }

        private void createEmployee() {
            RestTemplate restTemplate = new RestTemplate();
            Employee emp = new Employee();
            emp.setId(5);
            emp.setName("Ankit");
            emp.setDepartment("IT");
           // Employee response = restTemplate.postForObject(CREATE_EMP_URL, emp, Employee.class);
           // restTemplate.postForObject(CREATE_EMP_URL, emp, String.class);
           // RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            HttpEntity request = new HttpEntity(emp, headers);
            restTemplate.postForObject(CREATE_EMP_URL, request, String.class);

        }
}
