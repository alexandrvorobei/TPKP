package grsu.parsers;

import grsu.dataModel.Sentiment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ParseSentiments implements Parseable<Sentiment> {

    public List<Sentiment> parse(String route) throws IOException, ParseException {
        List<Sentiment> sentiments = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(route));
        String string;
        while ((string = reader.readLine()) != null){
            String[] array = string.split(",");
            Sentiment sentiment = new Sentiment(array[0],Double.parseDouble(array[1]));
            sentiments.add(sentiment);
        }
        return sentiments;
    }
}
