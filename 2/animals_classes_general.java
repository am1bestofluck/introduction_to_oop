import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;


/**
 * animals_classes
 */
abstract class animals_general  implements animal_i{
    protected Hashtable<String,Object> general_info = new Hashtable<>();
    protected Hashtable <String, Object> specifics = new Hashtable<>();
    // сначала сделал два класса потом вспомнил что они должны быть
    // под общим знаминателем :( :)

    public String getBreed(){
        return (String)this.general_info.get("breed");
    }

    public Integer getHeight(){
        return (Integer)this.specifics.get("height");
    }

    public Integer getWeight() {
        return (Integer)this.specifics.get("weight");
    }

    public String getEyeColor() {
        return (String)this.specifics.get("eyeColor");
}
    public String SayYourThing() {
        return (String)this.specifics.get("voice");
}
    public void getInfo(){
        System.out.println("Called through childs");
    }
    public void specialAction(){
        return;
    }
}

class DomesticAnimal extends animals_general implements domestic_i{
    
    public ArrayList<String> getGeneral(){
        String call_ = "This breed";
        ArrayList<String> out = new ArrayList<>();
        out.add(String.format("%s lives in %s.", call_,
        (String)super.general_info.get("habitant")));
        out.add(String.format("%s lives around %d years.", call_,
        (Integer)super.general_info.get("expectedAge")));
        out.add(String.format("%s prefers %s as food. Don't feed it though.", call_,
        (String)super.general_info.get("preferredFood")));
        out.add(String.format("%s usually goes \"%s\"", call_, 
        (String)super.general_info.get("feedback")));
        return out;
        
    }

    @Override
    public ArrayList<String> getSpecifics() {
        ArrayList<String> out = new ArrayList<>();
        String call_= "This animal";
        String boolVaccinated = this.getVaccinated()? "is":"isn't";
        out.add(String.format("%s's breed is %s.",call_, this.getBreed()));
        out.add(String.format("%s's name is %s.",call_,  this.getName()));
        out.add(String.format("%s's eye color is %s.", this.getName(),this.getEyeColor()));
        out.add(String.format("%s's wool color is %s.", this.getName(), this.getColor()));
        out.add(String.format("%s goes \"%s\".", this.getName(), this.SayYourThing()));
        out.add(String.format("%s was born in %s.",this.getName(), this.getBirthDate()
        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString()));
        out.add(String.format("%s %s vaccinated.", this.getName(),boolVaccinated));
        out.add(String.format("%s is as tall as %d cm's.", this.getName(),this.getHeight()));
        out.add(String.format("%s weigths %d kg's.", this.getName(), this.getWeight()));
        return out;
    }


    @Override
    public String getName() {
        return (String)super.specifics.get("name");
    }

    @Override
    public String getBreed() {
        return (String)super.specifics.get("breed");
    }

    @Override
    public boolean getVaccinated() {
        return (boolean)super.specifics.get("isVaccinated");
    }

    @Override
    public String getColor() {
        return (String)super.specifics.get("woolColor");
    }

    @Override
    public LocalDate getBirthDate() {
        return (LocalDate)super.specifics.get("bday");
    }

    @Override
    public void askAttention() {
        System.out.println(String.format("%s asks for food and treats! What are You waiting for?!", this.getName() ));
    }
    
    public DomesticAnimal(general_on_animal_inputClass info, specifics_on_animal_domestic_inputClass more_info){
        super.general_info = info.getData();
        super.specifics = more_info.getData();

    }
    @Override
    public void getInfo() {
        System.out.println(String.format("Shared data on %s:", this.getName()));
        for (String string : this.getGeneral()) {
            System.out.println(string);
        }
        System.out.println("This one specifically:");
        for (String string : this.getSpecifics()) {
            System.out.println(string);
        
        }
        System.out.println(this.SayYourThing());
        this.askAttention();
    }
    @Override
    public void specialAction() {
        super.specialAction();
        this.askAttention();
    }
}


class WildAnimal extends animals_general implements wild_i{
    

    public String getBreed(){
        return (String) super.specifics.get("breed");
    }
    
    @Override
    public ArrayList<String> getSpecifics() {
        String call_ = "This animal";
        ArrayList<String> out = new ArrayList<>();
        out.add(String.format("%s's breed is called \"%s\".",
            call_ ,this.getBreed()));
        out.add(String.format("This %s was discovered at %s.", this.getBreed(), 
            this.getFirstContactDate()
            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).toString());
        out.add(String.format("This %s goes \"%s\".", this.getBreed(), this.SayYourThing()));
        out.add(String.format("This %s has %s eyes.", this.getBreed(), this.getEyeColor()));
        out.add(String.format("This %s's height is %d cm's.", this.getBreed(),super.getHeight()));
        out.add(String.format("This %s's weight is %d kg's.", this.getBreed(),super.getWeight()));
        return out;
    }

    public ArrayList<String> getGeneral(){
        String call_ = "These animals";
        ArrayList<String> out = new ArrayList<>();
        out.add(String.format("%s live in %s",
            call_,super.general_info.get("habitant")));
        out.add(String.format("%s expectedly live around %d year(s).",
            call_,super.general_info.get("expectedAge")));
        out.add(String.format("%s prefer %s.",call_,
            super.general_info.get("preferredFood") ));
        out.add(String.format("%s usually go \"%s\"", call_,
            super.general_info.get("feedback")));
        return out;
    }


    @Override
    public String getInhabitedZone() {
        return (String) super.general_info.get("habitant");
    }

    @Override
    public LocalDate getFirstContactDate() {
        return (LocalDate)super.specifics.get("discDate");}

    public WildAnimal(general_on_animal_inputClass info, specifics_on_animal_wild more_info){
        super.general_info = info.getData();
        super.specifics = more_info.getData();
    }

    @Override
    public void getInfo() {
        System.out.println(String.format("Shared data on %s's:", this.getBreed()));

        for (String iterable_element : this.getGeneral()) {
            System.out.println(iterable_element);
        }
        System.out.println("This one specifically:");

        for (String iterable_element : this.getSpecifics()) {
            System.out.println( iterable_element);
        }
    }
}


abstract class bird extends animals_general implements bird_i{


    @Override
    public ArrayList<String> getGeneral() {
        Integer aviaryHeight = 5;
        String border = (this.getFlightLevel() <= aviaryHeight)
            ?""
            :String.format(
                "Not here though, aviary's height is %d meters straight.",
                aviaryHeight);
        ArrayList<String> out = new ArrayList<>();
        out.add(String.format("This bird's breed is called %s.", super.general_info.get("breed")));
        out.add(String.format("%s's usually go \"%s\".",
        super.general_info.get("breed"), super.SayYourThing()));
        out.add(String.format("%s can fly at around %d meters. %s",
        super.general_info.get("breed"),this.getFlightLevel(),border));
        return out;
    }

    @Override
    public ArrayList<String> getSpecifics() {
        
        ArrayList<String> out = new ArrayList<>();
        // "eyeColor","height","weight"
        String call_ = String.format("This %s",super.general_info.get("breed"));
        out.add(String.format("%s's eyecolor is %s. So cute ^^.",call_,super.specifics.get("eyeColor")));
        out.add(String.format("%s's height is %d cm's.",call_,super.getHeight()));
        out.add(String.format("%s's weight is %d kg's. ",call_,super.getWeight()));
        return out;
    }

    @Override
    public Integer getFlightLevel() {
        return (Integer)super.general_info.get("altitude");
    }

    @Override
    public String iFly() {
        return String.join("\n",
            String.format("%s stretches wings,breaks into flying for only few circles:",
            super.general_info.get("breed")),"",
            "\"Look at Me gliding and envy!\"","","","",
            "Then gracefully lands on sunny spot.");
    }
    public bird(general_on_bird info){
        for (String iterable_element : new String[]{"breed","voice","altitude"}) {
            super.general_info.put(iterable_element,info.getData().get(iterable_element));
        }
        for (String iterable_element : new String[]{"eyeColor","height","weight","voice"}) {
            super.specifics.put(iterable_element,info.getData().get(iterable_element));
        }
    }
    @Override
    public void getInfo(){
        System.out.println(String.format("\nShared data on %s:", super.general_info.get("breed")));
        for (String string : this.getGeneral()) {
            System.out.println(string);
        }
        System.out.println("This one specifically:");
        for (String string : this.getSpecifics()) {
            System.out.println(string);
    }

    }
@Override
public void specialAction() {
    super.specialAction();
    System.out.println(this.iFly());
    }
}
