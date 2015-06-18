package dk.tbsalling.training.rest;

public class Vessel {
    private final int mmsi;
    private final String name;
    private final float latitude;
    private final float longitude;
    private final float speed;
    private final float course;

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

    public String getName() {
        return name;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getSpeed() {
        return speed;
    }

    public float getCourse() {
        return course;
    }
}
