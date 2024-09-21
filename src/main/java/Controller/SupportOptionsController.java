package Controller;

import Model.SupportOptions;
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
        options.add(new SupportOptions(1, "Geisternetze melden", "../images/fishing.jpg", "Hier können Sie Geisternetze melden, wenn sie eines sehen, bitte melden Sie es umgehend."));
        options.add(new SupportOptions(2, "Geisternetz-Datenbank", "../images/boat.jpg", "Hier befindet sich eine Datenbank mit allen gemeldeten Geisternetzen, bei denen die Bergung noch bevorsteht, die bereits geborgen wurden oder verschollen sind. Die Datenbank wächst, helfen Sie mit."));
        options.add(new SupportOptions(3, "Geisternetze bergen", "../images/turtle.jpg", "Hier können Sie sich zum Bergen von Geisternetzen melden. Helfen Sie mit und retten Sie Tierleben."));
        options.add(new SupportOptions(4, "Weltkarte: noch zu bergende Geisternetze", "../images/sea.jpg", "Hier können Sie alle noch nicht geborgenen Geisternetze auf einer Weltkarte sehen."));
    }

    public List<SupportOptions> getOptions() {
        return options;
    }

    public int getRedirectID() {
        return redirectID;
    }


    //Navigation programmieren
    public String navigateToPage(int id) {
    /*je nach ID wird entsprechende Seite aufgerufen beim Klicken
        auf die Unterstützungsmöglichkeit*/
        this.redirectID = id;

        switch (id) {
            case 1:
                return "report.xhtml?faces-redirect=true&formType=report";
            case 2:
            case 3:
            case 4:
                return "login.xhtml?faces-redirect=true";
            default:
                return "index.xhtml?faces-redirect=true";
        }
    }

}

