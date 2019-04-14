package com.sy.giteaapi;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public interface SSLFactory {
	SSLSocketFactory getSSLSocketFactory();
	TrustManager[] getTrustManagers();
	X509TrustManager getTrustManager();
	HostnameVerifier getHostnameVerifier();
}
