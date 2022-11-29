package svg;

import emotion.Emotion;
import math.Point;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Svg {

    public static List<String> colors = Arrays.asList("red", "green", "blue", "darkmagenta", "slateblue", "black", "magenta", "yellow", "orange", "violet", "pink", "lawngreen", "crimson");
    public static Random random = new Random();

    public static void GenerateSVGFile(String filePath) {
        try(FileWriter fileWriter = new FileWriter(filePath, false)) {
            fileWriter.write("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void WriteLine(String filePath, String line) {
        try(FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.write(line + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void GenerateSVGFile(String filePath, Integer height, Integer width, List<Point> midpointsInnerCircle, List<Double> anglesInnerCircle, List<Point> midpointsMiddleCircle, List<Double> anglesMiddleCircle, List<Point> midpointsOuterCircle, List<Double> anglesOuterCircle, List<Emotion> emotionsList) {

        List<String> emotionsCenter = new ArrayList<>();
        List<String> emotionsMiddle = new ArrayList<>();
        List<String> emotionsOuter = new ArrayList<>();

        for (Emotion emotion : emotionsList) {
            if (emotion.origin_center == null) {
                emotionsCenter.add(emotion.original);
            } else if (emotion.origin_middle == null) {
                emotionsMiddle.add(emotion.original);
            } else {
                emotionsOuter.add(emotion.original);
            }
        }

        GenerateSVGFile(filePath);
        WriteLine(filePath, String.format("<svg height=\"%d\" width=\"%d\" xmlns=\"http://www.w3.org/2000/svg\">", height, width));
        WriteLine(filePath, "<style>text {font-size: 3px;}</style>");
        for (int i = 0; i < anglesInnerCircle.size(); i++) {
            WriteTextLine(filePath, midpointsInnerCircle.get(i).x, midpointsInnerCircle.get(i).y, anglesInnerCircle.get(i), colors.get(random.nextInt(colors.size())), emotionsCenter.get(i));
        }
        for (int i = 0; i < midpointsMiddleCircle.size(); i++) {
            WriteTextLine(filePath, midpointsMiddleCircle.get(i).x, midpointsMiddleCircle.get(i).y, anglesMiddleCircle.get(i), colors.get(random.nextInt(colors.size())), emotionsMiddle.get(i));
        }
        for (int i = 0; i < midpointsOuterCircle.size(); i++) {
            WriteTextLine(filePath, midpointsOuterCircle.get(i).x, midpointsOuterCircle.get(i).y, anglesOuterCircle.get(i), colors.get(random.nextInt(colors.size())), emotionsOuter.get(i));
        }
        WriteLine(filePath,"</svg>");
    }

    public static void WriteTextLine(String filePath, Double x, Double y, Double angle, String fillColor, String text) {
        WriteLine(filePath, String.format("<text font-size=\"0.2em\" dominant-baseline=\"middle\" text-anchor=\"middle\" transform=\"translate(%f,%f) rotate(%f)\" fill=\"%s\" font-family=\"Roboto\">%s</text>", x, y, angle, fillColor, text));
    }
}
