import java.math.MathContext;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PlotCollection
 */
public class PlotCollection {
    private Plot[] main_array;
    private Plot[] temp_array;
    private String tempString;
    private Scanner systemin = new Scanner(System.in);
    private String input_hint = String.join("\n",
    "Can add 'rectange', 'square', 'circle', 'triangle'.",
    "All values are positive doubles.",
    "'square' accepts one side.",
    "'rectange' accepts long and short side.",
    "'triangle' accepts all three sides.",
    "'circle' accepts radius.");
    private Pattern getFigure = Pattern.compile(
            "^\\(square|rectangle|triange|circle)\\", 
            Pattern.CASE_INSENSITIVE);
    private Pattern evalCircle = Pattern.compile("\\(circle\\)\\s\\)+",
        Pattern.CASE_INSENSITIVE);
    private Pattern evalSquare = Pattern.compile("square ",
        Pattern.CASE_INSENSITIVE);
    private Pattern evalTriange = Pattern.compile("square ",
        Pattern.CASE_INSENSITIVE);
    private Pattern evalRectangle = Pattern.compile("rectangle ",
        Pattern.CASE_INSENSITIVE);
    
    public PlotCollection(){
        this.main_array = new Plot[]{};

    }

    public void add(){
        String fail= "Plot not added. Aborting!";
        System.out.println(input_hint);
        tempString = systemin.nextLine();
        
        try{
            Matcher extractPlotType = getFigure.matcher(tempString);
            if (!extractPlotType.find()){
                tempString = "Wrong plot description";
                throw new UnexpectedPlotException(tempString);
                }
                System.out.println("test");
            Matcher tryCircle = evalCircle.matcher(tempString);
            Matcher trySquare = evalSquare.matcher(tempString);
            Matcher tryRectangle = evalRectangle.matcher( tempString);
            Matcher tryTriangle = evalTriange.matcher(tempString);
            if (!tryCircle.find() && !trySquare.find()
                && !tryRectangle.find() && !tryTriangle.find()){
                    tempString = "Invalid plot arguments";
                    throw new DescriptionFormatException(tempString);
        }
            
        }
        catch (UnexpectedPlotException e ){
            System.out.println(tempString);
            System.out.println(fail);
            return;
        }
        catch (DescriptionFormatException w){
            System.out.println(tempString);
            System.out.println(fail);
            return;
        }


    
    }
    public void add(String description){
        String descriptionFail= "DescriptionError";
        String success = "Done!";
        if (true){
            System.out.println(descriptionFail);
            return;
        }
        System.out.println(success);

    }
    public static void main(String[] args) {
        PlotCollection new_ = new PlotCollection();
        new_.add();
        new_.add("user happened.");
    }
}