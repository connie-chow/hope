/**
 * 
 */
package com.ffg.rrn.model;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonView;
import com.ffg.rrn.utils.AppConstants;

import lombok.Data;


/**
 * @author FFGRRNTeam
 * 
 */

@Data
@JsonView
public class Event {

	private List<String> residentIdList;
	private Boolean active;
	private String name;
	@NotNull(message = "'Event Name' is Required!")
	@NotEmpty(message = "Event Name is Required!")
	@Size(max = 20,message = "The length of Event Name can not be more than 20.")

	private String dateAdded;
	private String dateModified;
	private String modifiedBy;
	private String serviceCoord;
	private long eventId;
	
	private String residentList;
	private String description;
	private String allResidentsList;
	private String attendees;


	public Event() {
		super();
		//this.residentList = new List<Resident>();
		this.active = true;
		this.name = "my little shop";
		//Date date = new Date();
		this.dateAdded = "";
		this.serviceCoord = "dbadmin1";
	}
	
	
	public Event(String name, String description) {
		super();
		//this.residentList = new List<Resident>();
		this.active = true;
		this.name = name;
		this.description = description;
		//Date date = new Date();
		this.dateAdded = "";
		this.serviceCoord = "dbadmin1";
	}
	
	public Event(String residentList, Boolean active, String eventName, String description, String dateAdded, String serviceCoord) {
		super();
		this.residentList = residentList;
		this.active = active;
		this.name = eventName;
		this.dateAdded = dateAdded;
		this.serviceCoord = serviceCoord;
	}

	public String getServiceCoord() {
		// TODO Auto-generated method stub
		return serviceCoord;
	}

	public void setEventId(long newEventId) {
		// TODO Auto-generated method stub
		eventId = newEventId;
	}
	
	
	public String getResidentList() {
		return residentList;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	
	 public String getDateAdded() {
	 
		// TODO Auto-generated method stub
		return dateAdded;
		//return null;
	}
	

	public Long getEventId() {
		// TODO Auto-generated method stub
		return eventId;
	}

}
