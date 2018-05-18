package org.icec.web.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

public class JWTAuthenticationToken implements AuthenticationToken {

    private String token;

    public JWTAuthenticationToken( String token) {
        this.token = token;
    }
 

     

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }




	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return this.getToken();
	}

}