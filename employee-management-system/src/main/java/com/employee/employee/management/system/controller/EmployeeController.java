package com.employee.employee.management.system.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.employee.employee.management.system.model.Employee;
import com.employee.employee.management.system.repository.EmployeeRepository;


@Controller
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
   EmployeeRepository eRepo;

     @GetMapping({"/employeeList"})
    public ModelAndView showEmployees(){
        ModelAndView mav = new ModelAndView("list-employees");
        List<Employee> employees = eRepo.findAll();
        mav.addObject("listOfEmployees", employees);
        return mav;    
    }
     
    @GetMapping("addEmployeeForm")
    public ModelAndView addEmployee(){
    ModelAndView mav = new ModelAndView("new-employee");
    Employee newEmployee = new Employee();
    mav.addObject("employee", newEmployee);
    return mav;

    }
    @PostMapping("saveEmployee")
    public String saveemployee(@ModelAttribute Employee employee){
        eRepo.save(employee);
        return "redirect:/employees/employeeList";
    }

    @GetMapping("updateEmployeeForm")
    public ModelAndView updateEmployeeForm(@RequestParam Long employeeId){
        ModelAndView mav = new ModelAndView("update-employee");
        Employee updateemployee = eRepo.findById(employeeId).get();
        mav.addObject("employeedetails", updateemployee);
        return mav;
    }
     
    @PostMapping("updateEmployee")
    public String updateEmployee(@ModelAttribute Employee employee){
        eRepo.save(employee);
        return "redirect:/employees/employeeList";
    }

    @GetMapping("deleteEmployee")
    public String deleteEmployee(@RequestParam Long employeeId){
        eRepo.deleteById(employeeId);
        return "redirect:/employees/employeeList";
    }


    
}
