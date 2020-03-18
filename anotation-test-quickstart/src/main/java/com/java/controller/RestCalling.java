package com.java.controller;

import com.java.employee.Employee;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import org.springframework.web.client.RestTemplate;
// This class will call the rest api
public class RestCalling {
        private static final String GET_ALL_URL = "http://localhost:8080/spring/rest/emps";
        private static final String CREATE_EMP_URL = "http://localhost:8080/spring/rest/emp/create";
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
            List<LinkedHashMap> emps = restTemplate.getForObject(GET_ALL_URL, List.class);
            System.out.println(emps.size());
            for(LinkedHashMap map : emps){
                System.out.println("ID="+map.get("id")+",Name="+map.get("name")+",Department Name ="+map.get("department"));;
            }
        }

        private void createEmployee() {
            RestTemplate restTemplate = new RestTemplate();
            Employee emp = new Employee();
            emp.setId(5);
            emp.setName("Ankit");
            emp.setDepartment("IT");
            Employee response = restTemplate.postForObject(CREATE_EMP_URL, emp, Employee.class);
            printEmpData(response);
        }

        private void printEmpData(Employee emp){
            System.out.println("ID="+emp.getId()+",Name="+emp.getName()+",Department Name : = "+emp.getDepartment());
        }
}
