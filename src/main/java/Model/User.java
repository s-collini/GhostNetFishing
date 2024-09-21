import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class User implements Serializable {

    private String username;
    private String password;
    private String mail;

    public User() {

    }

    public User (String username, String password, String mail) {
        this.username = username;
        this.password = password;
        this.mail = mail;

    }

    //Getter und Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }
    public void setMail(String email) {
        this.mail = email;
    }

}


