package eu.quietroom.emp.entelligence.dbaccess;

import java.util.Date;

public class LogRowEntity extends DBUnit{

	private String entity;
	private int complete;
	private Date date;
	private int error;
	private String comments;

	public LogRowEntity() {

	}

	public LogRowEntity(String entity, int complete, Date date, int error,
			String comments) {
		this.entity = entity;
		this.complete = complete;
		this.date = date;
		this.error = error;
		this.comments = comments;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public int getComplete() {
		return complete;
	}

	public void setComplete(int complete) {
		this.complete = complete;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
