package com.redhat.github;

import java.util.List;

public class ContributorStats {

    public int total;
    public List<Week> weeks;
    public Author author;

    public static class Week {
	public long w;
	public long a;
	public long d;
	public long c;
    }

    public static class Author {
	public String login;
	public long id;
	public String avatarUrl;
	public String url;
	public String followersUrl;
	public String followingUrl;
	public String gistsUrl;
	public String starredUrl;
	public String subscriptionsUrl;
	public String organizationsUrl;
	public String reposUrl;
	public String eventsUrl;
	public String receivedEventsUrl;
	public String type;
	public boolean siteAdmin;
    }
}
