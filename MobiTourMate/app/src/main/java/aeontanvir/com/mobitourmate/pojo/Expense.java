package aeontanvir.com.mobitourmate.pojo;

/**
 * Created by aeon on 13 Nov, 2016.
 */

public class Expense {
    private int expnId;
    private int tourId;
    private String expnName;
    private String expnTime;
    private float expnAmount;

    public Expense() {
    }

    public Expense(int tourId, String expnName, String expnTime, float expnAmount) {
        setTourId(tourId);
        setExpnName(expnName);
        setExpnTime(expnTime);
        setExpnAmount(expnAmount);
    }

    public Expense(int expnId, int tourId, String expnName, String expnTime, float expnAmount) {
        setExpnId(expnId);
        setTourId(tourId);
        setExpnName(expnName);
        setExpnTime(expnTime);
        setExpnAmount(expnAmount);
    }

    public int getExpnId() {
        return expnId;
    }

    public void setExpnId(int expnId) {
        this.expnId = expnId;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public String getExpnName() {
        return expnName;
    }

    public void setExpnName(String expnName) {
        this.expnName = expnName;
    }

    public String getExpnTime() {
        return expnTime;
    }

    public void setExpnTime(String expnTime) {
        this.expnTime = expnTime;
    }

    public float getExpnAmount() {
        return expnAmount;
    }

    public void setExpnAmount(float expnAmount) {
        this.expnAmount = expnAmount;
    }
}
