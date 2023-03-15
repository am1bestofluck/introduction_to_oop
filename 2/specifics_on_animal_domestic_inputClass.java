import java.time.LocalDate;
import java.util.Hashtable;

//*
//  Входящие данные в виде классов
//*/
abstract class specifics_on_animal{
    protected Hashtable<String,Object> output = new Hashtable<>();

    protected Hashtable<String,Object> getData(){
        return this.output;
}
}

public class specifics_on_animal_domestic_inputClass extends specifics_on_animal {
    // protected Hashtable<String,Object> output = new Hashtable<>();

    public specifics_on_animal_domestic_inputClass(String name, String breed, String eyeColor,
    String woolColor,String voice, LocalDate birthday,boolean isVaccinated_,
    Integer height, Integer weight){
        super.output.put("name", name);
        super.output.put("breed", breed);
        super.output.put("eyeColor", eyeColor);
        super.output.put("woolColor", woolColor);
        super.output.put("voice", voice);
        super.output.put("bday", birthday);
        super.output.put("isVaccinated", isVaccinated_);
        super.output.put("height", height);
        super.output.put("weight", weight);

    }

    // public Hashtable<String,Object> getData(){
    //     return this.output;
    // }
}

class specifics_on_animal_wild extends specifics_on_animal{

    public specifics_on_animal_wild(String breed, String eyeColor,
    String voice, LocalDate discoveryDate,
    Integer height, Integer weight
    
    ){
        super.output.put("breed", breed);
        super.output.put("eyeColor", eyeColor);
        super.output.put("voice", voice);
        super.output.put("discDate", discoveryDate);
        super.output.put("height", height);
        super.output.put("weight", weight);

    }
}

class general_on_bird extends specifics_on_animal{

    public general_on_bird(String breed, String eyeColor,
            String voice, Integer altitude,
            Integer height, Integer weight){
        super.output.put("breed",breed);
        super.output.put("eyeColor",eyeColor);
        super.output.put("voice",voice);
        super.output.put("height",height);
        super.output.put("weight",weight);
        super.output.put("altitude",altitude);
    }
}