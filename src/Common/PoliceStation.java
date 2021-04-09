package Common;

import java.util.List;

public class PoliceStation {
    Address stationAddress;
    List<Integer> policeMen;

    public PoliceStation() {
    }

    public Address getStationAddress() {
        return stationAddress;
    }

    public void setStationAddress(Address stationAddress) {
        this.stationAddress = stationAddress;
    }

    public List<Integer> getPoliceMen() {
        return policeMen;
    }

    public void setPoliceMen(Integer policeMen) {
        this.policeMen.add(policeMen);
    }
}
