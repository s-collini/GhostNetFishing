package Controller;

import Model.User;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


// Bean erstellen und Gültigkeitsbereich festlegen
@Named
@ViewScoped
public class UserController implements Serializable {

    @Inject
    User user;
    @Inject
    SupportOptionsController supportOptionsController;

    private List<User> users = new ArrayList<User>();


    //User anlegen
    public UserController() {
        users.add(new User(1, "SusanD", "IHelpGhostNetFishing", "IHelpGhostNetFishing", "susan.parker@outlook.uk"));
        users.add(new User(2, "Dorie", "GhostNets", "GhostNets", "dorie@outlook.uk"));
        users.add(new User(3, "Nemo2", "FindGhostNets", "FindGhostNets", "nemo2@hotmail.uk"));
        users.add(new User(4, "Sarah_R5", "WhdL6z", "WhdL6z", "sarah_R5@icloud.com"));
        users.add(new User(5, "#Flipper", "IgagsGN23", "IgagsGN23", "flipper@gmail.de"));
    }

    //Getter und Setter
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setSupportOptionsController(SupportOptionsController supportOptionsController) {
        this.supportOptionsController = supportOptionsController;
    }

    //Validierung Benutzername und Passwort
    public String login() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        int redirectID = supportOptionsController.getRedirectID();

        if (validateUser(user.getUsername(), user.getPassword())) {
            // Weiterleitung zur Seite basierend auf ID
            
            switch (redirectID) {
                case 2:
                case 3:
                    return "overview.xhtml?faces-redirect=true";
                case 4:
                    return "worldMap.xhtml?faces-redirect=true";
                default:
                    return "index.xhtml?faces-redirect=true";
            }
        } else {
            // Eingaben sind falsch - Ausgabe Fehlermeldung für ungültige Anmeldedaten (Backend)
            String errorMessage = ResourceBundle.getBundle("nachrichten").getString("login.invalidCredentials");
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
            return null; // Bleibt auf der gleichen Seite
        }
    }

    private boolean validateUser(String username, String password) {
        // Vordefinierte User validieren
        return username.equals("SusanD") && password.equals("IHelpGhostNetFishing") ||
                username.equals("Dorie") && password.equals("GhostNets") ||
                username.equals("Nemo2") && password.equals("FindGhostNets") ||
                username.equals("Sarah_R5") && password.equals("WhdL6z") ||
                username.equals("#Flipper") && password.equals("IgagsGN23");
    }

    //Registrierung validieren
        public String register() {
            FacesContext facesContext = FacesContext.getCurrentInstance();

            if (!user.getPassword().equals(user.getRepeatPassword())) {
                String errorMessage = ResourceBundle.getBundle("nachrichten").getString("register.passwordsDoNotMatch");
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
                return null; // Bleibt auf der gleichen Seite

            } else {
                return "login.xhtml?faces-redirect=true";
            }
        }

}
