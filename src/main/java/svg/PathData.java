package svg;

import math.Point;

public class PathData {

    public String value;

    public PathData(Integer centerX, Integer centerY, Double radius, Point point1, Point point2) {
        this.value = String.format("M%d,%d L%f,%f A%f,%f 0 0,1 %f,%fZ", centerX, centerY, point1.x, point1.y, radius, radius, point2.x, point2.y);
    }

    public PathData(Double radius, Point point1, Point point2, Point point3, Point point4) {
        this.value = String.format("M%f,%f L%f,%f A%f,%f 0 0,1 %f,%f L%f,%f A%f,%f 0 0,0 %f,%fZ", point1.x, point1.y, point2.x, point2.y, radius * 2, radius * 2, point3.x, point3.y, point4.x, point4.y, radius, radius, point1.x, point1.y);
    }
}
