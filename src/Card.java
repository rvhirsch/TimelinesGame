public class Card implements Comparable{

    private int year;
    private int cardNumber;
    private String category;
    private String description;

    public Card(int year, int cardNumber, String category, String description) {
        this.year = year;
        this.cardNumber = cardNumber;
        this.category = category;
        this.description = description;
    }

    public String timelineToString() {
        return "(" + this.year + ") " + this.description;
    }

    @Override
    public String toString() {
        return this.description;
    }

    @Override
    public int compareTo(Object o) {
        Card comp = (Card) o;

        if (this.getYear() < comp.getYear()) {
            return -1;
        }

        if (this.getYear() > comp.getYear()) {
            return 1;
        }

        return 0;
    }

    public int getYear() {
        return this.year;
    }
}
