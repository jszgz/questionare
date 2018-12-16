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
 * Option entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "option", catalog = "questionaire")
public class Option implements java.io.Serializable {

	// Fields

	private Integer optionid;
	private Ques quesByQuesid;
	private Ques quesByNextquesid;
	private String optioncontent;
	private Set<Answer> answers = new HashSet<Answer>(0);

	// Constructors

	/** default constructor */
	public Option() {
	}

	/** minimal constructor */
	public Option(Ques quesByQuesid, Ques quesByNextquesid) {
		this.quesByQuesid = quesByQuesid;
		this.quesByNextquesid = quesByNextquesid;
	}

	/** full constructor */
	public Option(Ques quesByQuesid, Ques quesByNextquesid,
			String optioncontent, Set<Answer> answers) {
		this.quesByQuesid = quesByQuesid;
		this.quesByNextquesid = quesByNextquesid;
		this.optioncontent = optioncontent;
		this.answers = answers;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "optionid", unique = true, nullable = false)
	public Integer getOptionid() {
		return this.optionid;
	}

	public void setOptionid(Integer optionid) {
		this.optionid = optionid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quesid", nullable = false)
	public Ques getQuesByQuesid() {
		return this.quesByQuesid;
	}

	public void setQuesByQuesid(Ques quesByQuesid) {
		this.quesByQuesid = quesByQuesid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nextquesid", nullable = false)
	public Ques getQuesByNextquesid() {
		return this.quesByNextquesid;
	}

	public void setQuesByNextquesid(Ques quesByNextquesid) {
		this.quesByNextquesid = quesByNextquesid;
	}

	@Column(name = "optioncontent")
	public String getOptioncontent() {
		return this.optioncontent;
	}

	public void setOptioncontent(String optioncontent) {
		this.optioncontent = optioncontent;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "option")
	public Set<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

}