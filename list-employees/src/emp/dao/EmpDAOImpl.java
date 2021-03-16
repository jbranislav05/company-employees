package emp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import emp.entity.Employee;

@Repository
public class EmpDAOImpl implements EmpDAO {

	// inject session factory
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<Employee> getEmployees() {
		
		//  current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// query & sort by address
		Query<Employee> theQuery = 
				currentSession.createQuery("from Employee order by address",
											Employee.class);
		
		// execute
		List<Employee> employees = theQuery.getResultList();
				
		return employees;
	}

	@Override
	public void saveEmployee(Employee theEmployee) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		// save or update
		currentSession.saveOrUpdate(theEmployee);
		
	}

	@Override
	public Employee getEmployee(int theId) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		// read using PK
		Employee theEmployee = currentSession.get(Employee.class, theId);
		
		return theEmployee;
	}

	@Override
	public void deleteEmployee(int theId) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete using PK
		Query theQuery = 
				currentSession.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();		
	}

	@Override
	public List<Employee> searchEmployees(String theSearchName) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery = null;
		

        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // firstName, lastName or address
            theQuery =currentSession.createQuery("from Employee where lower(firstName) like :theName or lower(lastName) like :theName or lower(address) like:theName", Employee.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

        }
        else {
            // else, list all employees
            theQuery =currentSession.createQuery("from Employee", Employee.class);            
        }
        
        List<Employee> employees = theQuery.getResultList();
                
        return employees;
	}

}











