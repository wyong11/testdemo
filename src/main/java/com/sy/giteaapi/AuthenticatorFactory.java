package com.sy.giteaapi;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class AuthenticatorFactory {
	public static Authenticator createAuthenticator(String type, String ... args) {
		if("httpbasic".equals(type))
			return createHttpBasic(args[0],args[1]);
		else
			return null;
	}
	private static Authenticator createHttpBasic(String name, String password) {
		return new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                String credential = Credentials.basic(name, password);
                return response.request().newBuilder().header("Authorization", credential).build();
            }
        };
	}
}
