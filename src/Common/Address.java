package Common;

public class Address {
    String district;
    String state;
    String PIN;
    String landmark;

    public Address() {
    }

    public Address(String district, String state, String PIN, String landmark) {
        this.district = district;
        this.state = state;
        this.PIN = PIN;
        this.landmark = landmark;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }
}
