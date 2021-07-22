
package com.example.model;

import java.util.Date;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Transaction {

	public abstract double getPrice();
	
	public abstract int getCustomerId();
	
	public abstract Date getTransactionDate();

	static Builder builder() {
		return new AutoValue_Transaction.Builder();
	}

	@AutoValue.Builder
	abstract static class Builder {
		public abstract Builder setPrice(double value);
		
		public abstract Builder setCustomerId(int value);
		
		public abstract Builder setTransactionDate(Date date);

		public abstract Transaction build();
	}
}
