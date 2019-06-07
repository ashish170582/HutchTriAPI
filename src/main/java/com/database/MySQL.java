/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

/**
 *
 * @author Rajat.kumar
 */
public class MySQL {
	@Autowired
	DataSource dataSource;
	private DataSource ds;
	private Connection connection;

	public MySQL() {
		InitialContext initialContext;
		Context context;
		try {
			//connection=dataSource.getConnection();
			initialContext = new InitialContext();
			context = (Context) initialContext.lookup("java:/comp/env");
			// The JDBC Data source that we just created
			ds = (DataSource) context.lookup("connpool");
			try {
				connection = ds.getConnection();
			} catch (Exception e) {
				connection = null;
				connection = ds.getConnection();
			}
		} catch (Exception e) {
			connection = null;
			System.out.println("Exception in MySQL() - " + e.getMessage());
		}
	}

	public ResultSet prepareCall(String cmd) {
		try {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())
					+ " -- HutchTriBeatz  --> proc : " + cmd);
			if (connection.isClosed()) {
				connection = ds.getConnection();
			}
			CallableStatement cstmt = connection.prepareCall(cmd);
			return cstmt.executeQuery(cmd);
		} catch (Exception e) {
			System.out.println("Exception in MySQL.prepareCall(String cmd) - " + e.getMessage() + "\r\n" + cmd);
			return null;
		}
	}

	public ResultSet prepareCallForReceiver(String cmd) throws Exception {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())
				+ " -- HutchTriBeatz  --> proc : " + cmd);
		if (connection.isClosed()) {
			connection = ds.getConnection();
		}
		CallableStatement cstmt = connection.prepareCall(cmd);
		return cstmt.executeQuery(cmd);
	}

	public void executeBatch(String[] queries) {
		try {
			Statement statement = connection.createStatement();
			for (String query : queries) {
				System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())
						+ " -- HutchTriBeatz  --> executeBatch : " + query);
				statement.addBatch(query);
			}
			statement.executeBatch();
			statement.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in MySQL.executeBatch(String[] queries) - " + e.getMessage() + "\r\n" + queries);
		}
	}

	public void executeUpdate(String query) {
		try {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())
					+ " -- HutchTriBeatz  --> executeQuery : " + query);
			PreparedStatement ps = connection.prepareStatement(query);
			ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

	public ResultSet executeQuery(String cmd) {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())
				+ " -- HutchTriBeatz  --> executeQuery : " + cmd);
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(cmd);
			return rs;
		} catch (Exception ex) {
			System.out.println("ex......." + ex);
			ex.printStackTrace();
			return null;
		}
	}

	public void close() {
		try {
			if (connection != null) {
				connection.close(); // return to pool
			}
			connection = null;
		} catch (Exception e) {
			connection = null;
			System.out.println("Exception in MySQL.executeBatch(String[] queries) - " + e.getMessage());
		}
	}
}
