package pacApp.pacModel;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Measurement")
public class Measurement {
	public Measurement() {
		
	}
	
	public Measurement(float tempValue, int distance) {
		this.date = convertDatumToString();
		this.tempValue = tempValue;
		this.distance = distance;
		setRisk();
	}
	@Id
    @GeneratedValue
	@Column(name = "id", nullable = false)
	long id; 
	@Column(name = "date", length = 64, nullable = false)
	String date;
	@Column(name = "tempValue", length = 64, nullable = false)
	float tempValue;
	@Column(name = "distance", nullable = false)
	int distance;
	@Column(name = "risk", length = 10, nullable = false)
	boolean risk;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public float getTempValue() {
		return tempValue;
	}
	public void setTempValue(float tempValue) {
		this.tempValue = tempValue;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public boolean isRisk() {
		return risk;
	}
	public void setRisk(boolean risk) {
		this.risk = risk;
	}
	public boolean getRisk() {
		return this.risk;
	}
	
	private String convertDatumToString() {
		long currentMs = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ); 
		Date resultdate = new Date(currentMs);
		return sdf.format(resultdate);
	}
	
	private void setRisk() {
		if (this.getTempValue()> 38.7) {
			setRisk(Boolean.TRUE);
		}else {
			setRisk(Boolean.FALSE);
		}
	}
	
	@Override
	public String toString() {
		return "Measurement [id=" + id + ", date=" + date + ", tempValue=" + tempValue + ", distance=" + distance + ", risk=" + risk + "]";
	} 
	
	
}
