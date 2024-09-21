package Model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class GhostNets implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) // automatische Zuweisung ID durch DB
    private long id;
    private Double longitude;
    private Double latitude;
    private Double length;
    private Double width;
    private String status;

    // Netzmeldungen
    @ManyToOne
    @JoinColumn (name = "reported_by_id")
    private Form reportForm;

    // Netzbergungen
    @ManyToOne
    @JoinColumn(name = "rescued_by_id")
    private Form rescueForm;

    public GhostNets() {

    }

    public Form getReportForm() {
        return reportForm;
    }

    public void setReportForm(Form reportForm) {
        this.reportForm = reportForm;
    }

    public Form getRescueForm() {
        return rescueForm;
    }

    public void setRescueForm(Form rescueForm) {
        this.rescueForm = rescueForm;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

