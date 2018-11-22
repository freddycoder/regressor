import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.*;

public class RegressorTest {

    private static final double DELTA = 10e-2;
    private static final double[] distanceKm = { 14.2, 12.9, 8.4, 10.5, 11.8, 7.1, 13.5 };
    private static final double[] coutDollar = { 27.3, 25.7, 17.6, 21.9, 24.9, 15.4, 26.0 };
    private static final Regressor regressor = new Regressor(distanceKm, coutDollar);

    @Test
    public void validerAssert() {
        double[] x = { 1, 2, 3 };
        double[] y = { 1, 2 };

        try {
            Regressor regressor = new Regressor(x, y);
            fail();
        }
        catch (InvalidParameterException e) {
            assertTrue(e.getMessage().contains("la longueur de x 3 : la longueur de y 2"));
        }
    }

    @Test
    public void alpha() {
        assertEquals(3.64, regressor.alpha(), DELTA);
    }

    @Test
    public void beta() {
        assertEquals(1.70, regressor.beta(), DELTA);
    }

    @Test
    public void coefCoor() {
        assertEquals(0.99, regressor.coefCoor(), DELTA);
    }

    @Test
    public void determ() {
        assertEquals(0.98, regressor.determ(), DELTA);
    }

    @Test
    public void predictY() {
        assertEquals(30.84, regressor.predictY(16), DELTA);
    }

    @Test
    public void predictX() {
        assertEquals(15.5, regressor.predictX(30), DELTA);
    }

    @Test
    public void classFields() {
        Regressor regressor = new Regressor(distanceKm, coutDollar);

        assertEquals(1851.14, regressor.sumOfXY, DELTA);
        assertEquals(11.2, regressor.xBar, DELTA);
        assertEquals(2.67, regressor.sx, DELTA);
        assertEquals(22.69, regressor.yBar, DELTA);
        assertEquals(4.58, regressor.sy, DELTA);
    }

    @Test
    public void validerAssertsumProduct() {
        double[] x = { 1, 2, 3};
        double[] y = { 1, 2 };

        try {
            Regressor.sumProduct(x, y);
            assertTrue(false);
        }
        catch (InvalidParameterException e) {
            assertTrue(e.getMessage().contains("la longueur de x 3 : la longueur de y 2"));
        }
    }

    @Test
    public void ecartTypeCorrige() {
        assertEquals(4.58, Regressor.standartDeviation(coutDollar), DELTA);
    }

    @Test
    public void sumProduct() {
        assertEquals(1851.14, Regressor.sumProduct(distanceKm, coutDollar), DELTA);
    }

    @Test
    public void mean() {
        assertEquals(11.2, Regressor.mean(distanceKm), DELTA);
    }

    @Test
    public void sum() {
        assertEquals(158.8, Regressor.sum(coutDollar), DELTA);
    }
}