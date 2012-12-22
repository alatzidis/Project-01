package eu.quietroom.emp.entelligence.dbaccess;

import java.util.Date;

public class UnitRowEntity extends DBUnit{

	private Date date;
	private String unit;
	private double mw;
	private String comments;

	public UnitRowEntity() {

	}

	public UnitRowEntity(Date date, String unit, double mw, String comments) {
		this.date = date;
		this.unit = unit;
		this.mw = mw;
		this.comments = comments;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getMw() {
		return mw;
	}

	public void setMw(double mw) {
		this.mw = mw;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
