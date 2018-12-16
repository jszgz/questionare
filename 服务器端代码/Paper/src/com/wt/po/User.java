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
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "questionaire")
public class User implements java.io.Serializable {

	// Fields

	private Integer userid;
	private String openid;
	private String sessionKey;
	private String avatarUrl;
	private String city;
	private String country;
	private Integer gender;
	private String nickName;
	private String province;
	private Integer credit;
	private Set<Survey> surveies = new HashSet<Survey>(0);
	private Set<Answer> answers = new HashSet<Answer>(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String openid, String sessionKey, String nickName) {
		this.openid = openid;
		this.sessionKey = sessionKey;
		this.nickName = nickName;
	}

	/** full constructor */
	public User(String openid, String sessionKey, String avatarUrl,
			String city, String country, Integer gender, String nickName,
			String province, Integer credit, Set<Survey> surveies,
			Set<Answer> answers) {
		this.openid = openid;
		this.sessionKey = sessionKey;
		this.avatarUrl = avatarUrl;
		this.city = city;
		this.country = country;
		this.gender = gender;
		this.nickName = nickName;
		this.province = province;
		this.credit = credit;
		this.surveies = surveies;
		this.answers = answers;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "userid", unique = true, nullable = false)
	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	@Column(name = "openid", nullable = false)
	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Column(name = "sessionKey", nullable = false)
	public String getSessionKey() {
		return this.sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	@Column(name = "avatarUrl")
	public String getAvatarUrl() {
		return this.avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	@Column(name = "city")
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "country")
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "gender")
	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	@Column(name = "nickName", nullable = false)
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "province")
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "credit")
	public Integer getCredit() {
		return this.credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	public Set<Survey> getSurveies() {
		return this.surveies;
	}

	public void setSurveies(Set<Survey> surveies) {
		this.surveies = surveies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	public Set<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

}