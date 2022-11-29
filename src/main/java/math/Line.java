package math;

public class Line {

    public Double coefficient_a;
    public Double coefficient_b;
    public Double coefficient_c;

    public Line(Point point1, Point point2) {
        this.coefficient_a = point2.y - point1.y;
        this.coefficient_b = point1.x - point2.x;
        this.coefficient_c = coefficient_a * point1.x + coefficient_b * point1.y;
    }
}
