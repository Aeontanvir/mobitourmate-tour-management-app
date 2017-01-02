package aeontanvir.com.mobitourmate.pojo;

/**
 * Created by aeon on 25 Nov, 2016.
 */

public class Baggage {
    private int bageId;
    private int tourId;
    private String bageName;
    private int bageNo;

    public Baggage() {
    }

    public Baggage(int tourId, String bageName, int bageNo) {
        setTourId(tourId);
        setBageName(bageName);
        setBageNo(bageNo);
    }
    public Baggage(int bageId, int tourId, String bageName, int bageNo) {
        setBageId(bageId);
        setTourId(tourId);
        setBageName(bageName);
        setBageNo(bageNo);
    }

    public int getBageId() {
        return bageId;
    }

    public void setBageId(int bageId) {
        this.bageId = bageId;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public String getBageName() {
        return bageName;
    }

    public void setBageName(String bageName) {
        this.bageName = bageName;
    }

    public int getBageNo() {
        return bageNo;
    }

    public void setBageNo(int bageNo) {
        this.bageNo = bageNo;
    }
}
