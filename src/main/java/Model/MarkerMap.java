import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.model.map.*;


import java.io.Serializable;

@Named
@ViewScoped
public class MarkerMap implements Serializable {

    private MapModel marker = new DefaultMapModel();

    public MarkerMap(){
        marker.addOverlay(new Marker(new LatLng(42.453905, 6.444979), "Tyrrhenisches Meer bei Marseille"));
        marker.addOverlay(new Marker(new LatLng(41.605219, 12.026034), "Tyrrhenisches Meer bei Rom"));
        marker.addOverlay(new Marker(new LatLng(43.459216, 7.991838), "Riviera Ligure di Ponente bei Monaco"));
        marker.addOverlay(new Marker(new LatLng(41.303848, 2.657853), "Balearen-Meer bei Barcelona"));
        marker.addOverlay(new Marker(new LatLng(44.870035, 13.439791), "Adriatisches Meer bei Pula"));
        marker.addOverlay(new Marker(new LatLng(39.302714, 1.970494), "Balearen-Meer zwischen Ibiza und Palma"));
        marker.addOverlay(new Marker(new LatLng(45.185283, -2.025693), "Nord Atlantischer Ozean bei Bordeaux"));
        marker.addOverlay(new Marker(new LatLng(40.669499, 23.877450), "Strymonischer Golf bei Stavros"));
    }

    public void setMarker(MapModel marker) {
        this.marker = marker;
    }

    public MapModel getMarker() {
        return marker;
    }
}
