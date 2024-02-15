package coordinate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.*;

public class Coordinates {
    List<Point> coordinates = new ArrayList<Point>();
    int width = 0;
    int height = 0;

    public Coordinates(String string) {
        String coordinateString = removeAllBrackets(string);
        for (String coordinate : coordinateString.split("-")) {
            String[] splitedCoordinate = coordinate.split(",");

            System.out.println("splitedCoordinate : " + splitedCoordinate.toString());
            double x = Double.parseDouble(splitedCoordinate[0]);
            double y = Double.parseDouble(splitedCoordinate[1]);
            Point point = new Point(x, y);
            this.coordinates.add(point);
        }
    }
    public boolean isStraight() {
        return this.coordinates.size() == 2;
    }

    public double getDistance() {
        List<Double> xs = new ArrayList<Double>();
        List<Double> ys = new ArrayList<Double>();
        for (Point point : this.coordinates) {
            xs.add(point.getX());
            ys.add(point.getY());
        }
    return getDistance(xs, ys);
    }

    private double getDistance(List<Double> xs, List<Double> ys) {
        return sqrt(pow(xs.get(1) - xs.get(0), 2) + pow(ys.get(1) - ys.get(0), 2));
    }

    public boolean isRectangle() {
        return this.coordinates.size() == 4 && isValidRectangle();

    }

    private boolean isValidRectangle() {
        Set<Double> xSet = new HashSet<Double>();
        Set<Double> ySet = new HashSet<Double>();

        for (Point point : this.coordinates) {
            xSet.add(point.getX());
            ySet.add(point.getY());
        }

        if (xSet.size() == 2 && ySet.size() == 2) {
            Double[] xArray = xSet.toArray(new Double[0]);
            Double[] yArray = ySet.toArray(new Double[0]);

            this.width = (int) Math.abs(xArray[0] - xArray[1]);
            this.height = (int) Math.abs(yArray[0] - yArray[1]);

            return true;
        }
        return false;
    }

    private String removeAllBrackets(String string) {
        return string.replaceAll("[(]", "").replaceAll("[)]", "");
    }

    private boolean isValidRange(int coordinate) {
        if (coordinate >= 0 && coordinate <= 24) {
            return true;
        }
        throw new IllegalArgumentException("좌표값은 0 ~ 24 사이의 값이어야 합니다.");
    }

    public int getRectangleArea() {
        if (isRectangle()) {
            return this.width * this.height;
        }
        throw new IllegalArgumentException("직사각형이 아니어서 넓이를 구할 수 없습니다. 좌표값을 다시 입력하세요.");
    }
}
