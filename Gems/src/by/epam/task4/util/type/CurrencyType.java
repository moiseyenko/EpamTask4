package by.epam.task4.util.type;

public enum CurrencyType {
	USD, EUR, BYN;

	public static CurrencyType fromValue(String v) {
		return valueOf(v);
	}
}
