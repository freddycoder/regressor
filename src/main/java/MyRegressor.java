public class MyRegressor {
	
	private final double[] x;
	private final double[] y;
	public final double xBar;
	public final double yBar;
	public final double sx;
	public final double sy;
	public final double sumOfXY;
	
	public MyRegressor(double[] x, double[] y) {

	}
	
	public double alpha() {
		return 0;
	}
	
	public double beta() {
		return 0;
	}
	
	public double coefCoor() {
		return 0;
	}

	public double determ() {
		return 0;
	}
	
	public double predictY(double x) {
		return 0;
	}

	public double predictX(double y) {
		return 0;
	}

	public String toString() {
		String s = "n = " + x.length + ": x̄ = " + round(xBar, 3) + ": sx = " + round(sx, 3) + ": yBar = " + round(yBar, 3) + ": sy = " + round(sy,3);
		s += System.lineSeparator();
		s += "a = " + round(alpha(), 3) + ": b = " + round(beta(), 3) + System.lineSeparator();
		s += "coef = " + round(coefCoor(),3) + ": determ = " + round(determ(), 3);
		return s;
	}

	public double round(double x, int precision) {
		return Math.round(x * Math.pow(10, precision)) / Math.pow(10, precision);
	}
	
	public static double ecartTypeCorrige(double[] x) {
		return 0;
	}
	
	public static double sumProduct(double[] x, double[] y) {
		return 0;
	}
	
	public static double mean(double[] x) {
		return 0;
	}
	
	public static double sum(double[] x) {
		return 0;
	}
}
