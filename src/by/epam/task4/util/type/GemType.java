package by.epam.task4.util.type;

public enum GemType {
	GEMS("gems"), PRECIOUS_GEM("precious_gem"), FAKE_GEM("fake_gem"), GEM_ID("gem_id"), VISUAL_PARAMETERS("visual_parameters"), 
	FACES("faces"), MEASURE("measure"), CURRENCY("currency"),
	NAME("name"), ORIGIN("origin"), COLOR("color"), CLARITY("clarity"), WEIGHT("weight"),
	PRICE("price"), PRECIOUSNESS("preciousness"), METHOD("method"), CREATION_DATE("creation_date");
	
	private String value;

	private GemType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
