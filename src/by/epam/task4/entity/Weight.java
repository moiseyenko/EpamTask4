package by.epam.task4.entity;

public class Weight {

	private double value;
	private String measure = "ct.";

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getMeasure() {
		return measure;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Weight weight = (Weight) obj;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(weight.value)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) value;
		return result;
	}

	@Override
	public String toString() {
		return value + measure;
	}

}
