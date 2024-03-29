import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PlotCollection {

    //#region declare values
    private Plot[] main_array;
    private Plot[] temp_array;
    private String tempString;
    private Plot [] for_keyboard_input = new Plot[1];
    private Scanner systemin = new Scanner(System.in);
    private String input_hint = String.join("\n",
    "", "Can add 'rectange', 'square', 'circle', 'triangle'.",
    "All values are positive doubles.",
    "'square' accepts one side.",
    "'rectange' accepts long and short side.",
    "'triangle' accepts all three sides.",
    "'circle' accepts radius.");
    private Pattern getFigure = Pattern.compile(
            // "\\(square|rectangle|triange|circle\\)", 
            "^(circle|triangle|square|rectangle)(.)+",
            Pattern.CASE_INSENSITIVE);
    private Pattern evalCircle = Pattern.compile("^(circle)(\s+[0-9\\.]+)",
        Pattern.CASE_INSENSITIVE);
    private Pattern evalSquare = Pattern.compile("^(square)(\s+[0-9\\.]+)",
        Pattern.CASE_INSENSITIVE);
    private Pattern evalTriange = Pattern.compile(
        "^(triangle)(\s+[0-9\\.]+)(\s+[0-9\\.]+)(\s+[0-9\\.]+)",
        Pattern.CASE_INSENSITIVE);
    private Pattern evalRectangle = Pattern.compile(
        "^(rectangle)(\s+[0-9\\.]+)(\s+[0-9\\.]+)",
        Pattern.CASE_INSENSITIVE);
    //#endregion
    
    public PlotCollection(){
        this.main_array = new Plot[]{};

    }

    public void add(){
        
        this.InputPlot();
        if (this.for_keyboard_input[0] == null){
            return;
        }
        
        this.temp_array = new Plot[this.main_array.length+1];
        for (int i = 0; i < this.main_array.length; i++) {
            this.temp_array[i] = this.main_array[i];
        }
        temp_array[main_array.length] = this.for_keyboard_input[0];
        this.main_array = this.temp_array;
        this.temp_array = null;
        System.out.println("Success");
        }

    private void InputPlot(){
        String fail = "Plot not added. Aborting!";
        String success = "Created new plot!";
        System.out.println(input_hint);
        String tmp = systemin.nextLine();
        Plot temPlot;
        try{
            Matcher extractPlotType = getFigure.matcher(tmp);
            boolean JAVA_WHY_YOU_DO_IT = extractPlotType.find();
            if (!JAVA_WHY_YOU_DO_IT){
                tempString = "Wrong plot description";
                throw new UnexpectedPlotException(tempString);
                }

            Matcher tryCircle = evalCircle.matcher(tmp);
            Matcher trySquare = evalSquare.matcher(tmp);
            Matcher tryRectangle = evalRectangle.matcher(tmp);
            Matcher tryTriangle = evalTriange.matcher(tmp);
            boolean JAVA_WHY_YOU_DO_IT_c=tryCircle.find();
            boolean JAVA_WHY_YOU_DO_IT_s=trySquare.find();
            boolean JAVA_WHY_YOU_DO_IT_r=tryRectangle.find();
            boolean JAVA_WHY_YOU_DO_IT_t=tryTriangle.find();
            if (!JAVA_WHY_YOU_DO_IT_c && !JAVA_WHY_YOU_DO_IT_r
                && !JAVA_WHY_YOU_DO_IT_s && !JAVA_WHY_YOU_DO_IT_t){
                    tempString = "Invalid plot arguments";
                    throw new DescriptionFormatException(tempString);}
            
            
            if (JAVA_WHY_YOU_DO_IT_c){
                temPlot = new Circle("circleDefault", Double.valueOf(tryCircle.group(2)));
            }
            else if (JAVA_WHY_YOU_DO_IT_r){
                temPlot = new Rectangle("rectangleDefault"
                , Double.valueOf(tryRectangle.group(2))
                , Double.valueOf(tryRectangle.group(3)));
            }
            else if (JAVA_WHY_YOU_DO_IT_s){
                JAVA_WHY_YOU_DO_IT_s = trySquare.find(0);
                temPlot = new Square("defaultSquare"
                , Double.valueOf(trySquare.group(2)));
            }
            else {
                temPlot = new Triange("defaultTriangle"
                , Double.valueOf(tryTriangle.group(2))
                , Double.valueOf(tryTriangle.group(3))
                , Double.valueOf(tryTriangle.group(4)));
            }
        }
        

        catch (UnexpectedPlotException | DescriptionFormatException 
        | UnrealisticTriangleException | ExceptionInInitializerError
        | NegativePhysicalValueException | NumberFormatException w){

            System.out.println(fail);
            this.for_keyboard_input[0] = null;
            return;
        }
        this.for_keyboard_input[0] = temPlot;
        System.out.println(success);

    }
    
    public void add(Plot obj){
        this.temp_array = new Plot[this.main_array.length+1];
        for (int i = 0; i < this.main_array.length; i++) {
            this.temp_array[i] = this.main_array[i];
        }
        temp_array[main_array.length] = obj;
        this.main_array = this.temp_array;
        this.temp_array = null;
        System.out.println("Success");
        }

    public String inventorization(){
        StringBuilder tmp = new StringBuilder();
        String header ="\nPlots:";
        tmp.append(header);
        for (Plot plot : this.main_array) {
            tmp.append("\n");
            if (plot instanceof Circle){
                tmp.append(((Circle)plot).toString());
            }
            else if (plot instanceof Square){
                tmp.append(((Square)plot).toString());
            }
            else if (plot instanceof Rectangle){
                tmp.append(((Rectangle)plot).toString());
            }
            else if (plot instanceof Triange) {
                tmp.append(((Triange)plot).toString());
            }
            else {
                tmp.append("what are you?");
            }
            }
            if (this.main_array.length == 0){
                tmp.append("\nNothing to report.");
            }
            String out = tmp.toString();
            return out;
    }

    private Double evalAllArea(){
        Double out = 0.0;
        for (Plot plot : this.main_array){
            out += plot.getArea();
        }
        return out;


    }

    private Double evalAllPerimeter(){
        double out = 0.0;
        for (Plot plot : this.main_array){
            out += plot.getPerimeter();
        }
        return out;

    }

    public String reportAllArea(){
        return String.format("Accumulated area: %.2f",this.evalAllArea());
    }

    public String reportAllPerimeter(){
        return String.format("Accumulated perimeter: %.2f", this.evalAllPerimeter());
    }

    public void removePlot(Integer index){
        if (this.main_array.length == 0){
            return;
        }
        if (index < 0 || index > this.main_array.length){
            System.out.println("inadequate index");
            return;
        }
        System.out.println(String.format(
            "Removing %s", this.main_array[index].toString()));
        this.temp_array = new Plot[this.main_array.length-1];
        for (int i = 0; i < index; i++) {
            this.temp_array[i] = this.main_array[i];
        }
        for (int i = index; i < this.temp_array.length ; i++) {
            this.temp_array[i] = this.main_array[i+1];
        }
        this.main_array = this.temp_array;
        this.temp_array = null;
        System.out.println("Done.");
    }

    public void editPlot(Integer index){
        if (index <0 || index >= this.main_array.length){
            System.out.println("Inadequate index");
            return;
        }
        this.InputPlot();
        if (this.for_keyboard_input == null){
            return;
        }
        
        this.main_array[index] = this.for_keyboard_input[0];
    }

    public void editPlot(Integer index, Plot newPlot ){
        if (index <0 || index >= this.main_array.length){
            System.out.println("Inadequate index");
            return;
    }
    this.main_array[index] = newPlot;
    }

    public void sortBySquare(){
        List<Plot> tempList = new ArrayList<Plot>();
        for (Plot plot : this.main_array) {
            tempList.add(plot);
        }
        Collections.sort(tempList);
        for (int i = 0; i < tempList.size(); i++) {
            this.main_array[i] = tempList.get(i);
        }
    }
}