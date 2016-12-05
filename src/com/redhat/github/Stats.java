package com.redhat.github;

import java.util.List;

public class Stats {

    public final static void main(String[] args) throws Exception {

	String companyMappingFile = "";
	if (args.length > 0) {
	    companyMappingFile = args[0];
	}

	String user = "";
	if (args.length > 1) {
	    user = args[1];
	}

	String password = "";
	if (args.length > 2) {
	    password = args[2];
	}

	CompanyMapping companyMapping = null;
	if (companyMappingFile.length() > 0) {
	    companyMapping = new CompanyMapping(companyMappingFile);
	}

	GithubAdapter adapter = new GithubAdapter();
	adapter.initialize(user, password);

	List<ContributorStats> contribtorStatsList = adapter.getResource(Helper.STATS_URL, new ContributorStatsHandler());
	System.out.println("-----------------------------------------------------------");

	List<ContributorSummary> contributorSummaryList = Helper.buildContributorSummary(contribtorStatsList);
	System.out.println("-----------------------------------------------------------");

	for (ContributorSummary contributorSummary : contributorSummaryList) {
	    Profile profile = adapter.getResource(contributorSummary.profileUrl, new ProfileHandler());
	    String company = "";
	    if (profile.company != null) {
		if (companyMapping != null) {
		    company = companyMapping.mapCompany(profile.company);
		} else {
		    company = profile.company;
		}
	    } else {
		company  ="No Company Name";
	    }
	    contributorSummary.company = company;
	    System.out.println(contributorSummary.toString());
	}

	System.out.println("-----------------------------------------------------------");
	List<CompanySummary> companySummaryList = Helper.buildCompanySummary(contributorSummaryList);
	Helper.exportToCsv(companySummaryList);
	System.out.println("-------------------------Done------------------------------");

    }
}
