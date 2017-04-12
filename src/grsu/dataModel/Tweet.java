package grsu.dataModel;

import java.awt.geom.Point2D.Double;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Tweet {
    private Double coordinates;
    private Date tweetDate;
    private String tweetText;

    public Double getCoordinates() {return coordinates; }
    public String getTweetText(){
        return tweetText;
    }
    public void setTweetText(String text) {
        this.tweetText = text;
    }

    public Date getTweetDate(){
        return tweetDate;
    }

    public Tweet(Double coordinates, Date tweetDate, String tweetText) {
        this.coordinates = coordinates;
        this.tweetDate = tweetDate;
        this.tweetText = tweetText;
    }

    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "[" + this.coordinates.x + ", " + this.coordinates.y + "] "  + " " + dateFormat.format(this.tweetDate) + " " + this.tweetText;
    }

    //содержит ли текст твита хэштег
    public boolean containsHashtag(String hashtag){
        String[] array = tweetText.toLowerCase().split(" ");
        for(int i = 0; i < array.length; i++){
            if(array[i].equals(hashtag.toLowerCase()))
                return true;
        }
        return false;
    }

    public double getWeightTweet(List<Sentiment> sentiments){
        double weight = 0;
        String[] array = tweetText.toLowerCase().split(" ");
        for(int i = 0; i < array.length; i++){
            for(Sentiment st : sentiments){
                if(st.getSentiment().equals(array[i]))
                    weight += st.getNumber();
            }
        }

        return weight;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Tweet temp = (Tweet) obj;
        return temp.coordinates == coordinates && temp.tweetDate == tweetDate && temp.tweetText == tweetText;
    }

    @Override
    public int hashCode(){
        int result = coordinates.hashCode();
        result = 31*result + tweetDate.hashCode();
        result = 31*result + tweetText.hashCode();
        return result;
    }

}
