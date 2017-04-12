package grsu;

import grsu.dataModel.Sentiment;
import grsu.dataModel.State;
import grsu.dataModel.Tweet;
import grsu.parsers.ParseSentiments;
import grsu.parsers.ParseStates;
import grsu.parsers.ParseTweets;
import grsu.reports.Report1;
import grsu.reports.Report2;
import grsu.reports.Report3;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        ParseTweets parseTweets = new ParseTweets();
        List<Tweet> tweets = parseTweets.parse("all_tweets1.txt");

        Report1 report1 = new Report1();
        System.out.println("Input #hashtag or @username:");
        String hashtag = scanner.nextLine();
        report1.generate(tweets,hashtag);

        ParseSentiments parseSentiments = new ParseSentiments();
        List<Sentiment> sentiments = parseSentiments.parse("sentiments.csv");
        System.out.println("Input Started TIME like this hh:mm:ss : ");
        String t1 = scanner.nextLine();
        System.out.println("Input Finished TIME like this hh:mm:ss : ");
        String t2 = scanner.nextLine();
        Report2 report2 = new Report2();
        report2.generate(tweets, sentiments, t1, t2);

        ParseStates parseStates = new ParseStates();
        List<State> states = parseStates.parse("states.json");
        Report3 report3 = new Report3();
        report3.generate(tweets, states, t1, t2);
    }

}
