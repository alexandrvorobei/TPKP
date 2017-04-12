package grsu.reports;

import grsu.dataModel.State;
import grsu.dataModel.Tweet;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Report3 {
    public void generate (List<Tweet> tweets, List<State> states, String t1, String t2) throws IOException, SQLException, ClassNotFoundException {
        Date time1 = new Date();
        Date time2 = new Date();
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            time1 = SDF.parse(t1);
            time2 = SDF.parse(t2);

        }catch(Exception e)
        {

        }
        if(time1.compareTo(time2)>0){
            Date temp = time1;
            time1=time2;
            time2=temp;
        }
        int[] numOfTweets = new int[52];
        int i = 0;
        for(State state : states){
            for(Polygon polygon : state.polygons){
                for (Tweet tweet : tweets){
                    int x = (int)(tweet.getCoordinates().x)
                    if(time1.compareTo(tweet.getTweetDate())<=0 && time2.compareTo(tweet.getTweetDate())>=0 && polygon.contains(tweet.getCoordinates().x,tweet.getCoordinates().y))
                        numOfTweets[i]++;
                }
            }
            i++;
        }
        Arrays.sort(numOfTweets);
        BufferedWriter writer = new BufferedWriter(new FileWriter(".//report3.txt"));

        for(int j = 0; j < numOfTweets.length; j++){
            if(numOfTweets[j] == numOfTweets[numOfTweets.length - 1]) {
                State state = states.get(j);
                writer.write(state.getNameState() + " : " + numOfTweets[numOfTweets.length -1]);
                writer.newLine();
            }
        }

        writer.close();
    }
}
