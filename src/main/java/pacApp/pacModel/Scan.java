package pacApp.pacModel;

import java.text.DecimalFormat;

public class Scan {

	private float tempValue;
	private int distanceValue;
	
	public Scan(String scan) {
		parsToTempValue(scan); 
		parsToDistanceValue(scan);
	}
	public float getTempValue() {
		return  tempValue;
	}
	public String getTempValueString (){
		DecimalFormat df = new DecimalFormat("#.##"); 
		String formattedValue = df.format(this.tempValue); 
		return formattedValue;
	}
	public void setTempValue(float tempValue) {
		this.tempValue = tempValue;
	} 
	
	public int getDistanceValue() {
		return this.distanceValue;
	}
	
	public void setDistanceValue(int distanceValue) {
		this.distanceValue = distanceValue;
	}
	
	private void parsToTempValue(String scan) {
		try {
			DecimalFormat df = new DecimalFormat("###");
			float value = Float.parseFloat(scan.split(";")[0]);
			setTempValue(value);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void parsToDistanceValue(String scan) {
		try {
			setDistanceValue(Integer.parseInt(scan.split(";")[1]));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
}
