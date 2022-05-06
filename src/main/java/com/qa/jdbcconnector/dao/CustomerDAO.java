package com.qa.jdbcconnector.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qa.jdbcconnector.models.Customer;

public class CustomerDAO {

	private static String USERNAME = "root";
	private static String PASSWORD = "root";
	private static final String URL = "jdbc:mysql://localhost:3306/jdb?serverTimezone=UTC";
	
	private static Connection connection; 
	private static PreparedStatement statement;
	
	public CustomerDAO() {
		
	}
	
	//Mapper
	public Customer mapToCustomer(ResultSet rs) throws SQLException {
		Customer customer = new Customer();
		
		customer.setId(rs.getInt(1));
		customer.setFirstname(rs.getString(2));
		customer.setSurname(rs.getString(3));
		
		return customer;
	}
	
	// CREATE
	public void create(String firstName, String surname) throws SQLException {
		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		statement = connection.prepareStatement("INSERT INTO customers(first_name, surname) VALUES (?, ?)");
		
		statement.setString(1, firstName);
		statement.setString(2, surname);
		
		statement.execute();
		
	}
	// READ
	public void readAll() throws SQLException {
		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		statement = connection.prepareStatement("SELECT * FROM customers");
		
		ResultSet result = statement.executeQuery();
		
		List<Customer> myCustomers = new ArrayList<Customer>();
		
		while(result.next()) {
			myCustomers.add(mapToCustomer(result));
		}
		for (Customer c : myCustomers) {
			System.out.println(c);
		}
	}
	
	// UPDATE
	public void update(int id, Customer cust) throws SQLException {
		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		statement = connection.prepareStatement("UPDATE customers SET first_name=?, surname=? WHERE id=?");
		
		statement.setString(1, cust.getFirstname());
		statement.setString(2, cust.getSurname());
		statement.setInt(3, id);
		
		statement.execute();
	}
	// DELETE
	public void delete(int id) throws SQLException {
		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		statement = connection.prepareStatement("DELETE FROM customers WHERE id=?");
		
		statement.setInt(1, id);
		
		statement.execute();
	}
	// Utility
	
	public void closeResources() throws SQLException {
		this.connection.close();
		this.statement.close();
	}
}
