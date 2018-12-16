package com.wt.po;

import java.sql.Timestamp;
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

import org.hibernate.annotations.OrderBy;

/**
 * Survey entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "survey", catalog = "questionaire")
public class Survey implements java.io.Serializable {

	// Fields

	private Integer surveyid;
	private User user;
	private String surveyname;
	private String surveydescription;
	private Timestamp surveystarttime;
	private Timestamp surveyendtime;
	private String surveyperoration;
	private Set<Ques> queses = new HashSet<Ques>(0);

	// Constructors

	/** default constructor */
	public Survey() {
	}

	/** minimal constructor */
	public Survey(User user, String surveyname, Timestamp surveystarttime,
			Timestamp surveyendtime) {
		this.user = user;
		this.surveyname = surveyname;
		this.surveystarttime = surveystarttime;
		this.surveyendtime = surveyendtime;
	}

	/** full constructor */
	public Survey(User user, String surveyname, String surveydescription,
			Timestamp surveystarttime, Timestamp surveyendtime,
			String surveyperoration, Set<Ques> queses) {
		this.user = user;
		this.surveyname = surveyname;
		this.surveydescription = surveydescription;
		this.surveystarttime = surveystarttime;
		this.surveyendtime = surveyendtime;
		this.surveyperoration = surveyperoration;
		this.queses = queses;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "surveyid", unique = true, nullable = false)
	public Integer getSurveyid() {
		return this.surveyid;
	}

	public void setSurveyid(Integer surveyid) {
		this.surveyid = surveyid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "surveyname", nullable = false)
	public String getSurveyname() {
		return this.surveyname;
	}

	public void setSurveyname(String surveyname) {
		this.surveyname = surveyname;
	}

	@Column(name = "surveydescription")
	public String getSurveydescription() {
		return this.surveydescription;
	}

	public void setSurveydescription(String surveydescription) {
		this.surveydescription = surveydescription;
	}

	@Column(name = "surveystarttime", nullable = false, length = 19)
	public Timestamp getSurveystarttime() {
		return this.surveystarttime;
	}

	public void setSurveystarttime(Timestamp surveystarttime) {
		this.surveystarttime = surveystarttime;
	}

	@Column(name = "surveyendtime", nullable = false, length = 19)
	public Timestamp getSurveyendtime() {
		return this.surveyendtime;
	}

	public void setSurveyendtime(Timestamp surveyendtime) {
		this.surveyendtime = surveyendtime;
	}

	@Column(name = "surveyperoration")
	public String getSurveyperoration() {
		return this.surveyperoration;
	}

	public void setSurveyperoration(String surveyperoration) {
		this.surveyperoration = surveyperoration;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "survey")
	@OrderBy(clause = "quesorder asc")
	public Set<Ques> getQueses() {
		return this.queses;
	}

	public void setQueses(Set<Ques> queses) {
		this.queses = queses;
	}

}