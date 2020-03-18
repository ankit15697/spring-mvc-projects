package com.spring.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.spring.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestServices {
    Map<Integer, Employee> empData = new HashMap<Integer, Employee>();

    @RequestMapping(value = UrlControlles.GET_EMP, method = RequestMethod.GET)
    public @ResponseBody Employee getEmployee(@PathVariable("id") int empId) {
        return empData.get(empId);
    }

    @RequestMapping(value = UrlControlles.GET_ALL_EMP, method = RequestMethod.GET)
    public @ResponseBody List<Employee> getAllEmployees() {
        List<Employee> emps = new ArrayList<Employee>();
        Set<Integer> empIdKeys = empData.keySet();
        for(Integer i : empIdKeys){
            emps.add(empData.get(i));
        }
        return emps;
    }

    @RequestMapping(value = UrlControlles.CREATE_EMP, method = RequestMethod.POST)
    public @ResponseBody Employee createEmployee(@RequestBody Employee emp) {
        empData.put(emp.getId(), emp);
        return emp;
    }
}
