package com.smartbear.saas.sso;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;

public abstract class IDP
{

	public abstract User getUserInfo( String authToken ) throws SSOException;

	protected JSONObject executeGet( URLConnection connection )
			throws IOException, ParseException
	{
		String line;
		StringBuilder builder = new StringBuilder();
		InputStream is;

		is = connection.getInputStream();

		BufferedReader reader = new BufferedReader( new InputStreamReader( is ) );
		while( ( line = reader.readLine() ) != null )
		{
			builder.append( line );
		}
		return ( JSONObject )new JSONParser().parse( builder.toString() );
	}

	protected void checkError( JSONObject jsonResponse ) throws SSOException
	{
		if( jsonResponse.get( "error" ) != null )
		{
			throw new SSOException( jsonResponse.get( "error" ) + ":"
					+ jsonResponse.get( "error_description" ) );
		}
	}
}
