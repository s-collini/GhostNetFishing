package Controller;

import DAO.FormDAO;
import DAO.GhostNetsDAO;
import Model.Form;
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
public class FormController implements Serializable {

    @Inject
    Form form;

    //Getter und Setter, damit in report.xhtml auf name zugegriffen werden kann
    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }


    /////////////////////////////// für persistente Datenhaltung//////////////////////////////
    @Inject
    private FormDAO formDAO;

    @Inject
    private GhostNetsDAO ghostNetsDAO;

    private boolean isReportForm;
    private boolean isRescueForm;

    private List<Form> forms;
    private Form selectedForm;
    private Form newForm;  // Für Bearbeitung oder Erstellung

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
        forms = formDAO.findAll(); // Lädt alle Datensätze
        newForm = new Form();
        ghostNet = ghostNetsDAO.findAll();
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

        String formType = ec.getRequestParameterMap().get("formType");

        if ("report".equals(formType)) {
            isReportForm = true;
            isRescueForm = false;
        } else if ("rescue".equals(formType)) {
            isReportForm = false;
            isRescueForm = true;
        } else {
            // Fehlerbehandlung oder Standardwerte
            isReportForm = false;
            isRescueForm = false;
        }

    }

    public Form getSelectedForm() {
        return selectedForm;
    }

    public void setSelectedForm(Form selectedForm) {
        this.selectedForm = selectedForm;
    }

    public Form getNewForm() {
        return newForm;
    }

    public void setNewForm(Form newForm) {
        this.newForm = newForm;
    }


    public GhostNets getSelectedGhostNet() {
        return selectedGhostNet;
    }

    public void setSelectedGhostNet(GhostNets selectedGhostNet) {
        this.selectedGhostNet = selectedGhostNet;
    }

    public List<GhostNets> getGhostNet() {
        return ghostNet;
    }

    public void setGhostNet(List<GhostNets> ghostNet) {
        this.ghostNet = ghostNet;
    }

    public void setNewGhostNet(GhostNets newGhostNet) {
        this.newGhostNet = newGhostNet;
    }

    public GhostNets getNewGhostNet() {
        return newGhostNet;
    }

    //Methode speichert Daten aus Formular
    public void saveGhostForm() {

        try {
            // Status des GhostNet-Formulars abfragen
            String status = newGhostNet.getStatus();  // Annahme: newGhostNet enthält ein Statusfeld

            // Verhalten für den Status "verschollen"
            if ("verschollen".equals(status)) {
                // Überprüfen, ob die notwendigen Felder für "verschollen" ausgefüllt sind
                if (newForm.getName() != null && !newForm.getName().isEmpty() &&
                        newForm.getSurname() != null && !newForm.getSurname().isEmpty() &&
                        newForm.getPhoneNumber() != null && !newForm.getPhoneNumber().isEmpty()) {

                    // Speichern des Formulars
                    formDAO.save(newForm);

                    Form form = formDAO.findById(newForm.getId());

                    // Überprüfen und Speichern der GhostNets-Daten
                    if (form != null &&
                            (newGhostNet.getLongitude() != null && !newGhostNet.getLongitude().equals(0.0)) &&
                            (newGhostNet.getLatitude() != null && !newGhostNet.getLatitude().equals(0.0)) &&
                            (newGhostNet.getLength() != null && !newGhostNet.getLength().equals(0.0)) &&
                            (newGhostNet.getWidth() != null && !newGhostNet.getWidth().equals(0.0))) {

                        // Formularstatus (Report oder Rescue) prüfen und zuordnen
                        if (isReportForm) {
                            newGhostNet.setReportForm(form);
                        } else if (isRescueForm) {
                            newGhostNet.setRescueForm(form);
                        }

                        ghostNetsDAO.save(newGhostNet);
                    }
                } else {
                    // Wenn die Felder nicht ausgefüllt sind, erfolgt keine Speicherung
                    System.out.println("Für den Status 'verschollen' müssen alle Felder (Name, Vorname, Telefonnummer) ausgefüllt sein.");
                }
            }

            // Verhalten für den Status "gemeldet"
            else if ("gemeldet".equals(status)) {
                // Hier sind die Felder optional, keine Überprüfung notwendig
                formDAO.save(newForm);

                Form form = formDAO.findById(newForm.getId());

                // Überprüfen und Speichern der GhostNets-Daten
                if (form != null &&
                        (newGhostNet.getLongitude() != null && !newGhostNet.getLongitude().equals(0.0)) &&
                        (newGhostNet.getLatitude() != null && !newGhostNet.getLatitude().equals(0.0)) &&
                        (newGhostNet.getLength() != null && !newGhostNet.getLength().equals(0.0)) &&
                        (newGhostNet.getWidth() != null && !newGhostNet.getWidth().equals(0.0))) {

                    // Formularstatus (Report oder Rescue) prüfen und zuordnen
                    if (isReportForm) {
                        newGhostNet.setReportForm(form);
                    } else if (isRescueForm) {
                        newGhostNet.setRescueForm(form);
                    }

                    ghostNetsDAO.save(newGhostNet);
                }
            }

            // Aktualisieren und Zurücksetzen
            forms = formDAO.findAll();
            ghostNet = ghostNetsDAO.findAll();
            newForm = new Form();
            newGhostNet = new GhostNets();

        } catch (Exception e) {
            e.printStackTrace(); // Fehlerbehandlung
        }
    }

    //Methode ändert Formular
    public String editGhostForm(long id) {
        selectedForm = formDAO.findById(id);
        return "rescue?faces-redirect=true&id=" + id + "&formType=rescue";
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
        // Erstelle oder finde das Form-Objekt
        Form rescueForm = new Form();
        rescueForm.setName(newForm.getName());
        rescueForm.setSurname(newForm.getSurname());
        rescueForm.setPhoneNumber(newForm.getPhoneNumber());
        formDAO.save(rescueForm);

        // Setze das Form-Objekt auf dem Geisternetz
        ghostNet.setRescueForm(rescueForm);
        ghostNetsDAO.update(ghostNet);

        return rescueSubmit();
    }

    ////////////////////////////////////////////////////////////////////////////////

    private boolean submitted = false;

    private List<String> rescueStatus = new ArrayList<String>();
    private String rescueSelectStatus;

    private List<String> reportStatus = new ArrayList<>();
    private String reportSelectStatus;


    public FormController() {

        //Auswahloptionen Status Bergenformular
        rescueStatus.add("geborgen");
        rescueStatus.add("Bergung bevorstehend");

        //Auswahloptionen Status Meldeformular
        reportStatus.add("gemeldet");
        reportStatus.add("verschollen");

    }

    /////////////////////////////// Bergenformular ///////////////////////////////////////////

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
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, successMessage, null));

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
        return "report.xhtml?faces-redirect=true&formType=report";
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
    


