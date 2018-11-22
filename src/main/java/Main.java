import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final String ALPHAGO_BASE_URI = "https://www.alphavantage.co/query?";
    private static final String ALPHAGO_AUTHENTIFICATION = "apikey=VFSS3EDI6OQP7TX9";
    private static final APICaller ALPHAGO_API_CALLER = new APICaller(ALPHAGO_BASE_URI, ALPHAGO_AUTHENTIFICATION);
    private static final String[] functions = { "TIME_SERIES_INTRADAY", "TIME_SERIES_DAILY_ADJUSTED", "TIME_SERIES_WEEKLY", "TIME_SERIES_WEEKLY_ADJUSTED", "TIME_SERIES_MONTHLY" };
    private static final String[] symboles = { "TSLA", "CRON", "A", "AAPL", "INTC", "MSFT", "NVDA" };
    private static final String[] intervales = {"1min", "5min", "15min", "30min", "60min" };
    private static final double oneMinute = 60;

    public static void main(String[] args) throws Exception {
        // API dcomentation : https://www.alphavantage.co/documentation/

        System.out.println(getRegressor(buildUrl(functions[0], symboles[symboles.length-1], intervales[0])).toString());
    }

    public static void permutationExperiment() {
        String bestFunction = functions[0];
        String bestSymbole = symboles[0];
        String bestintervale = intervales[0];
        double bestDeterm = Double.MIN_VALUE;
        int nbRequestLastMinute = 0;

        Regressor bestRegressor = null;

        try {
            for (String symbole : symboles) {
                for (String function : functions) {

                    String currentintervale = "";
                    if (function.equals("TIME_SERIES_INTRADAY")) {
                        for (String intervale : intervales) {
                            if (nbRequestLastMinute == 5) {
                                Thread.sleep(1000 * 60);
                                nbRequestLastMinute = 0;
                            }
                            System.out.println("current_state : " + symbole + " " + " " + function + " " + intervale);

                            currentintervale = intervale;

                            Regressor r = getRegressor(buildUrl(function, symbole, currentintervale));
                            nbRequestLastMinute++;

                            if (r != null && r.beta() < bestDeterm) {
                                bestFunction = function;
                                bestSymbole = symbole;
                                bestintervale = currentintervale;
                                bestRegressor = r;
                                bestDeterm = r.beta();

                                System.out.println("Best function : " + bestFunction);
                                System.out.println("Best symbol : " + bestSymbole);

                                if (!intervales.equals("")) {
                                    System.out.println("Best interval : " + bestintervale);
                                }

                                System.out.println("Best regressor : " + bestRegressor.toString());
                            }
                        }
                    } else {
                        if (nbRequestLastMinute == 5) {
                            Thread.sleep(1000 * 60);
                            nbRequestLastMinute = 0;
                        }

                        System.out.println("current state : " + symbole + " " + function);

                        Regressor r = getRegressor(buildUrl(function, symbole, currentintervale));
                        nbRequestLastMinute++;

                        if (r != null && r.beta() < bestDeterm) {
                            bestFunction = function;
                            bestSymbole = symbole;
                            bestintervale = currentintervale;
                            bestRegressor = r;
                            bestDeterm = r.beta();

                            System.out.println("Best function : " + bestFunction);
                            System.out.println("Best symbol : " + bestSymbole);

                            if (!intervales.equals("")) {
                                System.out.println("Best interval : " + bestintervale);
                            }

                            System.out.println("Best regressor : " + bestRegressor.toString());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getCause() + " " + e.getMessage());
        }

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
        Parser p = new Parser(ALPHAGO_API_CALLER.FetchJSON(urlParameters));

        try {
            StockEntry se = p.Parse();
            return new Regressor(range(se.TimeSeries.size()), se.getArrayOf("4. close"));
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