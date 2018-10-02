package main.java.HillelTracker.dataModel;

public class Invoice {

    // Data
    private String number;
    private boolean activity;
    private String status;

    public Invoice(String number, boolean activity){
        this.number=number;
        this.activity=activity;
    }

    public Invoice(String number){
        this.number=number;
        activity=true;
    }

    // Setters and Getters

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        number = number;
    }

    public boolean getActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        activity = activity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    }
