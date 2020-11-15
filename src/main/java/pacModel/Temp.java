package pacModel;

import java.text.DecimalFormat;

public class Temp {

	public float value;

	public Temp(String value) {
		this.value = parsStrintToFloat(value); 
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	} 
	
	private float parsStrintToFloat(String value) {
		try {
			return Float.parseFloat(value); 
		}catch (Exception e) {
			System.out.print(e);
			return (float) 0.0;
		}
	}
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.##"); 
		String formattedValue = df.format(this.value); 
		return formattedValue;
	}
}
