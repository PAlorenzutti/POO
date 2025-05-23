public class App {
    public App() {
    }
 
    public static void main(String[] args) throws Exception {
       Point p1 = new Point(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
       Point p2 = new Point(Double.parseDouble(args[2]), Double.parseDouble(args[3]));
       Point p3 = new Point(Double.parseDouble(args[4]), Double.parseDouble(args[5]));
       Triangle triangle = new Triangle(p1, p2, p3);
       System.out.println(triangle.calculaPerimetro());
    }
 }