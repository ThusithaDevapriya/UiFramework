package com.uiFrameWork.zone92.projectAutomation.helper.database;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDBQuery {

	public int getEmpSalary(int empId) throws NumberFormatException, SQLException {
		int salary = 0;
		String dbQuery = "SELECT firstname FROM thusitha.myguests where id="+empId;	
		ResultSet result = DatabaseHelper.getResultSet(dbQuery);
		while(result.next()) {
			salary = Integer.parseInt(result.getString("firstname"));
		}
		
		return salary;		
	}	
	
	public List<Employee> getEmployee() throws SQLException {			
		List<Employee> data = new ArrayList<Employee>();
		String dbQuery = "SELECT * FROM thusitha.myguests";
		ResultSet result = DatabaseHelper.getResultSet(dbQuery);
		while (result.next()) {
			Employee emp = new Employee();
			emp.setEmpId(Integer.parseInt(result.getString("id")));
			emp.setSalary(Integer.parseInt(result.getString("firstname")));
			emp.setName(result.getString("lastname"));
			emp.setAddress(result.getString("email"));
			
			data.add(emp);
		}
		return data;
	}
	
	
	public static void main(String[] args) throws NumberFormatException, SQLException {
		ApplicationDBQuery applicationDBQuery = new ApplicationDBQuery();
		int salary = applicationDBQuery.getEmpSalary(2);
		System.out.println(salary);
		List<Employee> listOfData = applicationDBQuery.getEmployee();
		for(Employee data: listOfData) {
			System.out.println("empId is :"+data.getEmpId()+" emp salary is: "+data.getSalary()+"emp name is: "+data.getName());
		}
	}
	
}
