package com.smartbear.saas.sso;

public class IDPFactory
{
	public static IDP getIDPv1()
	{
		return new com.smartbear.saas.sso.impl.v1.IDPImpl();
	}

	public static IDP getIDPv1( String idpURL )
	{
		return new com.smartbear.saas.sso.impl.v1.IDPImpl( idpURL );
	}

	public static void main( String args[] ) throws SSOException
	{
		IDP idp = IDPFactory.getIDPv1();
		User u = idp.getUserInfo( "6accd9ff16714b1685eb7430f16d2b33" );
	}
}
