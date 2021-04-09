package Common;

import java.util.List;

public class Case {
    String tag;
    String reportedBy;
    boolean solved;
    List<String> suspects;
    String statementAtFir;
    Information info;
    List<String>victims;
    int severity;

    public Case() {
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public List<String> getSuspects() {
        return suspects;
    }

    public void setSuspects(String suspects) {
        this.suspects.add(suspects);
    }

    public String getStatementAtFir() {
        return statementAtFir;
    }

    public void setStatementAtFir(String statementAtFir) {
        this.statementAtFir = statementAtFir;
    }

    public Information getInfo() {
        return info;
    }

    public void setInfo(Information info) {
        this.info = info;
    }

    public List<String> getVictims() {
        return victims;
    }

    public void setVictims(String victims) {
        this.victims.add(victims);
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }
    //File list is yet to be put
}
