package Model;

import DAO.GhostNetsDAO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.model.map.*;


import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class MarkerMap implements Serializable {

    private GhostNetsDAO ghostNetsDAO = new GhostNetsDAO();

    private MapModel marker;

    @PostConstruct
    public void init() {
        marker = new DefaultMapModel();
        List<GhostNets> ghostNets = ghostNetsDAO.findAll();

        for (GhostNets net : ghostNets) {
            // Fügt alle Marker hinzu, ausgenommen Geisternetze mit Status "geborgen"
            if (net.getLatitude() != null && net.getLongitude() != null &&
                    !("geborgen".equals(net.getStatus()))) {
                marker.addOverlay(new Marker(new LatLng(net.getLatitude(), net.getLongitude()), net.getStatus()));
            }
        }
    }

    public MapModel getMarker() {
        return marker;
    }
}





