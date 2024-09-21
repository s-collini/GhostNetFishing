package Model;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ApplicationScoped
public class SupportOptions implements Serializable {

    private int id; //int da überschaubare Anzahl an Optionen, daher ausreichend
    private String name;
    private String image;
    private String description;


    public SupportOptions(int id, String name, String image,String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public SupportOptions() {

    }

    //Getter und Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}