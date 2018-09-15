package by.epam.task4.util.type;

public enum PreciousnessType {

	PRECIOUS("Precious"), SEMIPRECIOUS("Semiprecious");

	private String value;

	PreciousnessType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static PreciousnessType fromValue(String v) {
		for (PreciousnessType c : PreciousnessType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}
}
