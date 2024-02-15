package coordinate;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class Coordinates {
    List<Coordinate> coordinates = new ArrayList<Coordinate>();

    public Coordinates(String coordinateString) {
        for (String coordinate : coordinateString.split("-")) {
            String[] splitedCoordinate = coordinate.split(",");
            double x = Double.parseDouble(splitedCoordinate[0]);
            double y = Double.parseDouble(splitedCoordinate[1]);
            Coordinate coordi = new Coordinate(x, y);
            this.coordinates.add(coordi);
        }
    }
    public boolean isStraight() {
        return this.coordinates.size() == 2;
    }

    public double getDistance() {
        List<Double> xs = new ArrayList<Double>();
        List<Double> ys = new ArrayList<Double>();
        for (Coordinate coordinate : this.coordinates) {
            xs.add(coordinate.getX());
            ys.add(coordinate.getY());
        }
    return getDistance(xs, ys);
    }

    private double getDistance(List<Double> xs, List<Double> ys) {
        return sqrt(pow(xs.get(1) - xs.get(0), 2) + pow(ys.get(1) - ys.get(0), 2));
    }
}
