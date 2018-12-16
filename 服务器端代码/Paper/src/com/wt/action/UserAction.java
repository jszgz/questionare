package com.wt.action;

import java.io.Console;
import java.util.List;




import org.json.JSONException;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.wt.po.User;
import com.wt.util.MyHttpUtil;

public class UserAction extends ActionSupport implements DAOAction{

	private static final long serialVersionUID = -7674628282242324590L;
	public String code;
	public User userInfo;
	
	public String login() throws JSONException{
		
		System.out.println(code);
		System.out.println(userInfo.toString());
		String url="https://api.weixin.qq.com/sns/jscode2session?appid=wxca997c6d54f5e41a&secret=88be747111dd7be2a0c75e0270519062&js_code="+code+"&grant_type=authorization_code";
		String response=MyHttpUtil.sendGet(url);
		System.out.println("response is:"+response);
		
		JSONObject res=new JSONObject(response);
		
		
		String openid=res.getString("openid");
		String session_key=res.getString("session_key");
		
		List<User>  ul=userdao.findByOpenid(openid);
		
		if(ul.size()==0){
			userInfo.setOpenid(openid);
			userInfo.setSessionKey(session_key);
			userInfo.setCredit(0);
			userdao.save(userInfo);
		}else{
			User u=ul.get(0);
			userInfo.setCredit(u.getCredit());
			userInfo.setUserid(u.getUserid());
			userInfo.setCredit(u.getCredit());
			
			u.setAnswers(null);
			u.setSurveies(null);
			
			userInfo=u;
			
		}
		return "success";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public User getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}
}
