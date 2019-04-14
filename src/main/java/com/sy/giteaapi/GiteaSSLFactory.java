package com.sy.giteaapi;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class GiteaSSLFactory implements SSLFactory{

	
	  public SSLSocketFactory getSSLSocketFactory() {
	      try {
	          SSLContext sslContext = SSLContext.getInstance("SSL");
	          sslContext.init(null, getTrustManagers(), new SecureRandom());
	          return sslContext.getSocketFactory();
	      } catch (Exception e) {
	          throw new RuntimeException(e);
	      }
	  }

	 
	  public TrustManager[] getTrustManagers() {
	      TrustManager[] trustAllCerts = new TrustManager[]{
	              new X509TrustManager() {
	                  @Override
	                  public void checkClientTrusted(X509Certificate[] chain, String authType) {
	                  }

	                  @Override
	                  public void checkServerTrusted(X509Certificate[] chain, String authType) {
	                  }

	                  @Override
	                  public X509Certificate[] getAcceptedIssuers() {
	                      return new X509Certificate[]{};
	                  }
	              }
	      };
	      return trustAllCerts;
	  }
	  
	  public  X509TrustManager getTrustManager() {
		  X509TrustManager trustAllCerts = 
	              new X509TrustManager() {
	                  @Override
	                  public void checkClientTrusted(X509Certificate[] chain, String authType) {
	                  }

	                  @Override
	                  public void checkServerTrusted(X509Certificate[] chain, String authType) {
	                  }

	                  @Override
	                  public X509Certificate[] getAcceptedIssuers() {
	                      return new X509Certificate[]{};
	                  }
	              };
	              
	      return trustAllCerts;
	  }
	  
	  public HostnameVerifier getHostnameVerifier() {
	      HostnameVerifier hostnameVerifier = new HostnameVerifier() {
	          @Override
	          public boolean verify(String s, SSLSession sslSession) {
	              return true;
	          }
	      };
	      return hostnameVerifier;
	  }
}
