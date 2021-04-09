package Common;

import java.util.ArrayList;
import java.util.Vector;

public class Official extends Person{
    String loginName;
    String password;
    PoliceStation workingPoliceStation;
    String Designation;
    Experience exp;

    public Official() {
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PoliceStation getWorkingPoliceStation() {
        return workingPoliceStation;
    }

    public void setWorkingPoliceStation(PoliceStation workingPoliceStation) {
        this.workingPoliceStation = workingPoliceStation;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public Vector<String> getExp() {
        Vector<String> casesInvolved = exp.getCasesInvolved();
        return casesInvolved;
    }

    public void setExp(String caseInvolved) {
        this.exp.setCasesInvolved(caseInvolved);
    }
}