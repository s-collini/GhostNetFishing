package Controller;

import DAO.PersonDAO;
import DAO.GhostNetsDAO;
import Model.Person;
import Model.GhostNets;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

// Bean erstellen und Gültigkeitsbereich festlegen
@Named
@ViewScoped //damit ID richtig übernommen wird
public class PersonController implements Serializable {

    @Inject
    Person person;

    //Getter und Setter von Person-Model, damit in report.xhtml auf name zugegriffen werden kann
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    ///////////////// für persistente Datenhaltung//////////////////////////////
    @Inject
    private PersonDAO personDAO;

    @Inject
    private GhostNetsDAO ghostNetsDAO;


    private List<Person> person;
    private Person selectedPerson;
    private Person newPerson;  // Für Bearbeitung oder Erstellung

    private List<GhostNets> ghostNet;
    private GhostNets selectedGhostNet;
    private GhostNets newGhostNet;

    private long selectedGhostNetId;
    private String newStatus;

    public long getSelectedGhostNetId() {
        return selectedGhostNetId;
    }

    public void setSelectedGhostNetId(long selectedGhostNetId) {
        this.selectedGhostNetId = selectedGhostNetId;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    @PostConstruct
    public void init() {
        person = personDAO.findAll(); // Lädt alle Datensätze
        ghostNet = ghostNetsDAO.findAll();
        newPerson = new Person();
        newGhostNet = new GhostNets();


        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        //Ruft Parameter "id" aus URL ab (id in URL dank faces-redirect=true&id=" + id;)
        String idParam = ec.getRequestParameterMap().get("id");

        /*Überprüfung ob id in URl vorhanden und in Long-Variable Parsen, falls Umwandlung nicht
        möglich oder id in URL nicht möglich, Fehlermeldungen*/
        if (idParam != null) {
            try {
                selectedGhostNetId = Long.parseLong(idParam);
            } catch (NumberFormatException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Ungültige ID in der URL."));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Keine ID übergeben."));
        }

    }

    //Methode speichert Daten aus Formular
    public void saveGhostForm() {
        // 1. Teildaten (Formular-Daten) speichern

        String name = newPerson.getName();
        String surname = newPerson.getSurname();
        String phoneNumber = newPerson.getPhoneNumber();

        // 2. Restliche Daten direkt im Ghost-Objekt speichern (length, width usw.)
        GhostNets newGhostNets = new GhostNets();
        newGhostNets.setName(name); // Name aus dem Formular
        newGhostNets.setSurname(surname);
        newGhostNets.setPhoneNumber(phoneNumber); // Telefonnummer aus dem Formular

        // Beispiel für die restlichen Werte: Länge, Breite, Longitude und Latitude
        newGhost.setLength(150.0);  // Beispielwert für Länge
        newGhost.setWidth(100.0);   // Beispielwert für Breite
        newGhost.setLongitude(12.34);  // Beispielwert für Längengrad
        newGhost.setLatitude(56.78);   // Beispielwert für Breitengrad

        // 3. Speichern des neuen Ghost-Netzwerks in der Datenbank
        ghostDAO.save(newGhost);

        // 4. Nach dem Speichern die Liste der Ghost-Netzwerke neu laden
        ghostList = ghostDAO.findAll();

        // 5. Formular zurücksetzen, um es für neue Eingaben zu leeren
        newForm = new Person();
    }
    }

    //Methode ändert Formular
    public String editGhostForm(long id) {
        selectedForm = formDAO.findById(id);
        return "rescue?faces-redirect=true&id=" + id;
    }

    public String updateStatus() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        // Suche nach dem Geisternetz mit der gegebenen ID
        GhostNets ghostNet = ghostNetsDAO.findById(selectedGhostNetId);

        // Überprüfe den aktuellen Status und vergleiche ihn mit dem neuen Status
        String currentStatus = ghostNet.getStatus();

        if ("Bergung bevorstehend".equals(currentStatus) && "Bergung bevorstehend".equals(newStatus)) {
            // Fehlermeldung: Kein Wechsel von "Bergung bevorstehend" auf "Bergung bevorstehend"
            String errorMessage = ResourceBundle.getBundle("nachrichten").getString("identicalStatus");
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
            return null;
        }
        // Aktualisiere den Status des Geisternetzes
        ghostNet.setStatus(newStatus);
        ghostNetsDAO.update(ghostNet);

        // Speichere die Form-Daten
        Person person = new Form();
        form.setName(newForm.getName());
        form.setSurname(newForm.getSurname());
        form.setPhoneNumber(newForm.getPhoneNumber());
        formDAO.save(form);

        return rescueSubmit();
    }


    ////////////////////////////////////////////////////////////////////////////////

    private boolean submitted = false;

    private List<String> rescueStatus = new ArrayList<String>();
    private String rescueSelectStatus;

    private List<String> reportStatus = new ArrayList<>();
    private String reportSelectStatus;


    /////////////////////////////// Bergenformular ///////////////////////////////////////////
    //Auswahloptionen Location Bergenformular
    public FormController() {

        //Auswahloptionen Status Bergenformular
        rescueStatus.add("geborgen");
        rescueStatus.add("Bergung bevorstehend");

        //Auswahloptionen Status Meldeformular
        reportStatus.add("gemeldet");
        reportStatus.add("verschollen");

    }

    public List<String> getRescueStatus() {
        return rescueStatus;
    }

    public void setRescueStatus(List<String> recoverStatus) {
        this.rescueStatus = recoverStatus;
    }

    public String getRescueSelectStatus() {
        return rescueSelectStatus;
    }

    public void setRescueSelectStatus(String formSelectStatus) {
        this.rescueSelectStatus = formSelectStatus;
    }

    public String rescueSubmit() {

        FacesContext facesContext = FacesContext.getCurrentInstance();

        if (submitted) {
            return null; // Bereits verarbeitet, keine weitere Aktion erforderlich
        }

        // Infonachricht anzeigen
        String successMessage = ResourceBundle.getBundle("nachrichten").getString("successMessage");
        facesContext.addMessage(null , new FacesMessage(FacesMessage.SEVERITY_INFO, successMessage, null));

        // Markiert als bereits verarbeitet
        submitted = true;

        // Behält die Nachricht über die Weiterleitung bei
        Flash flash = facesContext.getExternalContext().getFlash();
        flash.setKeepMessages(true);

        // Weiterleitung an success.xhtml
        return "success.xhtml?faces-redirect=true";
    }

    /////////////////////////////// Meldenformular ///////////////////////////////////////////

    public List<String> getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(List<String> reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getReportSelectStatus() {
        return reportSelectStatus;
    }

    public void setReportSelectStatus(String reportSelectStatus) {
        this.reportSelectStatus = reportSelectStatus;
    }

    //Meldenformular absenden
    public String reportSubmit() {
        saveGhostForm();

        FacesContext facesContext = FacesContext.getCurrentInstance();

        if (submitted) {
            return null; // Bereits verarbeitet, keine weitere Aktion erforderlich
        }

        // Infonachricht anzeigen
        String successMessage = ResourceBundle.getBundle("nachrichten").getString("successMessage");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, successMessage, null));

        // Markiert als bereits verarbeitet
        submitted = true;

        // Behält die Nachricht über die Weiterleitung bei
        Flash flash = facesContext.getExternalContext().getFlash();
        flash.setKeepMessages(true);

        // Neuladen der aktuellen Seite
        return "report.xhtml?faces-redirect=true";
    }

    //Bei Auswahl Status verschollen - Nachname, Name und Telefonnummer als Pflichtfelder markieren
    public String report() {

        if ("verschollen".equals(reportSelectStatus) &&
                (newForm.getName() == null || newForm.getName().isEmpty() ||
                        newForm.getSurname() == null || newForm.getSurname().isEmpty() ||
                        newForm.getPhoneNumber() == null || newForm.getPhoneNumber().isEmpty())) {
            return null; // Geisternetzstatus nicht verschollen, keine Weiterleitung

        } else {

            saveGhostForm();

            // Erfolgreiche Validierung, ruft reportSubmit() Methode auf
            return reportSubmit();

        }

    }
}
    


