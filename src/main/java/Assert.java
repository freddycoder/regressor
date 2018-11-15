import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;

public final class Assert {
    private Assert() {

    }

    public static void arrayAreSameLenght(double[] x, double[] y) {
        if (x.length != y.length) {
            throw new InvalidParameterException("Le tableau de x doit être de la même longueur que le tableau de y \"" +
                    "la longueur de x " + x.length + " : la longueur de y " + y.length);
        }
    }
    public static void isValidCoef(double x) {
        if (x > 1 || x < -1) {
            throw new InvalidParameterException(x + " n'est pas un coeficient valide. Il doit être plus grand ou equal à -1 et plus petit ou equal a 1");
        }
    }
}
