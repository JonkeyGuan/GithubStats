package com.redhat.github;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

public class GithubAdapter {

    private HttpClient httpClient;
    private String authHeader;

    public void initialize(String user, String password) {

	httpClient = HttpClients.createDefault();

	String auth = user + ":" + password;
	byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
	authHeader = "Basic " + new String(encodedAuth);
    }

    @Override
    protected void finalize() throws Throwable {
	super.finalize();
    }

    public <T> T getResource(String url, ResponseHandler<T> handler) throws ClientProtocolException, IOException {
	T result = null;

	HttpGet httpGet = new HttpGet(url);
	httpGet.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

	System.out.println("Executing request " + httpGet.getRequestLine());

	result = httpClient.execute(httpGet, handler);

	return result;
    }

}
