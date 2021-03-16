package emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import emp.entity.Employee;
import emp.service.EmpService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	
	//inject the dao
	@Autowired
	private EmpService empService;

	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		
		
		//get employees from dao
		List<Employee> theEmployees = empService.getEmployees();
		
		//add them to model
		theModel.addAttribute("employees", theEmployees);
		
		return "employees-list";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Employee theEmployee = new Employee();
		
		theModel.addAttribute("employee", theEmployee);
		
		return "employee-save";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("Employee") Employee theEmployee) {
		
		//saving employee with service
		
		empService.saveEmployee(theEmployee);
		
		return "redirect:/employee/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {
		
		//get employee from service
		
		Employee theEmployee = empService.getEmployee(theId);
		
		//set employee as a model attr
		theModel.addAttribute("employee",theEmployee);
		
		//send it to form
		return "employee-save";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") int theId) {
		
		empService.deleteEmployee(theId);
		
		return "redirect:/employee/list";
	}
	
	@GetMapping("/search")
	public String searchEmployees(@RequestParam("theSearchName") String theSearchName,
								Model theModel) {
		
		List<Employee> theEmployees = empService.searchEmployees(theSearchName);
		
		theModel.addAttribute("employees", theEmployees);
		
		return "employees-list";
	}
	
}


