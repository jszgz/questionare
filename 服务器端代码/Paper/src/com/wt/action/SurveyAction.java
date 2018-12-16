package com.wt.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.wt.po.*;
import com.wt.dto.*;

public class SurveyAction implements DAOAction {

	private SurveyDTO survey;
	private ArrayList<QuesDTO> queses;
	private UserDTO user;
	
	
	private UserDTO userInfo;
	private List surveyList;
	
	
	private Integer surveyid;
	private Survey surveyresponse;
	
	
	
	private Integer userid;
	private ArrayList<AnswerDTO> optionList;
	
	public String completeSurvey(){
		
		User u=new User();
		u.setUserid(userid);
		
		for(int i=0;i<optionList.size();i++)
		{
			Option o=new Option();
			o.setOptionid(optionList.get(i).getOptionid());
			Answer ans=new Answer();
			ans.setOption(o);
			ans.setUser(u);
			ans.setAnswercontent(optionList.get(i).getAnswercontent());
			answerdao.save(ans);
		}
		
		return "success";
	}
	
	public String statistics(){
		
surveyresponse=surveydao.findById(surveyid);
		
surveyresponse.getUser().setAnswers(null);
surveyresponse.getUser().setSurveies(null);
		
		Iterator i=surveyresponse.getQueses().iterator();
		while(i.hasNext())
		{
			Ques q=(Ques) i.next();
			q.setOptionsForNextquesid(null);
			q.setSurvey(null);
			q.getQuestype().setQueses(null);
			Iterator it=q.getOptionsForQuesid().iterator();
			while(it.hasNext())
			{
				Option option=(Option) it.next();
				option.setQuesByNextquesid(null);
				option.setQuesByQuesid(null);
				Iterator subit=option.getAnswers().iterator();
				while(subit.hasNext())
				{
					Answer a=(Answer) subit.next();
					a.setOption(null);
					a.setUser(null);
				}
				
			}
		}
		
		
		return "success";
	}
	
	public String showSurvey(){
		surveyresponse=surveydao.findById(surveyid);
		
		surveyresponse.getUser().setAnswers(null);
		surveyresponse.getUser().setSurveies(null);
		
		Iterator i=surveyresponse.getQueses().iterator();
		while(i.hasNext())
		{
			Ques q=(Ques) i.next();
			q.setOptionsForNextquesid(null);
			q.setSurvey(null);
			q.getQuestype().setQueses(null);
			Iterator it=q.getOptionsForQuesid().iterator();
			while(it.hasNext())
			{
				Option option=(Option) it.next();
				option.setAnswers(null);
				option.setQuesByNextquesid(null);
				option.setQuesByQuesid(null);
			}
		}
		return "success";
	}
	
	public String showAllSurvey(){
		
		User u=new User();
		u.setUserid(userInfo.getUserid());
		
		surveyList=surveydao.findByProperty("user", u);
		for(int i=0;i<surveyList.size();++i)
		{
			Survey s=(Survey) surveyList.get(i);
			s.setQueses(null);
			s.setUser(null);
			surveyList.set(i, s);
			
		}
		return "success";
	}
	
	public String showAllUserSurvey(){

		surveyList=surveydao.findAll();
		for(int i=0;i<surveyList.size();++i)
		{
			Survey s=(Survey) surveyList.get(i);
			s.setQueses(null);
			s.setUser(null);
			surveyList.set(i, s);
			
		}
		return "success";
	}
	
	public String submitnewsurvey(){
		
		Survey sur=new Survey();
		sur.setSurveydescription(survey.getSurveydescription());
		sur.setSurveyendtime(Timestamp.valueOf(survey.getSurveyendtime()));
		sur.setSurveyname(survey.getSurveyname());
		sur.setSurveyperoration(survey.getSurveyperoration());
		sur.setSurveystarttime(new Timestamp(System.currentTimeMillis()));
		sur.setUser(userdao.findById(user.getUserid()));
		
		for(int i=0;i<queses.size();++i)
		{
			QuesDTO dto=queses.get(i);
			Ques qu=new Ques();
			qu.setQuescontent(dto.getQuescontent());
			qu.setQuesorder(dto.getQuesorder());
			qu.setQuesrequired(dto.getQuesrequired()==true? 1:0);
			qu.setQuestype(questypedao.findById(dto.getQuestypeid()));
			qu.setSurvey(sur);
			sur.getQueses().add(qu);

			//Iterator<OptionDTO> it=dto.getOptionsForQuesid().iterator();
			Iterator<HashMap> it=dto.getOptionsForQuesid().iterator();
			while(it.hasNext())
			{
//				OptionDTO opdto=it.next();
//				Option op=new Option();
//				op.setOptioncontent(opdto.getOptioncontent());
//				op.setQuesByQuesid(qu);
//				qu.getOptionsForQuesid().add(op);
				
				Option op=new Option();
				op.setOptioncontent((String) it.next().get("optioncontent"));
				op.setQuesByQuesid(qu);
				qu.getOptionsForQuesid().add(op);
				;
			}
		}
		
		surveydao.save(sur);
		
		return "success";
	}

	public SurveyDTO getSurvey() {
		return survey;
	}

	public void setSurvey(SurveyDTO survey) {
		this.survey = survey;
	}

	public ArrayList<QuesDTO> getQueses() {
		return queses;
	}

	public void setQueses(ArrayList<QuesDTO> queses) {
		this.queses = queses;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}


	public UserDTO getUserInfo() {
		return userInfo;
	}


	public void setUserInfo(UserDTO userInfo) {
		this.userInfo = userInfo;
	}


	public List getSurveyList() {
		return surveyList;
	}


	public void setSurveyList(List surveyList) {
		this.surveyList = surveyList;
	}

	public Integer getSurveyid() {
		return surveyid;
	}

	public void setSurveyid(Integer surveyid) {
		this.surveyid = surveyid;
	}

	public Survey getSurveyresponse() {
		return surveyresponse;
	}

	public void setSurveyresponse(Survey surveyresponse) {
		this.surveyresponse = surveyresponse;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}


	public ArrayList<AnswerDTO> getOptionList() {
		return optionList;
	}


	public void setOptionList(ArrayList<AnswerDTO> optionList) {
		this.optionList = optionList;
	}


}
