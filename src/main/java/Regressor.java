public interface Regressor {
    double alpha();
    double beta();
    double coefCoor();
    double determ();
    double predictY(double x);
    double predictX(double y);
}
