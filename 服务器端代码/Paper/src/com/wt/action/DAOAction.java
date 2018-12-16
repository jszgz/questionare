package com.wt.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wt.po.*;

public interface DAOAction {
	ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	AnswerDAO answerdao=AnswerDAO.getFromApplicationContext(ctx);
	OptionDAO optiondao=OptionDAO.getFromApplicationContext(ctx);
	QuesDAO quesdao=QuesDAO.getFromApplicationContext(ctx);
	QuestypeDAO questypedao=QuestypeDAO.getFromApplicationContext(ctx);
	SurveyDAO surveydao=SurveyDAO.getFromApplicationContext(ctx);
	UserDAO userdao=UserDAO.getFromApplicationContext(ctx);
}
