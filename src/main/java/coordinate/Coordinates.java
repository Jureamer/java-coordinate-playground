package coordinate;

import java.sql.Array;
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
        isValidCoordinateString(string);
        String coordinateString = removeAllBrackets(string);

        for (String coordinate : coordinateString.split("-")) {
            String[] splitedCoordinate = coordinate.split(",");

            double x = Double.parseDouble(splitedCoordinate[0]);
            double y = Double.parseDouble(splitedCoordinate[1]);

            if (isValidRange(x) && isValidRange(y)) {
                Point point = new Point(x, y);
                this.coordinates.add(point);
            }
        }
    }
    public boolean isStraight() {
        return this.coordinates.size() == 2;
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

    private boolean isValidRange(double point) {
        if (point >= 0 && point <= 24) {
            return true;
        }
        throw new IllegalArgumentException("좌표값은 0 ~ 24 사이의 값이어야 합니다.");
    }

    public double getStraightDistance() {
        List<Double> xs = new ArrayList<Double>();
        List<Double> ys = new ArrayList<Double>();

        for (Point point : coordinates) {
            xs.add(point.getX());
            ys.add(point.getX());
        }

        return sqrt(pow(xs.get(1) - xs.get(0), 2) + pow(ys.get(1) - ys.get(0), 2));
    }

    public int getRectangleArea() {
        if (isRectangle()) {
            return this.width * this.height;
        }
        throw new IllegalArgumentException("직사각형이 아니어서 넓이를 구할 수 없습니다. 좌표값을 다시 입력하세요.");
    }

    private boolean isValidCoordinateString(String coordinateString) {
        if (coordinateString.contains("(") && coordinateString.contains(")") && coordinateString.contains(",")) {
            return true;
        }

        throw new IllegalArgumentException("유효하지 않은 좌표값입니다.");
    }

    public boolean isTriangle() {
        return this.coordinates.size() == 3;
    }

    public double getTriangleArea() {
        if (!isTriangle()) {
            throw new IllegalArgumentException("해당 좌표값들은 삼각형이 아닙니다.");
        }

        List<Double> xs = new ArrayList<Double>();
        List<Double> ys = new ArrayList<Double>();


        for (Point point : coordinates) {
            xs.add(point.getX());
            ys.add(point.getY());
        }

        double[] distances = new double[3];
        distances[0] = Math.sqrt(Math.pow(xs.get(1) - xs.get(0), 2) + Math.pow(ys.get(1) - ys.get(0), 2));
        distances[1] = Math.sqrt(Math.pow(xs.get(2) - xs.get(1), 2) + Math.pow(ys.get(2) - ys.get(1), 2));
        distances[2] = Math.sqrt(Math.pow(xs.get(2) - xs.get(0), 2) + Math.pow(ys.get(2) - ys.get(0), 2));

        return calculateTriangleArea(distances);
    }

    private double calculateTriangleArea(double[] sides) {
        double a = sides[0];
        double b = sides[1];
        double c = sides[2];

        double s = (a + b + c) / 2.0;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}
