package by.epam.task4.entity;

import by.epam.task4.util.type.CurrencyType;

public class Price {

	private double value;
	private CurrencyType currency;

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public CurrencyType getCurrency() {
		if (currency == null) {
			return CurrencyType.USD;
		} else {
			return currency;
		}
	}

	public void setCurrency(CurrencyType value) {
		this.currency = value;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass()!=obj.getClass()) {
			return false;
		}
		Price price = (Price) obj;
		if(Double.doubleToLongBits(value)!=Double.doubleToLongBits(price.value)) {
			return false;
		}
		if(currency!=price.currency) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return value+" "+currency.name();
	}
}
