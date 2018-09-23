package main.java.HillelTracker.dataModel;

public class Invoice {

    // Data
    private String Number;
    private boolean Activity;
    private String status;
    private int transportCompanyId;

    // Setters and Getters

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public boolean getActivity() {
        return Activity;
    }

    public void setActivity(boolean activity) {
        Activity = activity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTransportCompanyId() {
        return transportCompanyId;
    }

    public void setTransportCompanyId(int transportCompanyId) {
        this.transportCompanyId = transportCompanyId;
    }
}
