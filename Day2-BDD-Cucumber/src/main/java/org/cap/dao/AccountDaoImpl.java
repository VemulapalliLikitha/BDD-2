package org.cap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.cap.model.Account;



public class AccountDaoImpl implements IAccountDao {

	@Override
	public boolean addAccount(Account account)  {
		String sql="insert into account values(?,?,?)";
		try {
			PreparedStatement pst=getMySqlConnection().prepareStatement(sql);
			pst.setInt(1, account.getAccountNo());
			pst.setDouble(2, account.getOpeningBalance());
			pst.setString(3, account.getCustomer().getFirstName());
			int count=pst.executeUpdate();
			if(count>0)
				return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}
private Connection getMySqlConnection() throws SQLException {
	Connection connection=null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/capsql", "root", "India123");
	}
	catch(ClassNotFoundException ex) {
		ex.printStackTrace();
	}
	
	return connection;
	
}
@Override
public Account findAccountById(int accountNo) {
	return null;
	
	
}
@Override
public Account updateBalance(int accountNo, double amount) {
	
	return null;
}
}
