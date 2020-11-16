package logicCalculator;

public class ValidValue {
	public boolean isValid;
	public int value;

	public ValidValue(boolean isValid, int value) {
		this.isValid = isValid;
		this.value = value;
	}

	public ValidValue(boolean isValid) {
		this.isValid = isValid;
	}

	public int getValue() {
		return value;
	}

	public boolean getIsValid() {
		return isValid;
	}
}
