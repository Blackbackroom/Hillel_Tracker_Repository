package main2.model;

public class Invoice {
    private String id;
    private boolean available;
    private String status;
    private int transportCompanyId;



    public Invoice(String number, boolean available){
        transportCompanyId=0;
        this.id=number;
        this.available=available;
        status=null;
    }

    public Invoice(String number){
        transportCompanyId=0;
        this.id=number;
        available=true;
        status=null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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
