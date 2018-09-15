package by.epam.task4.entity;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import by.epam.task4.util.type.MethodType;

public class FakeGem extends Gem {

	private MethodType method;
	private GregorianCalendar creationDate;

	public MethodType getMethod() {
		return method;
	}

	public void setMethod(MethodType value) {
		this.method = value;
	}

	public GregorianCalendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(GregorianCalendar value) {
		this.creationDate = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		FakeGem fakeGem = (FakeGem) obj;
		if (method != fakeGem.method) {
			return false;
		}
		if (creationDate == null) {
			if (fakeGem.creationDate != null) {
				return false;
			}
		} else if (!creationDate.equals(fakeGem.creationDate)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + ((creationDate == null)? 0: creationDate.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return super.toString() + ", method=" + method + ", creationDate=\""
				+ new SimpleDateFormat("dd-MM-yyyy").format(creationDate.getTime()) + "\"\n";
	}
}
