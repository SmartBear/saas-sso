package com.smartbear.sso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public abstract class IDP {

	public abstract com.smartbear.sso.User getUserInfo(String authToken) throws com.smartbear.sso.SSOException;

	protected JSONObject executeGet(URLConnection connection)
			throws ParseException, IOException {
		String line;
		StringBuilder builder = new StringBuilder();
		InputStream is = null;
		try {
			is = connection.getInputStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			return (JSONObject) new JSONParser().parse(builder.toString());
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	protected void checkError(JSONObject jsonResponse) throws SSOException {
		if (jsonResponse.get("error") != null) {
			throw new SSOException(jsonResponse.get("error") + ":"
					+ jsonResponse.get("error_description"));
		}
	}
}
