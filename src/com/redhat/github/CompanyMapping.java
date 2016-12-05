package com.redhat.github;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CompanyMapping {

    private Map<String, String> mapping = new HashMap<>();

    public CompanyMapping(String file) throws FileNotFoundException, IOException {
	try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	    String line;
	    while ((line = br.readLine()) != null) {
		String[] items = line.split("=");
		if (items != null && items.length >= 2) {
		    String key = items[0];
		    String value = items[1];
		    mapping.put(key, value);
		}
	    }
	}
    }

    public String mapCompany(String rawName) {
	String result = rawName;
	for (Map.Entry<String, String> entry : mapping.entrySet()) {
	    String key = entry.getKey();
	    String value = entry.getValue();
	    if (rawName.indexOf(key) >= 0) {
		result = value;
		break;
	    }
	}
	return result;
    }
}
