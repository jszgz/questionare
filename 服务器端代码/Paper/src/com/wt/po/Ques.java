package com.wt.po;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Ques entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ques", catalog = "questionaire")
public class Ques implements java.io.Serializable {

	// Fields

	private Integer quesid;
	private Survey survey;
	private Questype questype;
	private Integer quesrequired;
	private Integer quesorder;
	private String quescontent;
	private Set<Option> optionsForQuesid = new HashSet<Option>(0);
	private Set<Option> optionsForNextquesid = new HashSet<Option>(0);

	// Constructors

	/** default constructor */
	public Ques() {
	}

	/** minimal constructor */
	public Ques(Survey survey, Questype questype, Integer quesrequired,
			Integer quesorder, String quescontent) {
		this.survey = survey;
		this.questype = questype;
		this.quesrequired = quesrequired;
		this.quesorder = quesorder;
		this.quescontent = quescontent;
	}

	/** full constructor */
	public Ques(Survey survey, Questype questype, Integer quesrequired,
			Integer quesorder, String quescontent,
			Set<Option> optionsForQuesid, Set<Option> optionsForNextquesid) {
		this.survey = survey;
		this.questype = questype;
		this.quesrequired = quesrequired;
		this.quesorder = quesorder;
		this.quescontent = quescontent;
		this.optionsForQuesid = optionsForQuesid;
		this.optionsForNextquesid = optionsForNextquesid;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "quesid", unique = true, nullable = false)
	public Integer getQuesid() {
		return this.quesid;
	}

	public void setQuesid(Integer quesid) {
		this.quesid = quesid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "surveyid", nullable = false)
	public Survey getSurvey() {
		return this.survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "questypeid", nullable = false)
	public Questype getQuestype() {
		return this.questype;
	}

	public void setQuestype(Questype questype) {
		this.questype = questype;
	}

	@Column(name = "quesrequired", nullable = false)
	public Integer getQuesrequired() {
		return this.quesrequired;
	}

	public void setQuesrequired(Integer quesrequired) {
		this.quesrequired = quesrequired;
	}

	@Column(name = "quesorder", nullable = false)
	public Integer getQuesorder() {
		return this.quesorder;
	}

	public void setQuesorder(Integer quesorder) {
		this.quesorder = quesorder;
	}

	@Column(name = "quescontent", nullable = false)
	public String getQuescontent() {
		return this.quescontent;
	}

	public void setQuescontent(String quescontent) {
		this.quescontent = quescontent;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "quesByQuesid")
	public Set<Option> getOptionsForQuesid() {
		return this.optionsForQuesid;
	}

	public void setOptionsForQuesid(Set<Option> optionsForQuesid) {
		this.optionsForQuesid = optionsForQuesid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "quesByNextquesid")
	public Set<Option> getOptionsForNextquesid() {
		return this.optionsForNextquesid;
	}

	public void setOptionsForNextquesid(Set<Option> optionsForNextquesid) {
		this.optionsForNextquesid = optionsForNextquesid;
	}

}