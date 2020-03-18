package com.spring.controller;

import com.spring.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeCrud crud;
    @RequestMapping("/empform")
    public String showform(Model m){
        m.addAttribute("command", new Employee());
        return "empform";
    }
    @RequestMapping(value="/save",method = RequestMethod.POST)
    public String save(@ModelAttribute("emp") Employee emp){
        crud.save(emp);
        return "redirect:/viewemp";//will redirect to viewemp request mapping
    }
    @RequestMapping("/viewemp")
    public String viewemp(Model m){
        List<Employee> list=crud.getEmployees();
        m.addAttribute("list",list);
        return "viewemp";
    }
    @RequestMapping(value="/editemp/{id}")
    public String edit(@PathVariable int id, Model m){
        Employee emp=crud.getEmpById(id);
        m.addAttribute("command",emp);
        return "editempform";
    }
    @RequestMapping(value="/editsave",method = RequestMethod.POST)
    public String editsave(@ModelAttribute("emp") Employee emp){
        crud.update(emp);
        return "redirect:/viewemp";
    }
    @RequestMapping(value="/deleteemp/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable int id){
        crud.delete(id);
        return "redirect:/viewemp";
    }
}
