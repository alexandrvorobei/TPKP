package grsu.reports;

import grsu.dataModel.Tweet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Report1{

    public void generate(List<Tweet> tweets, String hashtag) throws IOException, SQLException, ClassNotFoundException {
        List<Tweet> tweetList = new ArrayList<>();
        for(Tweet tweet : tweets){
            if(tweet.containsHashtag(hashtag))
                tweetList.add(tweet);
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(".//report1.txt"));
        for (Tweet tweet : tweetList){
            writer.write(tweet.toString());
            writer.newLine();
        }
        writer.close();
    }
}
