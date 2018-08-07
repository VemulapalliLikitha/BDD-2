package createAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.cap.dao.AccountDaoImpl;
import org.cap.dao.IAccountDao;
import org.cap.exception.InvalidCustomer;
import org.cap.model.Account;
import org.cap.model.Address;
import org.cap.model.Customer;
import org.cap.service.AccountServiceImpl;
import org.cap.service.IAccountService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/*import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
*/
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdef {
	private Customer customer;
	private double openingBalance;
	private IAccountService accountService;
	private int accountNo;
	private double amount_withdraw;
	private double amount_deposit;
	@Mock
	private IAccountDao accountDao;
	@Before
	public void  setUp() {
		MockitoAnnotations.initMocks(this);
		customer= new Customer();
		openingBalance=1000;
		//accountDao=new AccountDaoImpl();
		accountService=new AccountServiceImpl(accountDao);
		
	}
	
	@Given("^customer details$")
	public void customer_details() throws Throwable {
	    customer.setFirstName("Tom");
	    customer.setLastName("Jerry");
	    Address address=new Address();
	    address.setDoorNo(12);
	    address.setCity("Chennai");
	    customer.setAddress(address);
	    
	}

	@When("^Valid customer$")
	public void valid_customer() throws Throwable {
	   assertNotNull(customer);
	}

	@When("^valid opening balance$")
	public void valid_opening_balance() throws Throwable {
	   assertTrue(openingBalance>=500);
	}

	@Then("^create new Account$")
	public void create_new_Account() throws Throwable {
		
		Account account=new Account();
		account.setAccountNo(1);
		account.setOpeningBalance(1000);
		account.setCustomer(customer);
		Mockito.when(accountDao.addAccount(account)).thenReturn(true);
		
		
	    Account account2=accountService.createAccount(customer, openingBalance);
	    Mockito.verify(accountDao).addAccount(account);
	    assertNotNull(account2);
	    assertEquals(openingBalance, account2.getOpeningBalance(),0.0);
	    assertEquals(1, account2.getAccountNo());
	}
	 
		
		@Given("^Customer details$")
		public void customer_details1() throws Throwable {
			customer=null;
		} 
		@When("^Invalid Customer$")
		public void invalid_Customer() throws Throwable {
		    assertNull(customer);
		}

		@Then("^throw 'Invalid Customer' error message$")
		public void throw_Invalid_Customer_error_message() throws Throwable {
		   try {
			 accountService.createAccount(customer, 3000);  
		   }
		   catch(Exception e) {
			   
		   }
		} 
	@Given("^Customer details and opening balance$")
		public void customer_details_and_opening_balance() throws Throwable {
		    openingBalance=100;
		}

		@When("^Invalid opening balance$")
		public void invalid_opening_balance() throws Throwable {
		    assertTrue(openingBalance<500);
		}

		@Then("^throw 'Insufficient Balance' error message$")
		public void throw_Insufficient_Balance_error_message() throws Throwable {
			 try {
				 accountService.createAccount(customer, openingBalance);  
			   }
			   catch(Exception e) {
				   
			   }
		}
		@Given("^Account Number$")
		public void account_Number() throws Throwable {
		    accountNo=1001;
		}

		@When("^Valid account Number$")
		public void valid_account_Number() throws Throwable {
		   assertTrue(accountNo>0);
		}

		@Then("^find account details$")
		public void find_account_details() throws Throwable {
			Account account1=new Account();
			account1.setAccountNo(1001);
			account1.setOpeningBalance(3000);
			account1.setCustomer(customer);
			Mockito.when(accountDao.findAccountById(1001)).thenReturn(account1);
			
		   Account account=accountService.findAccountById(accountNo);
		   Mockito.verify(accountDao).findAccountById(1001);
		}
		@Given("^Account number (\\d+) and amount (\\d+)$")
		public void account_number_and_amount(int accNo, int amount) throws Throwable {
		    this.accountNo=accNo;
		    this.amount_withdraw=amount;
		}

		@When("^Find account and do withdraw$")
		public void find_account_and_do_withdraw() throws Throwable {
			Account account1=new Account();
			account1.setAccountNo(1001);
			account1.setOpeningBalance(3000);
			account1.setCustomer(customer);
		
			Mockito.when(accountDao.findAccountById(1001)).thenReturn(account1);
			Mockito.when(accountDao.updateBalance(accountNo, 2000)).thenReturn(account1);
		    Account updatedaccount=accountService.withdraw(accountNo,amount_withdraw);
		    Mockito.verify(accountDao).findAccountById(1001);
		    Mockito.verify(accountDao).updateBalance(accountNo, 2000);
		    assertEquals(2000, updatedaccount.getOpeningBalance(),0.0);
		}

		@Then("^Update the account details\\.$")
		public void update_the_account_details() throws Throwable {
		    Account account1=new Account();
		    account1.setAccountNo(1001);
			account1.setOpeningBalance(3000);
			account1.setCustomer(customer);
			//Mockito.when(accountDao.updateBalance(accountNo, 2000)).thenReturn(account1);
			 Account updatedaccount=accountService.updateBalance(accountNo,2000);
			  // Mockito.verify(accountDao).updateBalance(accountNo, 2000);
			   assertEquals(2000, updatedaccount.getOpeningBalance(),0.0);
		}
		@Given("^Account Number (\\d+) and Amount (\\d+)$")
		public void account_Number_and_Amount(int accNo, int amount) throws Throwable {
		   this.accountNo=accNo;
		   this.amount_deposit=amount;
		}
		
		@When("^Find account and deposit amount$")
		public void find_account_and_deposit_amount() throws Throwable {
			Account account1=new Account();
			account1.setAccountNo(1001);
			account1.setOpeningBalance(3000);
			account1.setCustomer(customer);
			Mockito.when(accountDao.findAccountById(1001)).thenReturn(account1);
			Mockito.when(accountDao.updateBalance(accountNo, 5000)).thenReturn(account1);
			  Account account= accountService.deposit(accountNo,amount_deposit);
		    Mockito.verify(accountDao).findAccountById(1001);
		    Mockito.verify(accountDao).updateBalance(accountNo, 5000);
		    assertEquals(5000, account.getOpeningBalance(),0.0);
		 
		}

		@Then("^Update the account details after deposit\\.$")
		public void update_the_account_details_after_deposit() throws Throwable {
			 Account account1=new Account();
			    account1.setAccountNo(1001);
				account1.setOpeningBalance(3000);
				account1.setCustomer(customer);
			
				 Account updatedaccount=accountService.updateBalance(accountNo,5000);
				  
				   assertEquals(5000, updatedaccount.getOpeningBalance(),0.0);
		}

}
