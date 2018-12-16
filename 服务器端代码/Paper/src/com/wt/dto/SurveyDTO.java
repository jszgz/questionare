package com.wt.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wt.po.Ques;
import com.wt.po.User;

public class SurveyDTO {
	
	private Integer surveyid;
	private String surveyname;
	private String surveydescription;
	private String surveystarttime;
	private String surveyendtime;
	private String surveyperoration;
	public Integer getSurveyid() {
		return surveyid;
	}
	public void setSurveyid(Integer surveyid) {
		this.surveyid = surveyid;
	}

	public String getSurveyname() {
		return surveyname;
	}
	public void setSurveyname(String surveyname) {
		this.surveyname = surveyname;
	}
	public String getSurveydescription() {
		return surveydescription;
	}
	public void setSurveydescription(String surveydescription) {
		this.surveydescription = surveydescription;
	}
	public String getSurveystarttime() {
		return surveystarttime;
	}
	public void setSurveystarttime(String surveystarttime) {
		this.surveystarttime = surveystarttime;
	}
	public String getSurveyendtime() {
		return surveyendtime;
	}
	public void setSurveyendtime(String surveyendtime) {
		this.surveyendtime = surveyendtime;
	}
	public String getSurveyperoration() {
		return surveyperoration;
	}
	public void setSurveyperoration(String surveyperoration) {
		this.surveyperoration = surveyperoration;
	}	
}
