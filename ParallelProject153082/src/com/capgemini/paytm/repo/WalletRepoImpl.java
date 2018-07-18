package com.capgemini.paytm.repo;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.capgemini.paytm.beans.Customer;
import com.capgemini.paytm.beans.Wallet;
import com.capgemini.paytm.exception.InvalidInputException;

public class WalletRepoImpl implements WalletRepo {
/*	Connection con;
	public WalletRepoImpl(){
		
		String url="jdbc:mysql://localhost:3306/test";
		String uid="root";
		String pwd="corp123";
		try{
			this.con=DriverManager.getConnection(url,uid,pwd);
		}catch(SQLException e){
			e.printStackTrace();
		}
	} 
	private Map<String, Customer> data = new HashMap<>();

	public Map<String, Customer> getData() {
		return data;
	}

	public void setData(Map<String, Customer> data) {
		this.data = data;
	}

	Customer cust = new Customer();

	public WalletRepoImpl(Map<String, Customer> data) {
		super();
		this.data = data;
	}
*/
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPA DEMO");
	EntityManager em=emf.createEntityManager();
	EntityTransaction tx=em.getTransaction();

	@Override
	public boolean save(Customer customer) {

		
		tx.begin();
		em.persist(customer);
		tx.commit();
		String mobileNo = customer.getMobileNo();
		if (data.get(mobileNo) == null) {
			data.put(mobileNo, customer);
			return true;
		} else
			return false;
	}

	@Override
	public Customer findOne(String mobileNo) {
		Customer e=new Customer();
		tx.begin();
		em.persist(cust);
		e=em.find(Customer.class,mobileNo);
		tx.commit();
			return e;
	}

	@Override
	public Customer updateBal(Customer customer) {
		// TODO Auto-generated method stub
		EntityManager em1=emf.createEntityManager();
		tx.begin();
		Customer cust1=(Customer)em1.merge(customer);
		em1.persist(cust1);
		tx.commit();
		em1.close();
		return customer;
	}

}
