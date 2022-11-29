package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Functions {

    public static Point GetTwoLineIntersection(Line line1, Line line2) {
        double determinant = line1.coefficient_a * line2.coefficient_b - line2.coefficient_a * line1.coefficient_b;
        Double x = null;
        Double y = null;

        if (determinant == 0) {
            System.out.println("Something ain't right here");
            // should not be possible in this application
        } else {
            x = (line2.coefficient_b * line1.coefficient_c - line1.coefficient_b * line2.coefficient_c) / determinant;
            y = (line1.coefficient_a * line2.coefficient_c - line2.coefficient_a * line1.coefficient_c) / determinant;
        }
        return new Point(x, y);
    }

    public static Double CalculateX(Double radius, Double angle, Integer centerX) {
        return radius * Math.cos(angle * Math.PI / 180) + centerX;
    }

    public static Double CalculateY(Double radius, Double angle, Integer centerY) {
        return radius * Math.sin(angle * Math.PI / 180) * (-1) + centerY;
    }

    public static Point GetPointByAngleAndRadius(Integer centerX, Integer centerY, Double radius, Double angle) {
        Double x = CalculateX(radius, angle, centerX);
        Double y = radius * Math.sin(angle * Math.PI / 180) * (-1) + centerY;
        return new Point(x, y);
    }

    public static List<PointTuple> GeneratePointsForInnerCircle(Integer centerX, Integer centerY, Double radius) {
        List<PointTuple> tupleList = new ArrayList<>();
        List<Integer> sections = Arrays.asList(4, 8, 14, 23, 27, 31);

        double singleSectionAngle = 360/41d;
        Point firstSectionPoint1 = new Point(CalculateX(radius, singleSectionAngle * 4, centerX), CalculateY(radius, singleSectionAngle * 4, centerY));
        Point firstSectionPoint2 = new Point(CalculateX(radius, -singleSectionAngle * 4, centerX), CalculateY(radius, -singleSectionAngle * 4, centerY));
        tupleList.add(new PointTuple(firstSectionPoint1, firstSectionPoint2));

        for (int i = 0; i < sections.size(); i++) {
            Point point1;
            Point point2;
            if (i == sections.size() - 1) {
                point1 = new Point(CalculateX(radius, -singleSectionAngle * sections.get(i), centerX), CalculateY(radius, -singleSectionAngle * sections.get(i), centerY));
                point2 = new Point(CalculateX(radius, singleSectionAngle * 4, centerX), CalculateY(radius, singleSectionAngle * 4, centerY));
            } else {
                point1 = new Point(CalculateX(radius, -singleSectionAngle * sections.get(i), centerX), CalculateY(radius, -singleSectionAngle * sections.get(i), centerY));
                point2 = new Point(CalculateX(radius, -singleSectionAngle * sections.get(i + 1), centerX), CalculateY(radius, -singleSectionAngle * sections.get(i + 1), centerY));
            }
            tupleList.add(new PointTuple(point1, point2));
        }

        return tupleList;
    }

    public static List<PointQuadruple> GeneratePointsForMiddleCircle(Integer centerX, Integer centerY, Double radius) {
        List<PointQuadruple> quadruplesList = new ArrayList<>();
        double singleSectionAngle = 360/41d;

        for (int i = 0; i < 41; i++) {
            Point p1, p2, p3, p4;
            p1 = new Point(CalculateX(radius, -singleSectionAngle * i, centerX), CalculateY(radius, -singleSectionAngle * i, centerY));
            p2 = new Point(CalculateX(radius * 2, -singleSectionAngle * i, centerX), CalculateY(radius * 2, -singleSectionAngle * i, centerY));
            p3 = new Point(CalculateX(radius * 2, -singleSectionAngle * (i + 1), centerX), CalculateY(radius * 2, -singleSectionAngle * (i + 1), centerY));
            p4 = new Point(CalculateX(radius, -singleSectionAngle * (i + 1), centerX), CalculateY(radius, -singleSectionAngle * (i + 1), centerY));
            quadruplesList.add(new PointQuadruple(p1, p2, p3, p4));
        }

        return quadruplesList;
    }

    public static List<PointQuadruple> GeneratePointsForOuterCircle(Integer centerX, Integer centerY, Double radius) {
        List<PointQuadruple> quadruplesList = new ArrayList<>();
        double singleSectionAngle = 360/82d;

        for (int i = 0; i < 82; i++) {
            Point p1, p2, p3, p4;
            p1 = new Point(CalculateX(radius * 2, -singleSectionAngle * i, centerX), CalculateY(radius * 2, -singleSectionAngle * i, centerY));
            p2 = new Point(CalculateX(radius * 3, -singleSectionAngle * i, centerX), CalculateY(radius * 3, -singleSectionAngle * i, centerY));
            p3 = new Point(CalculateX(radius * 3, -singleSectionAngle * (i + 1), centerX), CalculateY(radius * 3, -singleSectionAngle * (i + 1), centerY));
            p4 = new Point(CalculateX(radius * 2, -singleSectionAngle * (i + 1), centerX), CalculateY(radius * 2, -singleSectionAngle * (i + 1), centerY));
            quadruplesList.add(new PointQuadruple(p1, p2, p3, p4));
        }

        return quadruplesList;
    }

    public static List<Point> CalculateMidPointsForTriangles(Integer centerX, Integer centerY, List<PointTuple> basePoints) {
        List<Point> midpoints = new ArrayList<>();
        for (PointTuple basePoint : basePoints) {
            midpoints.add(new Point((centerX + basePoint.p1.x + basePoint.p2.x) / 3, (centerY + basePoint.p1.y + basePoint.p2.y) / 3));
        }

        return midpoints;
    }

    public static List<Double> CalculateAnglesForSections(Integer centerX, Integer centerY, List<Point> midpoints) {
        List<Double> angles = new ArrayList<>();
        for (Point midpoint : midpoints) {
            angles.add(Math.atan2(midpoint.y - centerY, midpoint.x - centerX) * 180 / Math.PI);
        }

        return angles;
    }

    public static List<Point> CalculateMidpointsForTrapezoids(List<PointQuadruple> pointGroups) {
        List<Point> midpoints = new ArrayList<>();
        for (PointQuadruple group : pointGroups) {
            midpoints.add(GetTwoLineIntersection(new Line(group.p1, group.p3), new Line(group.p2, group.p4)));
        }
        return midpoints;
    }
}
