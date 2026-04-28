import java.time.LocalTime;
import java.util.ArrayList;

public abstract class Train implements RailInfo {

    int number;
    String name;
    String startStation;
    String destination;
    String departureTime;
    String arrivalTime;
    ArrayList<Station> stops = new ArrayList<>();

    public Train(int number, String name, String startStation,
                 String destination, String departureTime,
                 String arrivalTime) {

        this.number = number;
        this.name = name;
        this.startStation = startStation;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public void addStop(String name, int distance) {
        stops.add(new Station(name, distance));
    }

    public int totalStops() {
        return stops.size();
    }

    public int totalDistance() {
        return stops.get(stops.size() - 1).distanceFromStart;
    }

    public String currentLocation() {
        LocalTime now = LocalTime.now();

        if (now.isBefore(LocalTime.parse(departureTime))) {
            return "Not Started";
        }

        for (int i = 0; i < stops.size(); i++) {
            if (i == stops.size() - 1)
                return destination;

            if (now.isBefore(LocalTime.parse(arrivalTime))) {
                return stops.get(i).name;
            }
        }

        return destination;
    }

    public int passedStops() {
        LocalTime now = LocalTime.now();

        if (now.isBefore(LocalTime.parse(departureTime)))
            return 0;

        int passed = (int) ((double) LocalTime.now().toSecondOfDay()
                / LocalTime.parse(arrivalTime).toSecondOfDay()
                * totalStops());

        if (passed > totalStops())
            passed = totalStops();

        return passed;
    }
}