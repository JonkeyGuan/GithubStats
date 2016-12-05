package com.redhat.github;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ContributorStatsHandler implements ResponseHandler<List<ContributorStats>> {

    @Override
    public List<ContributorStats> handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
	StatusLine statusLine = response.getStatusLine();
	HttpEntity entity = response.getEntity();
	if (statusLine.getStatusCode() >= 300) {
	    throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
	}
	if (entity == null) {
	    throw new ClientProtocolException("Response contains no content");
	}

	System.out.println(response.getStatusLine());
	ContentType contentType = ContentType.getOrDefault(entity);
	Charset charset = contentType.getCharset();
	byte[] content = EntityUtils.toByteArray(entity);

	Gson gson = new GsonBuilder().create();
	Reader reader = new InputStreamReader(new ByteArrayInputStream(content), charset);
	
	return gson.fromJson(reader, new TypeToken<List<ContributorStats>>() {}.getType());
    }

}
