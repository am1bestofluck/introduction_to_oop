/**
 * Program
 */
public class Program3 {

    public static void main(String[] args) {
        Plot[] test = new Plot[]{};
        Plot[] test1 = new Plot[]{};
        try{
            test = new Plot[]{
            new Circle("external circle",2.5),
            new Square("external square",4.0),
            new Triange("external triangle", 3.0, 4.0, 5.0),
            new Rectangle("external rectangle", 5.0, 6.0)};}
        catch (Exception NothingGoesWrongHere){};

        try{
            test1 = new Plot[]{
            new Circle("<new circle>",2.5),
            new Square("<new square>",4.0),
            new Circle("<Circle for custom tweaks>", 3.0),
            new Triange("<edit circle to triangle>", 3.0, 4.0, 5.0),
            new Rectangle("<edit square to rectangle>", 5.0, 800.0)};}
        catch (Exception NothingGoesWrongHere){};

        
        
        try {
            Triange FailedTriangle = new Triange("none", 1.0, 2.0, 100.0);}
        catch (Exception fails_here){
            System.out.println(fails_here.getMessage());
            System.out.println("exected error");
        }
        
        PlotCollection new_ = new PlotCollection();
        new_.add(test[0]);
        new_.add(test[1]);
        new_.add(test[2]);
        new_.add(test[3]);

        new_.add();//для теста круга             circle 2.0
        new_.add();// для теста квадрата         squarE 1.5
        new_.add();// для теста прямоугольника   rectangle 4.2 4.4
        new_.add();// для теста треугольника     triangle 3.0 4.0 5.0
        
        System.out.println(new_.reportAllArea());
        System.out.println(new_.reportAllPerimeter());

        System.out.println(new_.inventorization());
        new_.removePlot(-1);
        new_.removePlot(0);
        new_.removePlot(0);
        new_.removePlot(0);
        new_.removePlot(0);
        System.out.println(new_.inventorization());

        System.out.println("reinitiliazation collection");
        new_ = new PlotCollection();
        new_.add(test1[0]);
        new_.add(test1[1]);
        new_.add(test1[2]);
        System.out.println(new_.inventorization());

        System.out.println("editing elements");
        new_.editPlot(0,test1[3]);
        new_.editPlot(1,test1[4]);
        new_.editPlot(2);
        System.out.println(new_.inventorization());

        System.out.println("Sorting by square");
        new_.sortBySquare();
        System.out.println(new_.inventorization());

        
        }
}