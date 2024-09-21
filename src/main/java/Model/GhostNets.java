import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class GhostNets implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) // automatische Zuweisung ID durch DB
    private int id;
    private double longitude;
    private double latitude;
    private double length;
    private double width;
    private String status;

    public GhostNets() {

    }

    public GhostNets(int id, double longitude, double latitude, double length, double width, String status) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.length = length;
        this.width = width;
        this.status = status;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

