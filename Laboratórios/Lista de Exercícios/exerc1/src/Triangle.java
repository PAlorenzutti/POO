public class Triangle {
    private Point p1, p2, p3;

    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public Point getP1(){
        return this.p1;
    }

    public Point getP2(){
        return this.p2;
    }

    public Point getP3(){
        return this.p3;
    }

    public double calculaPerimetro(){
        double dist1 = this.p1.calculaDistancia(this.p2);
        double dist2 = this.p2.calculaDistancia(this.p3);
        double dist3 = this.p3.calculaDistancia(this.p1);


        return dist1 + dist2 + dist3;
    }
}
