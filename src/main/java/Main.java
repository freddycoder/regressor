import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final String[] functions = { "TIME_SERIES_INTRADAY", "TIME_SERIES_DAILY_ADJUSTED", "TIME_SERIES_WEEKLY", "TIME_SERIES_WEEKLY_ADJUSTED", "TIME_SERIES_MONTHLY" };
    private static final String[] symboles = { "TSLA", "MSFT", "CRON", "A", "AAPL", "INTC", "MSFT" };
    private static final String[] intervales = {"1min", "5min", "15min", "30min", "60min" };
    private static final double oneMinute = 60;

    public static void main(String[] args) throws Exception {
        // API dcomentation : https://www.alphavantage.co/documentation/

        String bestFunction = functions[0];
        String bestSymbole = symboles[0];
        String bestintervale = intervales[0];
        double bestDeterm = Double.MIN_VALUE;
        int nbRequestLastMinute = 0;
        Regressor bestRegressor = null;


        // Trouver qui à le meilleur determinant parmis tout les combinaisons possibles.

        // Votre code ici


        // Print best result
        System.out.println("Best function : " + bestFunction);
        System.out.println("Best symbol : " + bestSymbole);
        if (!intervales.equals("")) {
            System.out.println("Best interval : " + bestintervale);
        }
        System.out.println("Best regressor : " + bestRegressor.toString());
    }

    public static Map<String, String> buildUrl(String function, String symbol, String interval) {
        HashMap<String, String> urlParameters = new HashMap<>();
        urlParameters.put("symbol", symbol);
        urlParameters.put("interval", interval);
        urlParameters.put("function", function);
        return urlParameters;
    }

    public static Regressor getRegressor(Map<String, String> urlParameters) {
        Parser p = new Parser(APICaller.FetchJSON(urlParameters));

        try {
            StockEntry se = p.Parse();
            return new MyRegressor(range(se.TimeSeries.size()), se.getArrayOf("4. close"));
        } catch (Exception e) {
            System.err.println(e.getCause() + " " + e.getMessage());
        }

        return null;
    }

    public static double[] range(int nbElement) {
        double[] x = new double[nbElement];
        for (int i = 0; i < nbElement; i++) {
            x[i] = i + 1;
        }
        return x;
    }
}