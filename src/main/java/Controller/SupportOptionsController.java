import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Bean erstellen und Gültigkeitsbereich festlegen
@Named
@ApplicationScoped
public class SupportOptionsController implements Serializable {

    @Inject
    SupportOptions supportOptions;

    private List<SupportOptions> options = new ArrayList<SupportOptions>();
    private int redirectID;

    //Unterstützungsmöglichkeiten auf Startseite in Form einer Tabelle darstellen
    public SupportOptionsController() {
        options.add(new SupportOptions("Geisternetze melden", "fishing.jpg", "Hier können Sie Geisternetze melden, wenn sie eines sehen, bitte melden Sie es umgehend.", 1));
        options.add(new SupportOptions("Überblick Geisternetze", "boat.jpg", "Hier befindet sich eine Tabelle mit allen gemeldeten Geisternetzen, bei denen die Bergung noch bevorsteht, die bereits geborgen wurden oder verschollen sind. Die Datenbank wächst, helfen Sie mit.", 2));
        options.add(new SupportOptions("Geisternetze bergen", "turtle.jpg", "Hier können Sie sich zum Bergen von Geisternetzen melden. Helfen Sie mit und retten Sie Tierleben.", 3));
        options.add(new SupportOptions("Weltkarte: noch zu bergende Geisternetze", "sea.jpg", "Hier können Sie alle noch nicht geborgenen Geisternetze auf einer Weltkarte sehen.", 4));
    }

    public SupportOptions getSupportOptions() {
        return supportOptions;
    }

    public void setSupportOptions(SupportOptions supportOptions) {
        this.supportOptions = supportOptions;
    }

    public List<SupportOptions> getOptions() {
        return options;
    }

    public int getRedirectID() {
        return redirectID;
    }

    public void setRedirectID(int redirectID) {
        this.redirectID = redirectID;
    }

    //Navigation programmieren
    public String navigateToPage(int id) {
    //je nach ID wird entsprechende Seite aufgerufen beim Klicken auf die Schrift
        this.redirectID = id;

        switch (id) {
            case 1:
                return "report.xhtml?faces-redirect=true";
            case 2:
            case 3:
            case 4:
                return "login.xhtml?faces-redirect=true";
            default:
                return "index.xhtml?faces-redirect=true";
        }
    }

}
