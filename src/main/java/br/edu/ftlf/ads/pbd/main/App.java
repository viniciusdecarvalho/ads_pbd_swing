package br.edu.ftlf.ads.pbd.main;

import br.edu.ftlf.ads.pbd.bean.UserInfo;

public class App {
	
	private static UserInfo userInfo;
	
	public static UserInfo getUserInfo() {
		return userInfo;
	}
	
	public static void setUserInfo(UserInfo userInfo) {
		App.userInfo = userInfo;
	}
	
}
