package aeontanvir.com.mobitourmate.pojo;

/**
 * Created by aeon on 13 Nov, 2016.
 */

public class Tour {

    private int tourId;
    private String tourDestination;
    private float tourBudget;
    private String tourStartDate;
    private String tourEndDate;


    public Tour(){

    }

    public Tour(String tourDestination, float tourBudget, String tourStartDate, String tourEndDate) {
        setTourDestination(tourDestination);
        setTourBudget(tourBudget);
        setTourStartDate(tourStartDate);
        setTourEndDate(tourEndDate);
    }

    public Tour(int tourId, String tourDestination, float tourBudget, String tourStartDate, String tourEndDate) {
        setTourId(tourId);
        setTourDestination(tourDestination);
        setTourBudget(tourBudget);
        setTourStartDate(tourStartDate);
        setTourEndDate(tourEndDate);
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public String getTourDestination() {
        return tourDestination;
    }

    public void setTourDestination(String tourDestination) {
        this.tourDestination = tourDestination;
    }

    public float getTourBudget() {
        return tourBudget;
    }

    public void setTourBudget(float tourBudget) {
        this.tourBudget = tourBudget;
    }

    public String getTourStartDate() {
        return tourStartDate;
    }

    public void setTourStartDate(String tourStartDate) {
        this.tourStartDate = tourStartDate;
    }

    public String getTourEndDate() {
        return tourEndDate;
    }

    public void setTourEndDate(String tourEndDate) {
        this.tourEndDate = tourEndDate;
    }
}
