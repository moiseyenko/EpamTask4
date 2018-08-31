package by.epam.task4.util.type;

public enum MethodType {

	CRYSTALLIZATION("Crystallization"), WERNEL_METHOD("Wernel method"), HYDROTHERMAL("Hydrothermal"),
	MOLTEN_SOLVENT("Molten solvent"), ZONE_MELTING_METHOD("Zone melting method"),
	SMOOTHIE_FUSION_METHOD("Smoothie-fusion method"), CZOCHRALSKI_METHOD("Czochralski method");

	private final String value;

	MethodType(String v) {
		value = v;
	}

	public String getValue() {
		return value;
	}

	public static MethodType fromValue(String v) {
		for (MethodType c : MethodType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

}
