package org.example.data;

public class ChangeStatusRequest {

	public Integer applId;
	public Integer staffid;
	public String action;

	public ChangeStatusRequest(Integer applId, Integer staffid, String action) {
		this.applId = applId;
		this.staffid = staffid;
		this.action = action;
	}
}
