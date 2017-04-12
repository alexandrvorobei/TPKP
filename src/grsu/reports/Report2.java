package grsu.reports;

import grsu.dataModel.Sentiment;
import grsu.dataModel.Tweet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Report2{
    public void generate(List<Tweet> tweets, List<Sentiment> sentiments, String t1, String t2) throws IOException, SQLException, ClassNotFoundException {
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

        double weight = 0;
        for(Tweet tweet : tweets){
           if(time1.compareTo(tweet.getTweetDate())<=0 && time2.compareTo(tweet.getTweetDate())>=0)
           {
               weight += tweet.getWeightTweet(sentiments);
           }

        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(".//report2.txt"));
        writer.write("Вес твитов равен " + weight);
        writer.close();
    }
}
