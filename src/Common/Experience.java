package Common;

import java.util.Vector;

public class Experience {
    //    ArrayList<pair<Date,PoliceStation>> previousPostings;
    Vector<String> CasesInvolved;

    public Experience() {
    }

    public Vector<String> getCasesInvolved() {
        return CasesInvolved;
    }

    public void setCasesInvolved(String casesInvolved) {
        CasesInvolved.add(casesInvolved);
    }
}
