public class MyRegressor implements Regressor {
	
	private final double[] x;
	private final double[] y;
	public final double xBar;
	public final double yBar;
	public final double sx;
	public final double sy;
	public final double sumOfXY;
	
	public MyRegressor(double[] x, double[] y) {
		Assert.arrayAreSameLenght(x, y);
		this.x = x;
		this.y = y;
		this.xBar = mean(x);
		this.sx = ecartTypeCorrige(x);
		this.yBar = mean(y);
		this.sy = ecartTypeCorrige(y);
		this.sumOfXY = sumProduct(x, y);
	}
	
	public double alpha() {
		return yBar - (beta() * xBar);
	}
	
	public double beta() {
		return (this.sumOfXY - (this.x.length * xBar * yBar)) / ((this.x.length - 1) * Math.pow(sx, 2));
	}
	
	public double coefCoor() {
		double coef = ((sumOfXY - (x.length * xBar * yBar))) / ((x.length - 1) * sx * sy);
		Assert.isValidCoef(coef);
		return coef;
	}

	public double determ() {
		return Math.pow(coefCoor(), 2);
	}
	
	public double predictY(double x) {
		return alpha() + beta() * x;
	}

	public double predictX(double y) {
		return (-alpha() + y) / beta();
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
		double sum = 0;
		double mean = mean(x);
		for (double d : x) {
			sum += Math.pow(d - mean, 2);
		}
		return Math.sqrt(sum/(x.length-1));
	}
	
	public static double sumProduct(double[] x, double[] y) {
		Assert.arrayAreSameLenght(x, y);
		double sum = 0;
		for (int i = 0; i < x.length; i++) {
			sum += x[i] * y[i];
		}
		return sum;
	}
	
	public static double mean(double[] x) {
		return sum(x) / x.length;
	}
	
	public static double sum(double[] x) {
		double sum = 0;
		for (double d : x) {
			sum += d;
		}
		return sum;
	}
}
