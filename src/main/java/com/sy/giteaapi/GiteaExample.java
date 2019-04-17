package com.sy.giteaapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class GiteaExample {

	public static void main(String[] args) {
		RestApi api = new GiteaRestApi();
		String result = api.createBuilder()
			.addAuthenticator("httpbasic", "wy","123456")
			.addSSL(new GiteaSSLFactory())
			.buildClient()
			//.execute("localhost",3000,"/api/v1/orgs/152011/teams","post","{\"description\":\"test\",\"name\":\"test\",\"permission\":\"read\",\"units\":[\"\"]}");
			//.execute("localhost",3000,"/api/v1/admin/users","post","{\"email\":\"student02@qq.com\",\"username\":\"student02\",\"password\":\"123456\",\"login_name\":\"student02\",\"full_name\":\"student02\"}");
			//.execute("localhost",3000,"/api/v1/admin/users/student02/repos","post","{\"descripotion\":\"first repo\",\"name\":\"student02\",\"gitignores\":\"\",\"license\":\"student02\",\"readme\":\"\"}");
			//.execute("localhost",3000,"/api/v1/orgs/152011/teams","post","{\"description\":\"152011 test\",\"name\":\"152011\",\"permission\":\"read\",\"units\":[\"\"]}");
			
			//.execute2("localhost",3000,"/api/v1/teams/6/members/student01","put");
			//.execute2("localhost",3000,"/api/v1/admin/orgs","get");
			.execute2("localhost",3000,"/api/v1/orgs/152011/members","get");
		/*
		 * JSONObject json = JSON.parseObject(result);
		 * System.out.println(json.get("username")); String username =
		 * (String)json.get("username");
		 * 
		 * System.out.println(username);
		 */
		System.out.println(result);
	}

}
 