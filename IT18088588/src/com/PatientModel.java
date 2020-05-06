package com;

/**
* @author  Kirushan_Sivanandam_IT18088588
* created: April 26th, 2020 10:00PM
* modified: May 3rd, 2020 04.00PM
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PatientModel {
	private Connection connect() {
		Connection con = null;
		String url = "jdbc:mysql://127.0.0.1:3306/test2";
		String username = "root";
		String password = "root";
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
	
	public boolean checkAvailabilityById(String id) {
		Connection con = connect();
		String query = "select * from patient where id=" + id;
		int count = 0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				count++;
			}

			rs.close();
			stmt.close();
			con.close();

			if (count > 0)
				return true;
			else
				return false;

		} catch (Exception e) {
			return true; // If any error then main function will handle it
		}
	}
	
	// Insert--------------------------------------------------------
	public Map<String, Object> insertPatient(Patient p) {
		Error em = new Error();
		Map<String, Object> data = new HashMap<String, Object>();
		
		Connection con = connect();
		if (con == null) {
			em.setError_code(1);
			em.setError_message("Database Connection Error");
			data.put("Error", em);
			return data;
		}
		
		String query = "insert into test2.patient(name,email,address,contactno) values(?,?,?,?)";

		try {
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, p.getName());
			preparedStmt.setString(2, p.getEmail());
			preparedStmt.setString(3, p.getAddress());
			preparedStmt.setString(4, p.getContactno());
			preparedStmt.executeUpdate();
			con.close();

			em.setError_code(0);
			em.setError_message("Data inserted");
			data.put("Error", em);
			return data;

		}catch (Exception ex) {
			System.out.println(ex);
			em.setError_code(1);
			em.setError_message("Some error occured");
			data.put("Error", em);
			return data;
		}
	}
	
	// Update --------------------------------------------------------
	public Map<String, Object> updatePatient(Patient p) {
		Error em = new Error();
		Map<String, Object> data = new HashMap<String, Object>();
		
		if (checkAvailabilityById(p.getId()) == false) {
			em.setError_code(1);
			em.setError_message("Data not available");
			data.put("Error", em);
			return data;
		}
		
		Connection con = connect();
		if (con == null) {
			em.setError_code(1);
			em.setError_message("Database Connection Error");
			data.put("Error", em);
			return data;
		}
		
		String query = "update patient set name=?,email=?,address=?,contactno=? where id=? ";

		try {
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, p.getName());
			preparedStmt.setString(2, p.getEmail());
			preparedStmt.setString(3, p.getAddress());
			preparedStmt.setString(4, p.getContactno());
			preparedStmt.setString(5, p.getId());
			preparedStmt.executeUpdate();
			con.close();

			em.setError_code(0);
			em.setError_message("Data updated");
			data.put("Error", em);
			return data;

		}catch (Exception ex) {
			System.out.println(ex);
			em.setError_code(1);
			em.setError_message("Some error occured");
			data.put("Error", em);
			return data;
		}
	}
	
	// Delete --------------------------------------------------------
	public Map<String, Object> deletePatient(String id) {
		Error em = new Error();
		Map<String, Object> data = new HashMap<String, Object>();
		
		if (checkAvailabilityById(id) == false) {
			em.setError_code(1);
			em.setError_message("Data not available");
			data.put("Error", em);
			return data;
		}
		
		Connection con = connect();
		if (con == null) {
			em.setError_code(1);
			em.setError_message("Database Connection Error");
			data.put("Error", em);
			return data;
		}
		
		String query = "delete from patient where id=?";

		try {
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, id);
			preparedStmt.executeUpdate();
			con.close();

			em.setError_code(0);
			em.setError_message("Data deleted");
			data.put("Error", em);
			return data;

		}catch (Exception ex) {
			System.out.println(ex);
			em.setError_code(1);
			em.setError_message("Some error occured");
			data.put("Error", em);
			return data;
		}
	}
	
	// Get All Data --------------------------------------------------------
	public Map<String, Object> searchPatient(int id) {
		Error em = new Error();
		Map<String, Object> data = new HashMap<String, Object>();
		Connection con = connect();
		if (con == null) {
			em.setError_code(1);
			em.setError_message("Database Connection Error");
			data.put("Error", em);
			return data;
		}
		
		String query = "select * from patient where id="+ id;
		Patient p = new Patient();
		int count = 0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			

			while (rs.next()) {
				count++;
				p.setId(rs.getString(1));
				p.setName(rs.getString(2));
				p.setEmail(rs.getString(3));
				p.setAddress(rs.getString(4));
				p.setContactno(rs.getString(5));
			}
			con.close();

			em.setError_code(0);
			em.setError_message("Data Recieved");
			data.put("Error", em);
			data.put("NoOfData", count);
			if (count > 0) {
				data.put("Data", p);
			}
			return data;

		}catch (Exception ex) {
			System.out.println(ex);
			em.setError_code(1);
			em.setError_message("Some error occured");
			data.put("Error", em);
			return data;
		}
	}
	
	// Get all Data --------------------------------------------------------
	public Map<String, Object> searchAllPatient() {
		Error em = new Error();
		Map<String, Object> data = new HashMap<String, Object>();
		Connection con = connect();
		if (con == null) {
			em.setError_code(1);
			em.setError_message("Database Connection Error");
			data.put("Error", em);
			return data;
		}
		
		String query = "select * from patient";
		List<Patient> patients = new ArrayList<>();
		int count = 0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			

			while (rs.next()) {
				count++;
				Patient p = new Patient();
				p.setId(rs.getString(1));
				p.setName(rs.getString(2));
				p.setEmail(rs.getString(3));
				p.setAddress(rs.getString(4));
				p.setContactno(rs.getString(5));
				patients.add(p);
			}
			con.close();

			em.setError_code(0);
			em.setError_message("Data Recieved");
			data.put("Error", em);
			data.put("NoOfData", count);
			if (count > 0) {
				data.put("Data", patients);
			}
			return data;

		}catch (Exception ex) {
			System.out.println(ex);
			em.setError_code(1);
			em.setError_message("Some error occured");
			data.put("Error", em);
			return data;
		}
	}

}
