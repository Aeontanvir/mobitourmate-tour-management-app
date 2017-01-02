package aeontanvir.com.mobitourmate.pojo;

/**
 * Created by aeon on 25 Nov, 2016.
 */

public class Photo {
    private int photoId;
    private int tourId;
    private String photoName;
    private String photoTime;

    public Photo() {
    }

    public Photo(int tourId, String photoName, String photoTime) {
        setTourId(tourId);
        setPhotoName(photoName);
        setPhotoTime(photoTime);
    }

    public Photo(int photoId, int tourId, String photoName, String photoTime) {
        setPhotoId(photoId);
        setTourId(tourId);
        setPhotoName(photoName);
        setPhotoTime(photoTime);
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoTime() {
        return photoTime;
    }

    public void setPhotoTime(String photoTime) {
        this.photoTime = photoTime;
    }
}
