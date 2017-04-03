package com.company;

import com.company.analyzer.datamodel.State;
import com.company.analyzer.datamodel.Tweet;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// разбить методы parse и методы чтения

public  class ReadText {
    public  static Tweet[] spis ;//= new Tweet[900000];
    public static int count = 0;
    public  static Tweet[] readTweets() {
        try {
            FileInputStream fstream = new FileInputStream("D:/all_tweets.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            Pattern p = Pattern.compile("\\[(\\-?[0-9]+\\.[0-9]+)\\,.(\\-?[0-9]+\\.[0-9]+)\\].([0-9]+).([0-9]{4}\\-[0-9]{2}\\-[0-9]{2}).([0-9]{2}:[0-9]{2}:[0-9]{2}).(.*)");
            SimpleDateFormat parserSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String line = new String();
            Date d;
            while (( line = br.readLine()) != null) {

                if(line == null)
                    break;
                Matcher  m = p.matcher(line);
                if (m.find()){
                    d = parserSDF.parse(m.group(4) + ' ' + m.group(5));
                    Tweet tw = new Tweet(d,m.group(6));
                    Tweet.GeoCord geoC = tw.new GeoCord( Double.parseDouble(m.group(1)),Double.parseDouble(m.group(2)));

                    spis[count] = tw;
                    count++;
                }
            }
        }
        catch(IOException e){
            System.out.println("Error");
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return spis;
    }



    public static List<State> parse() throws IOException, ParseException {
        List<State> states = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("D:/states.json"));

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
            state.getAreas().add(area);
            //state.getPolygons().add(polygon);
        }
        return state;
    }
}
