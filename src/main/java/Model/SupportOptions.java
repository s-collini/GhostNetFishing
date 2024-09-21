import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ApplicationScoped
public class SupportOptions implements Serializable {
    private String name;
    private String image;
    private String description;
    private int id;

    public SupportOptions() {

    }

    public SupportOptions(String name, String image,String description, int id) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.id = id;
    }

    //Getter und Setter
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}