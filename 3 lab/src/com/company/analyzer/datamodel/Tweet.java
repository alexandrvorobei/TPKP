package com.company.analyzer.datamodel;
import java.util.Date;
public class Tweet {
    public  class GeoCord
    {
        private Double latitude ;
        private Double longitude ;

        public GeoCord(Double latitude1, Double longitude1){
            latitude = latitude1;
            longitude = longitude1;
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date ;
    private String text;

    public Tweet(Date date1, String text1){
        date = date1;
        text = text1;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "date=" + date +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tweet tweets = (Tweet) o;

        if (date != null ? !date.equals(tweets.date) : tweets.date != null) return false;
        return text != null ? text.equals(tweets.text) : tweets.text == null;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
