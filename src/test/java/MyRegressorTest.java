import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.*;

public class MyRegressorTest {

    private static final double DELTA = 10e-2;
    private static final double[] distanceKm = { 14.2, 12.9, 8.4, 10.5, 11.8, 7.1, 13.5 };
    private static final double[] coutDollar = { 27.3, 25.7, 17.6, 21.9, 24.9, 15.4, 26.0 };

    @Test
    public void classFields() {
        MyRegressor regressor = new MyRegressor(distanceKm, coutDollar);

        assertEquals(1851.14, regressor.sumOfXY, DELTA);
        assertEquals(11.2, regressor.xBar, DELTA);
        assertEquals(2.67, regressor.sx, DELTA);
        assertEquals(22.69, regressor.yBar, DELTA);
        assertEquals(4.58, regressor.sy, DELTA);
    }

    @Test
    public void validerAssert() {
        double[] x = { 1, 2, 3};
        double[] y = { 1, 2 };

        try {
            MyRegressor.sumProduct(x, y);
            assertTrue(false);
        }
        catch (InvalidParameterException e) {
            assertTrue(e.getMessage().contains("la longueur de x 3 : la longueur de y 2"));
        }
    }

    @Test
    public void ecartTypeCorrige() {
        assertEquals(4.58, MyRegressor.ecartTypeCorrige(coutDollar), DELTA);
    }

    @Test
    public void sumProduct() {
        assertEquals(1851.14, MyRegressor.sumProduct(distanceKm, coutDollar), DELTA);
    }

    @Test
    public void mean() {
        assertEquals(11.2, MyRegressor.mean(distanceKm), DELTA);
    }

    @Test
    public void sum() {
        assertEquals(158.8, MyRegressor.sum(coutDollar), DELTA);
    }
}