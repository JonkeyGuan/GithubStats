package com.redhat.github;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;

import com.redhat.github.ContributorStats.Week;

public class Helper {

    public final static String STATS_URL = "https://api.github.com/repos/openshift/origin/stats/contributors";
    public final static String PROFILE_URL = "https://api.github.com/users";

    public static List<ContributorSummary> buildContributorSummary(List<ContributorStats> list) {
	List<ContributorSummary> result = new ArrayList<>();
	for (ContributorStats stats : list) {
	    ContributorSummary summary = new ContributorSummary();
	    summary.login = stats.author.login;
	    summary.total = stats.total;
	    summary.profileUrl = PROFILE_URL + "/" + summary.login;
	    for (Week week : stats.weeks) {
		summary.commits += week.c;
		summary.additon += week.a;
		summary.deletion += week.d;
	    }
	    result.add(summary);
	    System.out.println(summary);
	}
	return result;
    }

    public static List<CompanySummary> buildCompanySummary(List<ContributorSummary> list) {
	List<CompanySummary> result = null;
	Map<String, CompanySummary> map = new HashMap<>();
	for (ContributorSummary contributor : list) {
	    CompanySummary company = map.get(contributor.company);
	    if (company == null) {
		company = new CompanySummary(contributor.company);
		map.put(company.name, company);
	    }
	    company.total += contributor.total;
	    company.additon += contributor.additon;
	    company.deletion += contributor.deletion;
	    company.commits += contributor.commits;
	}

	result = new ArrayList<CompanySummary>(map.values());
	Collections.sort(result);
	return result;
    }

    public static void exportToCsv(List<CompanySummary> companySummaryList) throws IOException {
	String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
	String fileName = "OpenShift_Company_Contributions_by_" + date + ".csv";
	File file = new File(fileName);

	BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	String line = "Company,Commits,Addition,Deletion\r\n";
	writer.write(line);
	System.out.print(line);

	for (CompanySummary company : companySummaryList) {
	    line = String.format("%s,%s,%s,%s\r\n", StringEscapeUtils.escapeCsv(company.name), company.commits, company.additon, company.deletion);
	    writer.write(line);
	    System.out.print(line);
	}
	writer.close();
	System.out.println("-----------------------------------------------------------");
	System.out.println("output to " + file.getAbsolutePath());
    }
}
