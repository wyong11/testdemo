package com.sy.giteaapi;

public interface RestApi {	
	public RestApi createBuilder();
	public RestApi addAuthenticator(String type, String... args);
	public RestApi addSSL(SSLFactory ssl);
	public RestApi buildClient();
	public String execute(String host,int port,String uri,String method,String json);
	public String execute2(String host,int port,String uri,String method);
}
