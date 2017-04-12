package grsu.dataModel;

public class Sentiment {
    private String sentiment;
    private double number;

    public Sentiment(String sent, double num) {
        this.sentiment = sent;
        this.number = num;
    }

    public String getSentiment(){
        return sentiment;
    }
    public void setSentiment(String sentim) {
        this.sentiment = sentim;
    }

    public double getNumber(){
        return number;
    }
    public void setNumber(double num) {
        this.number = num;
    }

    public String toString() {
        return this.sentiment + "," + this.number;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Sentiment temp = (Sentiment) obj;
        return temp.sentiment == sentiment && temp.number == number;
    }

    @Override
    public int hashCode() {
        int result = sentiment.hashCode();
        result = 31 * result + (int)number;
        return result;
    }
}