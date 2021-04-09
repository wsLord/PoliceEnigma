package Common;

import java.util.List;

public class Witness extends Person{
    List<String> statements;

    public Witness() {

    }

    public List<String> getStatements() {
        return statements;
    }

    public void setStatements(String statements) {
        this.statements.add(statements);
    }
    //vector<video> videos
    //vector<audio> audios
}