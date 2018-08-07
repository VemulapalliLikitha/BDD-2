package createAccount;

import static org.junit.Assert.*;

import org.cap.dao.IAccountDao;
import org.cap.exception.InvalidBalance;
import org.cap.exception.InvalidCustomer;
import org.cap.model.Account;
import org.cap.model.Address;
import org.cap.model.Customer;
import org.cap.service.AccountServiceImpl;
import org.cap.service.IAccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TestMockAccount {
private IAccountService accountService;
@Mock
private IAccountDao accountDao;
@Before
public void setUp() {
	MockitoAnnotations.initMocks(this);
	accountService=new AccountServiceImpl(accountDao);
}
@Test
public void test_createAccount_with_mock() throws InvalidCustomer, InvalidBalance {
	Customer customer=new Customer();
	customer.setFirstName("likitha");
	customer.setLastName("pinky");
	Address address=new Address();
	address.setDoorNo(12);
	address.setCity("Hyderabad");
	customer.setAddress(address);
	Account account=new Account();
	account.setAccountNo(1);
	account.setOpeningBalance(2000);
	account.setCustomer(customer);
	
	Mockito.when(accountDao.addAccount(account)).thenReturn(true);
	Account new_account=accountService.createAccount(customer, 2000);
	 Mockito.verify(accountDao).addAccount(account);
	  assertEquals( account.getOpeningBalance(),new_account.getOpeningBalance(),0.0);
}

}
