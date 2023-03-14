import java.time.LocalDate;
import java.util.Hashtable;

public class specifics_on_animal_domestic {
    private Hashtable<String,Object> output = new Hashtable<>();

    public specifics_on_animal_domestic(String name, String breed, String eyeColor,
    String woolColor,String voice, LocalDate birthday,boolean isVaccinated_,
    Integer height, Integer weight){
        this.output.put("name", name);
        this.output.put("breed", breed);
        this.output.put("eyeColor", eyeColor);
        this.output.put("woolColor", woolColor);
        this.output.put("voice", voice);
        this.output.put("bday", birthday);
        this.output.put("isVaccinated", isVaccinated_);
        this.output.put("height", height);
        this.output.put("weight", weight);

    }

    public Hashtable<String,Object> getData(){
        return this.output;
    }
}

class specifics_on_animal_wild{
    private Hashtable<String,Object> output = new Hashtable<>();
    public specifics_on_animal_wild(String breed, String eyeColor,
    String voice, LocalDate discoveryDate,
    Integer height, Integer weight
        //height weight eyeColor sound whenFound
    ){
        this.output.put("breed", breed);
        this.output.put("eyeColor", eyeColor);
        this.output.put("voice", voice);
        this.output.put("discDate", discoveryDate);
        this.output.put("height", height);
        this.output.put("weight", weight);

    }
    public Hashtable<String,Object> getData(){
        return this.output;
    }
}