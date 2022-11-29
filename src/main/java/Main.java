import emotion.Emotion;
import math.*;
import svg.Path;
import svg.PathData;
import svg.Svg;
import xml.Xml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static math.Functions.*;

public class Main {

    public static void main(String[] argv) {

        Integer c_x = 100;
        Integer c_y = 100;
        Integer r = 30;

        String filePathSVG = null;
        String filePathXML = null;

        String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        String appConfigPath = rootPath + "app.properties";
        try (InputStream input = new FileInputStream(appConfigPath)) {
            Properties appProps = new Properties();
            appProps.load(input);
            filePathSVG = appProps.getProperty("path.svg");
            filePathXML = appProps.getProperty("path.xml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        List<String> emotions_origins_center = Arrays.asList("x", "x", "x", "x", "x", "x", "x", "Pyktis", "Pyktis", "Pyktis", "Pyktis", "Pasibjaurėjęs", "Pasibjaurėjęs", "Pasibjaurėjęs", "Pasibjaurėjęs", "Liūdesys", "Liūdesys", "Liūdesys", "Liūdesys", "Liūdesys", "Liūdesys", "Laimingas", "Laimingas", "Laimingas", "Laimingas", "Laimingas", "Laimingas", "Laimingas", "Laimingas", "Laimingas", "Nuostaba", "Nuostaba", "Nuostaba", "Nuostaba", "Netikęs", "Netikęs", "Netikęs", "Netikęs", "Baimė", "Baimė", "Baimė", "Baimė", "Baimė", "Baimė", "Pyktis", "Pyktis", "Pyktis", "Pyktis", "Pyktis", "Pyktis", "Pyktis", "Pyktis", "Pyktis", "Pyktis", "Pyktis", "Pyktis", "Pasibjaurėjęs", "Pasibjaurėjęs", "Pasibjaurėjęs", "Pasibjaurėjęs", "Pasibjaurėjęs", "Pasibjaurėjęs", "Pasibjaurėjęs", "Pasibjaurėjęs", "Liūdesys", "Liūdesys", "Liūdesys", "Liūdesys", "Liūdesys", "Liūdesys", "Liūdesys", "Liūdesys", "Liūdesys", "Liūdesys", "Liūdesys", "Liūdesys", "Laimingas", "Laimingas", "Laimingas", "Laimingas", "Laimingas", "Laimingas", "Laimingas", "Laimingas", "Laimingas", "Laimingas", "Laimingas", "Laimingas", "Laimingas", "Laimingas", "Laimingas", "Laimingas", "Laimingas", "Laimingas", "Nuostaba", "Nuostaba", "Nuostaba", "Nuostaba", "Nuostaba", "Nuostaba", "Nuostaba", "Nuostaba", "Netikęs", "Netikęs", "Netikęs", "Netikęs", "Netikęs", "Netikęs", "Netikęs", "Netikęs", "Baimė", "Baimė", "Baimė", "Baimė", "Baimė", "Baimė", "Baimė", "Baimė", "Baimė", "Baimė", "Baimė", "Baimė", "Pyktis", "Pyktis", "Pyktis", "Pyktis", "Pyktis", "Pyktis", "Pyktis", "Pyktis");
        List<String> emotions_origins_middle = Arrays.asList("x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "Agresyvus", "Agresyvus", "Frustruotas", "Frustruotas", "Atsiribojęs", "Atsiribojęs", "Kritikuojantis", "Kritikuojantis", "Nepritariantis", "Nepritariantis", "Nusivylęs", "Nusivylęs", "Bjaurus", "Bjaurus", "Atmestas", "Atmestas", "Įskaudintas", "Įskaudintas", "Depresiškas", "Depresiškas", "Kaltas", "Kaltas", "Beviltiškas", "Beviltiškas", "Pažeidžiamas", "Pažeidžiamas", "Vienišas", "Vienišas", "Optimistiškas", "Optimistiškas", "Pasitikintis", "Pasitikintis", "Taikus", "Taikus", "Stiprus", "Stiprus", "Priimtas", "Priimtas", "Išdidus", "Išdidus", "Susidomėjęs", "Susidomėjęs", "Patenkintas", "Patenkintas", "Žaismingas", "Žaismingas", "Sužadintas", "Sužadintas", "Nustebęs", "Nustebęs", "Sumišęs", "Sumišęs", "Apstulbęs", "Apstulbęs", "Pavargęs", "Pavargęs", "Stresuojantis", "Stresuojantis", "Perkrautas", "Perkrautas", "Nuobodus", "Nuobodus", "Išsigandęs", "Išsigandęs", "Nerimastingas", "Nerimastingas", "Nesaugus", "Nesaugus", "Neryžtingas", "Neryžtingas", "Atstumtas", "Atstumtas", "Grėsmingas", "Grėsmingas", "Nuviltas", "Nuviltas", "Pažemintas", "Pažemintas", "Kandus", "Kandus", "Įsiutęs", "Įsiutęs");
        List<String> emotions_original = Arrays.asList("Pyktis", "Pasibjaurėjęs", "Liūdesys", "Laimingas", "Nuostaba", "Netikęs", "Baimė", "Agresyvus", "Frustruotas", "Atsiribojęs", "Kritikuojantis", "Nepritariantis", "Nusivylęs", "Bjaurus", "Atmestas", "Įskaudintas", "Depresiškas", "Kaltas", "Beviltiškas", "Pažeidžiamas", "Vienišas", "Optimistiškas", "Pasitikintis", "Taikus", "Stiprus", "Priimtas", "Išdidus", "Susidomėjęs", "Patenkintas", "Žaismingas", "Sužadintas", "Nustebęs", "Sumišęs", "Apstulbęs", "Pavargęs", "Stresuojantis", "Perkrautas", "Nuobodus", "Išsigandęs", "Nerimastingas", "Nesaugus", "Neryžtingas", "Atstumtas", "Grėsmingas", "Nuviltas", "Pažemintas", "Kandus", "Įsiutęs", "Provokuojantis", "Priešiškas", "Susierzinęs", "Sudirgęs", "Vengiantis", "Sustingęs", "Skeptiškas", "Atstumiantis", "Kritiškas", "Gniuždantis", "Gąsdinantis", "Maištaujantis", "Šlykštus", "Niekinantis", "Pasibaisėjęs", "Abejojantis", "Sumišęs", "Nusivylęs", "Niekam tikęs", "Tuščias", "Apgailėtinas", "Susigėdęs", "Bejėgis", "Sielvartaujantis", "Trapus", "Kenčiantis", "Paliktas", "Izoliuotas", "Įkvepiantis", "Viltingas", "Artimas", "Jautrus", "Dėkingas", "Mylintis", "Kūrybingas", "Drąsus", "Vertinamas", "Gerbiamas", "Pasitikintis", "Sėkmingas", "Žingeidus", "Smalsus", "Linksmas", "Laisvas", "Žvalus", "Sužadintas", "Energingas", "Nekantrus", "Pagarbus", "Priblokštas", "Suglumęs", "Nusivylęs", "Dirglus", "Šokiruotas", "Išsiblaškęs", "Mieguistas", "Nekontroliuojantis", "Priblokštas", "Skubinamas", "Spaudžiamas", "Apatiškas", "Abejingas", "Bejėgis", "Baimingas", "Priblokštas", "Susirūpinęs", "Netinkamas", "Nepilnavertis", "Bevertis", "Nesvarbus", "Išskirtas", "Persekiojamas", "Nervingas", "Pažeidžiamas", "Išduotas", "Įsižeidęs", "Neįvertintas", "Išjuoktas", "Nemalonus", "Pažeistas", "Įniršęs", "Pavydus");
        List<Emotion> emotionsList= new ArrayList<>();

        for (int i = 0; i < emotions_original.size(); i++) {
            String emotion_origin_center = null;
            String emotion_origin_middle = null;
            if (!emotions_origins_center.get(i).equals("x")) {
                emotion_origin_center = emotions_origins_center.get(i);
            }
            if (!emotions_origins_middle.get(i).equals("x")) {
                emotion_origin_middle = emotions_origins_middle.get(i);
            }
            emotionsList.add(new Emotion(emotion_origin_center, emotion_origin_middle, emotions_original.get(i)));
        }

        // Center section
        List<PointTuple> pointsInnerCircle = GeneratePointsForInnerCircle(c_x,c_y, r);
        List<Point> midpointsInnerCircleTemp = CalculateMidPointsForTriangles(c_x, c_y, pointsInnerCircle);
        List<Double> anglesInnerCircle = CalculateAnglesForSections(c_x, c_y, midpointsInnerCircleTemp);
        List<Point> midpointsInnerCircle = new ArrayList<>();
        for (int i = 0; i < midpointsInnerCircleTemp.size(); i++) {
            midpointsInnerCircle.add(Functions.GetPointByAngleAndRadius(c_x, c_y , r * 0.6, -anglesInnerCircle.get(i)));
        }
        List<Path> pathsInnerCircle = new ArrayList<>();
        for (PointTuple pt : pointsInnerCircle) {
            Path p = new Path(new PathData(c_x, c_y, r, pt.p1, pt.p2).value);
            pathsInnerCircle.add(p);
        }

        // Middle section
        List<PointQuadruple> pointsMiddleCircle = GeneratePointsForMiddleCircle(c_x, c_y, r);
        List<Point> midPointsMiddleCircleTemp = CalculateMidpointsForTrapezoids(pointsMiddleCircle);
        List<Double> anglesMiddleCircle = CalculateAnglesForSections(c_x, c_y, midPointsMiddleCircleTemp);
        List<Point> midPointsMiddleCircle = new ArrayList<>();
        for (int i = 0; i < midPointsMiddleCircleTemp.size(); i++) {
            midPointsMiddleCircle.add(Functions.GetPointByAngleAndRadius(c_x, c_y , r * 1.5, -anglesMiddleCircle.get(i)));
        }
        List<Path> pathsMiddleCircle = new ArrayList<>();
        for (PointQuadruple pq : pointsMiddleCircle) {
            Path p = new Path(new PathData(r, pq.p1, pq.p2, pq.p3, pq.p4).value);
            pathsMiddleCircle.add(p);
        }

        // Outer section
        List<PointQuadruple> pointsOuterCircle = GeneratePointsForOuterCircle(c_x, c_y, r);
        List<Point> midPointsOuterCircleTemp = CalculateMidpointsForTrapezoids(pointsOuterCircle);
        List<Double> anglesOuterCircle = CalculateAnglesForSections(c_x, c_y, midPointsOuterCircleTemp);
        List<Point> midPointsOuterCircle = new ArrayList<>();
        for (int i = 0; i < midPointsOuterCircleTemp.size(); i++) {
            midPointsOuterCircle.add(Functions.GetPointByAngleAndRadius(c_x, c_y , r * 2.5, -anglesOuterCircle.get(i)));
        }
        List<Path> pathsOuterCircle = new ArrayList<>();
        for (PointQuadruple pq : pointsOuterCircle) {
            Path p = new Path(new PathData(r, pq.p1, pq.p2, pq.p3, pq.p4).value);
            pathsOuterCircle.add(p);
        }

        Svg.GenerateSVGFile(filePathSVG, 200, 200, midpointsInnerCircle, anglesInnerCircle, midPointsMiddleCircle, anglesMiddleCircle, midPointsOuterCircle, anglesOuterCircle, emotionsList);
        Xml.GenerateXMLFile(filePathXML, pathsInnerCircle, pathsMiddleCircle, pathsOuterCircle);
    }
}
