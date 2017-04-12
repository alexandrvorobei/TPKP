package grsu.parsers;

import grsu.dataModel.Tweet;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseTweets implements Parseable<Tweet> {

    private final Pattern tweetPattern = Pattern.compile("\\[(-?[0-9]+\\.[0-9]+), (-?[0-9]+\\.[0-9]+)\\].([0-9]).([0-9]{4}\\-[0-1][0-9]\\-[0-3][0-9]).([0-2][0-9]:[0-6][0-9]:[0-6][0-9]).(.+)");

    public List<Tweet> parse(String route) throws IOException, ParseException {
        List<Tweet> tweets = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        BufferedReader reader = new BufferedReader(new FileReader(route));
        String string;
        int j = 0;
        while ((string = reader.readLine()) != null){
            if(j == 1000000) break;
            if(isCorrect(string)){
                Matcher matcher = tweetPattern.matcher(string);
                matcher.find();
                Point2D.Double point = new Point2D.Double();
                point.x = Double.parseDouble(matcher.group(1));
                point.y = Double.parseDouble(matcher.group(2));
                Date date = format.parse(matcher.group(4) + ' ' + matcher.group(5));
                Tweet tweet = new Tweet(point, date, matcher.group(6));
                tweets.add(tweet);
            }
            else {
                Tweet previousTweet = tweets.get(tweets.size()-1);
                previousTweet.setTweetText(previousTweet.getTweetText()+string);
            }
            j++;
        }
        return tweets;
    }
    private boolean isCorrect(String string){
        Matcher matcher = tweetPattern.matcher(string);
        return matcher.find();
    }
}
