public class Chrono {

    private long timeStarted;

    public Chrono() {
        timeStarted = Long.MIN_VALUE;
    }

    public boolean isStartSinceLessThan(double seconde) {
        if (this.timeStarted + seconde * 1000 > System.currentTimeMillis()) {
            return true;
        }
        return false;
    }

    public void start() {
        timeStarted = System.currentTimeMillis();
    }

}
