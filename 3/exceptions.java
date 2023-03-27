import java.io.IOException;

class NegativePhysicalValueException extends IOException{
    
    public NegativePhysicalValueException(String ErrorMessage){

        super(ErrorMessage);

    }
}

class UnrealisticTriangleException extends ArithmeticException{
    
    public UnrealisticTriangleException(String ErrorMessage){
        super(ErrorMessage);
    }
}

class UnexpectedPlotException extends IOException{
    

    public UnexpectedPlotException( String ErrorMessage){
        super(ErrorMessage);
    }
}

class DescriptionFormatException extends IOException{

    public DescriptionFormatException( String ErrorMessage){
        super(ErrorMessage);
    }
}