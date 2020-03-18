package com.spring.controller;

import com.spring.model.Employee;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeCrud {
    JdbcTemplate template;
    private int id;
    private String name;
    private String city;
    private String age;
    private String department;
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public int save(Employee p){
        id = p.getId();
        name = p.getName();
        age = p.getAge();
        city = p.getCity();
        department = p.getDepartmentName();
        String sql="INSERT INTO employeedemo.employee(id, name, city, age, departmentname) VALUES('"+id+"','"+name+"','"+city+"','"+age+"','"+department+"')";
        return template.update(sql);
    }
    public int update(Employee p){
        String sql="UPDATE employeedemo.employee set name='"+p.getName()+"', city='"+p.getCity()+"',age="+p.getAge()+",departmentname='"+p.getDepartmentName()+"' where id="+p.getId()+"";
        return template.update(sql);
    }
    public int delete(int id){
        String sql="DELETE FROM employeedemo.employee where id="+id+"";
        return template.update(sql);
    }
    public Employee getEmpById(int id){
        String sql="select * from employeedemo.employee where id=?";
        return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Employee>(Employee.class));
    }
    public List<Employee> getEmployees(){
        return template.query("select * from employeedemo.employee",new RowMapper<Employee>(){
            public Employee mapRow(ResultSet rs, int row) throws SQLException {
                Employee e=new Employee();
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setCity(rs.getString(3));
                e.setAge(rs.getString(4));
                e.setDepartmentName(rs.getString(5));
                return e;
            }
        });
    }
}
