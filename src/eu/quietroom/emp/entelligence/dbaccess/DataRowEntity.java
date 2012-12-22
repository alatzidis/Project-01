package eu.quietroom.emp.entelligence.dbaccess;

import java.util.Date;

public class DataRowEntity extends DBUnit{

	private Date date;
	private String group;
	private int step;
	private double data;
	private String metadata;

	public DataRowEntity() {

	}

	public DataRowEntity(Date date, String group, int step, double data, String metadata) {
		this.date = date;
		this.group = group;
		this.step = step;
		this.data = data;
		this.metadata = metadata;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public double getData() {
		return data;
	}

	public void setData(double data) {
		this.data = data;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

}
