package test.dro;

public class ZipPairDRO {

    private String key;
    private String description;
    private String inputZips;
    private String expectedZips;   

    public ZipPairDRO(String Key, String description, String input, String expected) {
		this.key=Key;
		this.description=description;
		this.inputZips=input;
		this.expectedZips=expected;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInputZips() {
		return inputZips;
	}

	public void setInputZips(String inputZips) {
		this.inputZips = inputZips;
	}

	public String getExpectedZips() {
		return expectedZips;
	}

	public void setExpectedZips(String expectedZips) {
		this.expectedZips = expectedZips;
	}
	
}