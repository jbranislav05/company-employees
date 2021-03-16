package emp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import emp.dao.EmpDAO;
import emp.entity.Employee;

@Service
public class EmpServiceImpl implements EmpService {
	
	//inject DAO
	
	@Autowired
	private EmpDAO empDAO;
	

	@Override
	@Transactional
	public List<Employee> getEmployees() {
		
		return empDAO.getEmployees();
	}


	@Override
	@Transactional
	public void saveEmployee(Employee theEmployee) {
		
		empDAO.saveEmployee(theEmployee);
		
	}


	@Override
	@Transactional
	public Employee getEmployee(int theId) {
		
		return empDAO.getEmployee(theId);
	}


	@Override
	@Transactional
	public void deleteEmployee(int theId) {
		
		empDAO.deleteEmployee(theId);
	}


	@Override
	@Transactional
	public List<Employee> searchEmployees(String theSearchName) {
		
		return empDAO.searchEmployees(theSearchName);
	}

}
