package com.wt.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Answer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "answer", catalog = "questionaire")
public class Answer implements java.io.Serializable {

	// Fields

	private Integer answerid;
	private User user;
	private Option option;
	private String answercontent;

	// Constructors

	/** default constructor */
	public Answer() {
	}

	/** minimal constructor */
	public Answer(User user, Option option) {
		this.user = user;
		this.option = option;
	}

	/** full constructor */
	public Answer(User user, Option option, String answercontent) {
		this.user = user;
		this.option = option;
		this.answercontent = answercontent;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "answerid", unique = true, nullable = false)
	public Integer getAnswerid() {
		return this.answerid;
	}

	public void setAnswerid(Integer answerid) {
		this.answerid = answerid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "optionid", nullable = false)
	public Option getOption() {
		return this.option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	@Column(name = "answercontent")
	public String getAnswercontent() {
		return this.answercontent;
	}

	public void setAnswercontent(String answercontent) {
		this.answercontent = answercontent;
	}

}