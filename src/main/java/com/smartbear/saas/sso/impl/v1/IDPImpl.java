package com.smartbear.sso.impl.v1;

import java.net.URL;
import java.net.URLConnection;
import java.util.Set;

import org.json.simple.JSONObject;

import com.smartbear.sso.IDP;
import com.smartbear.sso.SSOException;
import com.smartbear.sso.User;

public class IDPImpl extends IDP {
	private String userInfoEndpointURL = "https://accounts.ftr.smartbear.com/api/v1/auth/openid-connect/userinfo";

	public IDPImpl(String userInfoEndpointURL) {
		this.userInfoEndpointURL = userInfoEndpointURL;
	}

	public IDPImpl() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserInfo(String authToken) throws SSOException {
		try {
			URL url = new URL(userInfoEndpointURL);
			URLConnection connection = url.openConnection();
			connection.addRequestProperty("Authorization", "Bearer " + authToken);
			connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.addRequestProperty("Accept", "application/json");
			JSONObject userJson = executeGet(connection);
			checkError(userJson);

			String guid = (String) userJson.get("sub");
			String email = (String) userJson.get("email");
			String firstName = (String) userJson.get("first_name");
			String lastName = (String) userJson.get("last_name");		
			Set<String> orgs = ((JSONObject) userJson.get("organizations")).keySet();
			JSONObject access_token_info = (JSONObject) userJson.get("access_token_info");
			
			long expiresInSeconds = (Long) access_token_info.get("expires_in");
			return new User(guid, firstName, lastName, email, expiresInSeconds,orgs);
		} catch (Exception e) {
			throw new SSOException("Error getting user info: " + e.getMessage(), e);
		}

	}
}
