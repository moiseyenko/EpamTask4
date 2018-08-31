package by.epam.task4.util.type;

public enum ColorType {

	COLORLESS("Colorless"), BLUE("Blue"), GREEN("Green"), CHAMPAGNE("Champagne"), PURPLE("Purple"),
	GRAY_METALLIC("Gray metallic"), PINK("Pink"), SKYBLUE("Skyblue");

	private String value;

	ColorType(String v) {
		value = v;
	}

	public String getValue() {
		return value;
	}

	public static ColorType fromValue(String v) {
        for (ColorType c: ColorType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
