package com.wt.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wt.po.Option;
import com.wt.po.Questype;
import com.wt.po.Survey;

public class QuesDTO {

	private Integer quesid;
	private Survey survey;
	private Integer questypeid;
	private Boolean quesrequired;
	private Integer quesorder;
	private String quescontent;
	private Collection optionsForQuesid=new HashSet<OptionDTO>();
	public Integer getQuesid() {
		return quesid;
	}
	public void setQuesid(Integer quesid) {
		this.quesid = quesid;
	}
	public Survey getSurvey() {
		return survey;
	}
	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	public Integer getQuestypeid() {
		return questypeid;
	}
	public void setQuestypeid(Integer questypeid) {
		this.questypeid = questypeid;
	}
	public Boolean getQuesrequired() {
		return quesrequired;
	}
	public void setQuesrequired(Boolean quesrequired) {
		this.quesrequired = quesrequired;
	}
	public Integer getQuesorder() {
		return quesorder;
	}
	public void setQuesorder(Integer quesorder) {
		this.quesorder = quesorder;
	}
	public String getQuescontent() {
		return quescontent;
	}
	public void setQuescontent(String quescontent) {
		this.quescontent = quescontent;
	}
	public Collection getOptionsForQuesid() {
		return optionsForQuesid;
	}
	public void setOptionsForQuesid(Collection optionsForQuesid) {
		this.optionsForQuesid = optionsForQuesid;
	}

}
