package com.redhat.github;

public class CompanySummary implements Comparable<CompanySummary> {

    public String name;

    public int total;
    public long additon;
    public long deletion;
    public long commits;

    public CompanySummary(String name) {
	super();
	this.name = name;
    }

    @Override
    public int compareTo(CompanySummary that) {
	if (this.total > that.total) {
	    return -1;
	} else if (this.total < that.total) {
	    return 1;
	} else if (this.additon + this.deletion > that.additon + that.deletion) {
	    return -1;
	} else if (this.additon + that.deletion < that.additon + that.deletion) {
	    return 1;
	} else {
	    return 0;
	}
    }

    @Override
    public String toString() {
	return "CompanySummary [name=" + name + ", total=" + total + ", additon=" + additon + ", deletion=" + deletion + ", commits=" + commits + "]";
    }

}
