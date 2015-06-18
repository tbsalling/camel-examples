package dk.tbsalling.training.rest;

public class Vessel {
    private int mmsi;
    private String name;
    private float latitude;
    private float longitude;
    private float speed;
    private float course;

    public Vessel () {
    }

    public Vessel(int mmsi, String name, float latitude, float longitude, float speed, float course) {
        this.mmsi = mmsi;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.course = course;
    }

    public int getMmsi() {
        return mmsi;
    }

    public void setMmsi(int mmsi) {
        this.mmsi = mmsi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getCourse() {
        return course;
    }

    public void setCourse(float course) {
        this.course = course;
    }
}
