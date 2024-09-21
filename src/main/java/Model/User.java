package Model;

import java.io.Serializable;


public class User implements Serializable {


    private long id;
    private String username;
    private String password;
    private String repeatPassword;
    private String mail;


    public User (long id, String username, String password, String repeatPassword, String mail) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.mail = mail;

    }

    public User() {

    }

    //Getter und Setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getRepeatPassword() { return repeatPassword; }
    public void setRepeatPassword(String repeatPassword) { this.repeatPassword = repeatPassword; }

    public String getMail() {
        return mail;
    }
    public void setMail(String email) {
        this.mail = email;
    }

}


