package com.redhat.github;

public class ContributorSummary {

    public String login;
    public String company;
    public String profileUrl;

    public int total;
    public long additon;
    public long deletion;
    public long commits;

    @Override
    public String toString() {
	return "ContributorSummary [login=" + login + ", company=" + company + ", profileUrl=" + profileUrl + ", total=" + total + ", additon=" + additon + ", deletion=" + deletion
		+ ", commits=" + commits + "]";
    }

}
