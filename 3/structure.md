<pre>interface Operations  
    public static String getSimpleName;
    public Double getPerimeter;
    public Double getSquare;


abstract class Plot implements Operations() throws NumberFormatException{
    public Plot(String[] args)
}


class Circle extends Plot  
class Square extends Plot  
class Rectangle extends Plot  
class Triange extends Plot

class PlotCollection(){ // implements array???
    private Plot[] plots;

    public PlotCollection(){}

    public PlotCollection( PlotCollection test){}

    public PlotCollection ( Plot[] test){}

    public PlotCollection ( Stack<Plot> test){}

    public PlotCollection ( Queue<Plot> test){}

    public void addPlot(Plot newPlot){}

    private Double[] getWholePerimeter{} // out[0] - итого, out[1]++ - фигуры

    public void reportPerimeter(){}

    private Double [] getWholeArea{} // out[0] - итого, out[1]++ - фигуры

    public void reportArea(){}

    public String[] inventorization(){}

    public void SortByArea(){}
    
    public void removePlot(Integer index){}

    public void editPlot(Integer index) {}


}

class IOException(){}
class AttributeValueException extends IOException(){}

</pre>