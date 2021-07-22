package com.example.rewards;

import org.springframework.boot.test.context.SpringBootTest;

import com.example.model.Transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;


/**
* RewardsApplication test class
*
* @author Veera Gopisetty
*/
@SpringBootTest
public class RewardsControllerTest {

	static List<Transaction> transactions;
	static RewardsController rewardsController;
	 
	@BeforeClass
	public static void setup() {
		 setUpData();
	     rewardsController = new RewardsController(transactions);
	}
	
	@Test
	public void testSuccessTotalRewardsforCustomer() {
		assertEquals(rewardsController.getCustomerTotalRewards(100),4300.0);
	}
	
	@Test
	public void testSuccessRewardsforCustomerPerMonth() {
		List<Double> rewardsListPerMonth = rewardsController.getCustomerRewardsPerMonth(100);
		assertEquals(rewardsListPerMonth.get(0).doubleValue(), 80.0);
		assertEquals(rewardsListPerMonth.get(1).doubleValue(), 2540.0);
		assertEquals(rewardsListPerMonth.get(2).doubleValue(), 1680.0);
		 
	}
	
	private static void setUpData() {
		transactions = new ArrayList<>();
		Calendar cal = new GregorianCalendar();
		Date today = new Date();
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Transaction trans1 = Transaction.builder().setPrice(60).setCustomerId(100).setTransactionDate(cal.getTime()).build();
		Transaction trans2 = Transaction.builder().setPrice(110).setCustomerId(100).setTransactionDate(cal.getTime()).build();
		
		cal.add(Calendar.DAY_OF_MONTH, -35);
		Transaction trans3 = Transaction.builder().setPrice(120).setCustomerId(100).setTransactionDate(cal.getTime()).build();
		Transaction trans4 = Transaction.builder().setPrice(1300).setCustomerId(100).setTransactionDate(cal.getTime()).build();
		
		cal.add(Calendar.DAY_OF_MONTH, -32);
		Transaction trans5 = Transaction.builder().setPrice(80).setCustomerId(100).setTransactionDate(cal.getTime()).build();
		Transaction trans6 = Transaction.builder().setPrice(900).setCustomerId(100).setTransactionDate(cal.getTime()).build();

		transactions.add(trans1);
		transactions.add(trans2);
		transactions.add(trans3);
		transactions.add(trans4);
		transactions.add(trans5);
		transactions.add(trans6);
	}
	
}
