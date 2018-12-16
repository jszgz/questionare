package com.wt.po;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Questype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "questype", catalog = "questionaire")
public class Questype implements java.io.Serializable {

	// Fields

	private Integer typeid;
	private String typename;
	private Set<Ques> queses = new HashSet<Ques>(0);

	// Constructors

	/** default constructor */
	public Questype() {
	}

	/** minimal constructor */
	public Questype(String typename) {
		this.typename = typename;
	}

	/** full constructor */
	public Questype(String typename, Set<Ques> queses) {
		this.typename = typename;
		this.queses = queses;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "typeid", unique = true, nullable = false)
	public Integer getTypeid() {
		return this.typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	@Column(name = "typename", nullable = false)
	public String getTypename() {
		return this.typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "questype")
	public Set<Ques> getQueses() {
		return this.queses;
	}

	public void setQueses(Set<Ques> queses) {
		this.queses = queses;
	}

}