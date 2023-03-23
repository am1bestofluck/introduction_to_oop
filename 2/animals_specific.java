import java.time.LocalDate;
import java.util.ArrayList;

/**
 * animals_specific
 */

class Cat extends DomesticAnimal{

    private boolean hasWool;
    
    public Cat(general_on_animal_inputClass info,
    specifics_on_animal_domestic_inputClass more_info,
    boolean hasWool){
        super(info, more_info);
        this.hasWool = hasWool;

    }
    public boolean HasWoolReport(){
        return this.hasWool;
    }

    @Override
    public ArrayList<String> getSpecifics() {
        String temp =(this.hasWool)?"has":"doesn't have";
        ArrayList<String> tmp = super.getSpecifics();
        tmp.add(String.format("%s %s wool.",
        super.specifics.get("name"),temp));
        return tmp;
    }
    }

class Tiger extends WildAnimal{
    
    public Tiger (general_on_animal_inputClass info, specifics_on_animal_wild more_info){
        super(info, more_info);
    }
}

class Dog extends DomesticAnimal{

    private boolean isTraied;

    public Dog(general_on_animal_inputClass info,
    specifics_on_animal_domestic_inputClass more_info,
    boolean isTrained){
        super(info, more_info);
        this.isTraied = isTrained;
}
    public boolean isItTrained(){
        return this.isTraied;
    }

    public void TrainMore(){
        if (this.isTraied){
            System.out.println(String.format("%s knows it all. Begone, %s!",
             super.specifics.get("name"),super.SayYourThing()));
        }
        else{
        System.out.println(String.format("Training %s for some time.",super.specifics.get("name")));
    }}

    @Override
    public ArrayList<String> getSpecifics() {
        
        String temp = (this.isTraied)?"":"n't";
        ArrayList<String> tmp = super.getSpecifics();
        tmp.add(String.format("%s is%s trained",super.getName(),temp));
        return tmp;
    }
    @Override
    public void specialAction() {
        super.specialAction();
        this.TrainMore();
    }
}

class Wolf extends WildAnimal{
    
    private boolean isLeader;

    public Wolf(
        general_on_animal_inputClass info, 
        specifics_on_animal_wild more_info,
        boolean isLeader){
            
            super(info,more_info);
            this.isLeader =isLeader;
        }
    public boolean getIsLeader(){
        return this.isLeader;
    }
    
    @Override
    public ArrayList<String> getSpecifics() {
        String temp = (this.isLeader)?"":"n't";
        ArrayList<String> tmp = super.getSpecifics();
        tmp.add(String.format("This %s is%s leader.",this.getBreed(), temp));
        return tmp;
    }
    
}

class Chicken extends bird{
    
    public Chicken (general_on_bird info){
        super(info);

}
}

class Stork extends bird {//аист
    public Stork (general_on_bird info){
        super(info);

}
}

class Parrot extends bird{
    public Parrot(general_on_bird info){
        super(info);
    }
}
class Test{
    public static void main(String[] args) {
        // cat
        general_on_animal_inputClass onBarsik_c= new general_on_animal_inputClass(
            "at home", 7, "cat food", "meow");
        specifics_on_animal_domestic_inputClass onBarsic_s = 
            new specifics_on_animal_domestic_inputClass("Barsik"
            , "polar grey", "green", "grey", "nyai", 
            LocalDate.of(2022,1,1), true, 23, 3);
        Cat Barsik = new Cat(onBarsik_c, onBarsic_s, true);
        // Barsik.askAttention();
        // Barsik.getInfo();
        // Tiger
        general_on_animal_inputClass onTiger_c= new general_on_animal_inputClass(
            "Africa", 20, "slow and weak animals", "leave.");
        specifics_on_animal_wild onTiger_s = 
            new specifics_on_animal_wild(
                "bengal", "pretty!", "gimmeFood!", LocalDate.of(2000,2,2), 80, 90);
        Tiger Jorik = new Tiger(onTiger_c, onTiger_s);
        // Jorik.getInfo();
        // Dog
        general_on_animal_inputClass onDog_c = new general_on_animal_inputClass("around here", 13, "cats", "bark");
        specifics_on_animal_domestic_inputClass onDog_s = new specifics_on_animal_domestic_inputClass(
                "Richi", "haskee", "blue and green", "pinkey grey",
                "ohnonnon", LocalDate.of(2003,3,3), false, 50, 8);
        Dog Richi = new Dog(onDog_c, onDog_s, true);
        Dog Archi = new Dog(onDog_c, onDog_s, false);
        // Richi.getInfo();
        // Richi.TrainMore();
        // Archi.TrainMore();
        // WOlf
        general_on_animal_inputClass onWolf_c= new general_on_animal_inputClass("forests", 16, "tigers", "graw");
        specifics_on_animal_wild onWolf_s = new specifics_on_animal_wild("arctic wolf", "blue", "rgh", LocalDate.of(2003,2,2), 5, 5);
        Wolf Nyamka = new Wolf(onWolf_c, onWolf_s, true);
        Nyamka.getInfo();
        // Chicken
        general_on_bird onChicken = new general_on_bird("Polish Chicken", "brown", "okkoko", 2, 30, 2);
        Chicken Kurka = new Chicken(onChicken);
        Kurka.getInfo();
        System.out.println(Kurka.iFly());
        //Stork
        general_on_bird onStork = new general_on_bird("Pretty Stork", "blue", "tic-tac", 50, 120, 7);
        Chicken Stork_bird = new Chicken(onStork);
        Stork_bird.getInfo();
        System.out.println(Stork_bird.iFly());
    }
}