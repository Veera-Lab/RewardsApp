package com.example.rewards;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import com.example.model.Transaction;

/**
 *Controller for getting rewards of a customer
 * 
 * @author Veera Gopisetty
 */
public class RewardsController {
	List<Transaction> transactions = new ArrayList<>();
	
	public RewardsController(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
	public List<Double> getCustomerRewardsPerMonth(int customerId) {
		Date startDate30DaysBefore = getStartDate(-30);
		Date startDate60DaysBefore = getStartDate(-60);
		Date startDate90DaysBefore = getStartDate(-90);
		
		List<Transaction> customerTransactions = getTransactionsForCustomer(customerId);
		List<Transaction> customerTransLast30Days = customerTransactions.stream().filter(t -> !(t.getTransactionDate().before(startDate30DaysBefore))).collect(Collectors.toList());
	    List<Transaction> customerTransLast60Days = customerTransactions.stream().filter(t -> !(t.getTransactionDate().before(startDate60DaysBefore) || t.getTransactionDate().after(startDate30DaysBefore))).collect(Collectors.toList());
	    List<Transaction> customerTransLast90Days = customerTransactions.stream().filter(t -> !(t.getTransactionDate().before(startDate90DaysBefore) || t.getTransactionDate().after(startDate60DaysBefore))).collect(Collectors.toList());
	    
	    List<Double> rewardsPerMonth = new ArrayList<>();
	    rewardsPerMonth.add(getCustomerRewardsForGivenTransactions(customerId, customerTransLast30Days));
	    rewardsPerMonth.add(getCustomerRewardsForGivenTransactions(customerId, customerTransLast60Days));
	    rewardsPerMonth.add(getCustomerRewardsForGivenTransactions(customerId, customerTransLast90Days));
	    
	    return rewardsPerMonth;
	}
	
	
	public double getCustomerTotalRewards(int customerId) {
		List<Transaction> customerTransactions = getTransactionsForCustomer(customerId);
		double rewards = 0.0;
		for(Transaction transaction: customerTransactions) {
			rewards += calculateRewards(transaction.getPrice());
		}
		
		return rewards;
	}
	
	private double getCustomerRewardsForGivenTransactions(int customerId,List<Transaction> transactions) {
		double rewards = 0.0;
		for(Transaction transaction: transactions) {
			rewards += calculateRewards(transaction.getPrice());
		}
		
		return rewards;
	}
	
	private List<Transaction> getTransactionsForCustomer(int customerId){
		return transactions.stream().filter(transaction -> transaction.getCustomerId() == customerId).collect(Collectors.toList());
	}
	
	private double calculateRewards(double price) {
		double rewards = 0;
		 if (price >=50 && price < 100) {
		        rewards = price-50;
		    } else if (price >100){
		        rewards = (2*(price-100) + 50);
		    }
		    return rewards;
	}
	
	private static Date getStartDate(int amount) {
		Calendar cal = new GregorianCalendar();
		Date today = new Date();
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, amount);
		return cal.getTime();
		
	}
}
