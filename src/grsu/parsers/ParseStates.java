package grsu.parsers;


import grsu.dataModel.Area;
import grsu.dataModel.State;

import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseStates implements Parseable<State> {
    public List<State> parse(String route) throws IOException, ParseException {
        List<State> states = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(route));
        String[] array = reader.readLine().split(", \"");
        Pattern nameState = Pattern.compile("[A-Z]{2}");
        for(int i = 0; i < array.length; i++) {
            Matcher matcher1 = nameState.matcher(array[i]);
            matcher1.find();
            State state = getState(matcher1.group(),array[i]);
            states.add(state);
        }
        return states;
    }

    public static State getState(String nameState, String coordinates){
        State state = new State(nameState);
        Pattern coordinate = Pattern.compile("\\-?[0-9]+\\.[0-9]+");
        String[] array2 = coordinates.split("\\]\\]");
        for(int j = 0; j < array2.length; j++){
            if(array2[j].equals("]"))
                break;
            Polygon polygon = new Polygon();
            Area area = new Area();
            Matcher matcher2 = coordinate.matcher(array2[j]);
            while (matcher2.find()){
                Point2D.Double point = new Point2D.Double();
                point.x = Double.parseDouble(matcher2.group());
                matcher2.find();
                point.y = Double.parseDouble(matcher2.group());
                area.addPoint(point);
                polygon.addPoint((int) (point.x * 10 + 1850),(int) (point.y * 10 * -1 + 770));
            }
            state.areas.add(area);
            state.polygons.add(polygon);
        }
        return state;
    }
}

