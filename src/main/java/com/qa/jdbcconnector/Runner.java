package com.qa.jdbcconnector;

import java.sql.SQLException;

import com.qa.jdbcconnector.dao.CustomerDAO;
import com.qa.jdbcconnector.models.Customer;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CustomerDAO customerTable = new CustomerDAO();
		
		try {
			customerTable.create("John", "Smith");
//			customerTable.update(1, new Customer("Jessica", "Example"));
//			customerTable.delete(1);
			customerTable.readAll();
			
			customerTable.closeResources();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
