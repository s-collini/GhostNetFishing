package Model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Form implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    private String phoneNumber;


    @OneToMany(mappedBy = "reportForm")
    private List<GhostNets> reportedGhostNets = new ArrayList<>();

    @OneToMany(mappedBy = "rescueForm")
    private List<GhostNets> rescuedGhostNets = new ArrayList<>();

    //Getter und Setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<GhostNets> getReportedGhostNets() {
        return reportedGhostNets;
    }

    public void setReportedGhostNets(List<GhostNets> reportedGhostNets) {
        this.reportedGhostNets = reportedGhostNets;
    }

    public List<GhostNets> getRescuedGhostNets() {
        return rescuedGhostNets;
    }

    public void setRescuedGhostNets(List<GhostNets> rescuedGhostNets) {
        this.rescuedGhostNets = rescuedGhostNets;
    }
}