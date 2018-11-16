import java.util.Date;

public class Evenement {
    public final String symbol;
    public final Date date;
    public final String title;
    public final String text;

    public Evenement(String symbol, Date date, String title, String text) {
        this.symbol = symbol;
        this.date = date;
        this.title = title;
        this.text = text;
    }
}
