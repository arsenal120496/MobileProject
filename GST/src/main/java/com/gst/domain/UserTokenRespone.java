package com.gst.domain;

/**
 * Created by truonghuuthanh on 4/20/17.
 */
public class UserTokenRespone {
    Account s;
    String tokenHeader;
    String tokenValue;
    boolean checkLogin;

    

	public UserTokenRespone(Account s, String tokenHeader, String tokenValue,boolean checkLogin) {
        this.s = s;
        this.tokenHeader = tokenHeader;
        this.tokenValue = tokenValue;
        this.checkLogin = checkLogin;
    }
    
    public UserTokenRespone(boolean checkLogin, String tokenHeader, String tokenValue) {
        this.checkLogin = checkLogin;
        this.tokenHeader = tokenHeader;
        this.tokenValue = tokenValue;
    }
    
    public UserTokenRespone(Account s) {
        this.s = s;
    }
    
    public UserTokenRespone(boolean s) {
        this.checkLogin = s;
    }

    public UserTokenRespone() {
    }

    
    

    public Account getS() {
		return s;
	}

	public void setS(Account s) {
		this.s = s;
	}

	public String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }
    
    public boolean isCheckLogin() {
		return checkLogin;
	}

	public void setCheckLogin(boolean checkLogin) {
		this.checkLogin = checkLogin;
	}
}
