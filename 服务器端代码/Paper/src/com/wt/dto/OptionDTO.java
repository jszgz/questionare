package com.wt.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wt.po.Answer;
import com.wt.po.Ques;

public class OptionDTO {

	private Integer optionid;
	private Ques quesByQuesid;
	private Ques quesByNextquesid;
	private String optioncontent;
	
	
	private Integer nextquesid;

	public Integer getOptionid() {
		return optionid;
	}

	public void setOptionid(Integer optionid) {
		this.optionid = optionid;
	}

	public Ques getQuesByQuesid() {
		return quesByQuesid;
	}

	public void setQuesByQuesid(Ques quesByQuesid) {
		this.quesByQuesid = quesByQuesid;
	}

	public Ques getQuesByNextquesid() {
		return quesByNextquesid;
	}

	public void setQuesByNextquesid(Ques quesByNextquesid) {
		this.quesByNextquesid = quesByNextquesid;
	}

	public String getOptioncontent() {
		return optioncontent;
	}

	public void setOptioncontent(String optioncontent) {
		this.optioncontent = optioncontent;
	}


	public Integer getNextquesid() {
		return nextquesid;
	}

	public void setNextquesid(Integer nextquesid) {
		this.nextquesid = nextquesid;
	}
}
