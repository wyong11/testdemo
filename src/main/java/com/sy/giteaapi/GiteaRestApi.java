package com.sy.giteaapi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.OkHttpClient.Builder;


public class GiteaRestApi implements RestApi {
	
	private String protocol = "http";
	private Builder builder;
	private OkHttpClient client;
	public static final MediaType JSON = MediaType
            .parse("application/json; charset=utf-8");

	
	@Override
	public RestApi createBuilder() {
		this.builder = new OkHttpClient.Builder();
		return this;
	}

	@Override
	public RestApi addAuthenticator(String type, String... args) {
		builder.authenticator(AuthenticatorFactory.createAuthenticator(type,args));
		return this;
	}

	@Override
	public RestApi addSSL(SSLFactory ssl) {
		protocol = "http";
		builder.readTimeout(10000, TimeUnit.MILLISECONDS)
        .writeTimeout(10000, TimeUnit.MILLISECONDS)
        .connectTimeout(10000, TimeUnit.MILLISECONDS)
        .sslSocketFactory(ssl.getSSLSocketFactory(),ssl.getTrustManager())
        .hostnameVerifier(ssl.getHostnameVerifier());
		return this;
	}
	
	@Override
	public RestApi buildClient() {
		this.client = builder.build();
		return this;
	}

	@Override
	public String execute(String host,int port,String uri,String method,String json) {
		String url = formatUrl(host,port,uri);	
		Request request=null;
		RequestBody requestBody = new FormBody.Builder().build();
		switch(method) {
		case "get":
			request = new Request.Builder().url(url).build();
			break;
		case "post":			
			request = new Request.Builder().url(url).post(RequestBody.create(JSON,json)).build();
			break;
		case "delete":			
			request = new Request.Builder().url(url).post(requestBody).build();
			break;
		case "put":			
			request = new Request.Builder().url(url).post(requestBody).build();
			break;
		case "patch":			
			request = new Request.Builder().url(url).post(requestBody).build();
			break;
		}		
		return getResponse(request);		
	}
	
	
	
	public String get(String host,int port,String uri) {
		String url = formatUrl(host,port,uri);
		Request request = new Request.Builder().url(url).build();
		return getResponse(request);
	}
	
	public String post(String host,int port,String uri) {
		String url = formatUrl(host,port,uri);
		RequestBody requestBody = new FormBody.Builder().build();
		Request request = new Request.Builder().url(url).post(requestBody).build();
		return getResponse(request);
	}
	
	public String patch(String host,int port,String uri) {
		String url = formatUrl(host,port,uri);
		RequestBody requestBody = new FormBody.Builder().build();
		Request request = new Request.Builder().url(url).patch(requestBody).build();
		return getResponse(request);
	}
	
	public String delete(String host,int port,String uri) {
		String url = formatUrl(host,port,uri);
		RequestBody requestBody = new FormBody.Builder().build();
		Request request = new Request.Builder().url(url).delete(requestBody).build();
		return getResponse(request);
	}
	
	public String formatUrl(String host,int port,String uri) {
		return String.format("%s://%s:%d%s", protocol,host,port,uri);
	}
	
	public String getResponse(Request request) {
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	 @Override
		public String execute2(String host,int port,String uri,String method) {
			String url = formatUrl(host,port,uri);	
			Request request=null;
			RequestBody requestBody = new FormBody.Builder().build();
			switch(method) {
			case "get":
				request = new Request.Builder().url(url).build();
				break;
			case "post":			
				request = new Request.Builder().url(url).post(requestBody).build();
				break;
			case "delete":			
				request = new Request.Builder().url(url).post(requestBody).build();
				break;
			case "put":			
				request = new Request.Builder().url(url).post(requestBody).build();
				break;
			case "patch":			
				request = new Request.Builder().url(url).post(requestBody).build();
				break;
			}		
			return getResponse(request);		
		}
	 

}
