import java.time.LocalDate;
import java.util.Stack;

import javax.management.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Zoo
 */
public class Zoo {

    private animals_general[] content;
    private animals_general[] temporary;

    public Zoo(Queue<animals_general> queueIncoming){
        this.content = new animals_general[queueIncoming.size()];
        Integer i = 0;
        while( queueIncoming.peek()!= null){
            this.content[i] = queueIncoming.poll();
            i++;

        }
    }

    public Zoo( animals_general[] arrayIncoming){
        this.content = arrayIncoming;
    }
    
    public Zoo (Stack<animals_general> stackIncoming){
        
        this.content = new animals_general[stackIncoming.size()];
        for (int i = 0; i < this.content.length; i++) {
            this.content[i] = stackIncoming.pop();
                }
        }
    
    public Zoo(){
        this.content = new animals_general[]{};
    }


    public animals_general[] getAllAnimals(){
        return this.content;
    }



    public void appendAnimal(animals_general newAnimal){

    this.temporary = new animals_general[this.content.length+1];
    if (this.content.length >0){
    for (int i=0; i <this.content.length; i++) {
        this.temporary[i] = this.content[i];
    }}
    this.temporary[this.temporary.length-1] = newAnimal;
    this.content = this.temporary;
    this.temporary =  new animals_general[]{};
    }

    public void removeAnimal( int removeIt){
        if ((removeIt<0) || (removeIt>=this.content.length)){
            System.out.println("inadequate index");
            return;
        }

        if (this.content.length == 1){
            this.content = new animals_general[]{};
            return;
        }

        this.temporary = new animals_general[this.content.length-1];

        for (int i = 0; i < removeIt; i++) {
           this.temporary[i] = this.content[i]; 
        }
        for (int i = this.content.length-1; i > removeIt; i--) {
            this.temporary[i-1] = this.content[i];
        }
        this.content = this.temporary;
        this.temporary = new animals_general[]{};
    }
    public void  getData( int index){
        if ((index<0) || (index>=this.content.length)){
            System.out.println("inadequate index");
            return;
        }
        this.content[index].getInfo();
    }
    public void standAndSing(int index){
        System.out.println(String.format("%s goes \"%s\"!",this.content[index].getBreed(), this.content[index].SayYourThing()));
    }

    public void Excursion(){
        for (animals_general animal : this.content) {
            System.out.println("###");
            animal.getInfo();
        }
    }
    public void Meeting(){
        ArrayList<Integer> sample = new ArrayList<>();
        for (int i = 0; i < this.content.length; i++) {
            sample.add(i);    
        }
        Collections.shuffle(sample);
        for (Integer integer : sample) {
            this.standAndSing(integer);
            this.trick(integer);
            
        }
    
    }
    public void trick(int index){
        Object pet = this.content[index];
        if (pet instanceof Dog){
            Dog temp = (Dog) pet;
            temp.specialAction();
        }
        else if (pet instanceof bird){
            bird temp = (bird) pet;
            temp.specialAction();
        }
        else if (pet instanceof DomesticAnimal){
            DomesticAnimal temp = (DomesticAnimal) pet;
            temp.specialAction();
        }
    }

    public void allSing(){
        ArrayList<Integer> sample = new ArrayList<>();
        for (int i = 0; i < this.content.length; i++) {
            sample.add(i);    
        }
        Collections.shuffle(sample);
        for (Integer integer : sample) {
            this.standAndSing(integer);
        }
        
    }
    
    



    public static void main(String[] args) {


        general_on_animal_inputClass onPuma_g = new general_on_animal_inputClass("Africa", 18, "slow mammals", "turn back.");
        specifics_on_animal_wild onPuma_s = new specifics_on_animal_wild("Black Puma", "brown", "gruuu", LocalDate.of(2020,3,1), 80, 96);
        WildAnimal puma = new WildAnimal(onPuma_g, onPuma_s);
    
        general_on_animal_inputClass onCatBarsik_g = new general_on_animal_inputClass("around humans", 15, "catfood", "meow");
        specifics_on_animal_domestic_inputClass onCatBarsic_s = new specifics_on_animal_domestic_inputClass("Barsik", "Irish Drunkard", "grey", "white", "oh long johnson", LocalDate.of(2019,3,3), true, 23, 7);
        DomesticAnimal catBarsic = new DomesticAnimal(onCatBarsik_g, onCatBarsic_s);
     
        general_on_bird onParrot = new general_on_bird("Cacadoo", "blue", "LalaLa", 50, 50, 2);
        Parrot dreadnought = new Parrot(onParrot);
        // cat
        general_on_animal_inputClass onBarsik_c= new general_on_animal_inputClass(
            "at home", 7, "cat food", "meow");
        specifics_on_animal_domestic_inputClass onBarsic_s = 
            new specifics_on_animal_domestic_inputClass("Barsik"
            , "polar grey", "green", "grey", "nyai", 
            LocalDate.of(2022,1,1), true, 23, 3);
        Cat Barsik = new Cat(onBarsik_c, onBarsic_s, true);

        // Tiger
        general_on_animal_inputClass onTiger_c= new general_on_animal_inputClass(
            "Africa", 20, "slow and weak animals", "leave.");
        specifics_on_animal_wild onTiger_s = 
            new specifics_on_animal_wild(
                "bengal", "pretty!", "gimmeFood!", LocalDate.of(2000,2,2), 80, 90);
        Tiger Jorik = new Tiger(onTiger_c, onTiger_s);
        

        general_on_animal_inputClass onDog_c = new general_on_animal_inputClass("around here", 13, "cats", "bark");
        specifics_on_animal_domestic_inputClass onDog_s = new specifics_on_animal_domestic_inputClass(
                "Richi", "haskee", "blue and green", "pinkey grey",
                "ohnonnon", LocalDate.of(2003,3,3), false, 50, 8);
        Dog Richi = new Dog(onDog_c, onDog_s, true);
        Dog Archi = new Dog(onDog_c, onDog_s, false);

        general_on_animal_inputClass onWolf_c= new general_on_animal_inputClass("forests", 16, "tigers", "graw");
        specifics_on_animal_wild onWolf_s = new specifics_on_animal_wild("arctic wolf", "blue", "rgh", LocalDate.of(2003,2,2), 5, 5);
        Wolf Nyamka = new Wolf(onWolf_c, onWolf_s, true);
        
        
        general_on_bird onChicken = new general_on_bird("Polish Chicken", "brown", "okkoko", 2, 30, 2);
        Chicken Kurka = new Chicken(onChicken);
        
        
        //Stork
        general_on_bird onStork = new general_on_bird("Pretty Stork", "blue", "tic-tac", 50, 120, 7);
        Chicken Stork_bird = new Chicken(onStork);
        Stork_bird.getInfo();
        System.out.println(Stork_bird.iFly());
    
       
    
        Zoo zoo1 = new Zoo();
        zoo1.appendAnimal(puma);
        zoo1.appendAnimal(catBarsic);
        zoo1.appendAnimal(dreadnought);
        System.out.println(zoo1.getAllAnimals()[0].SayYourThing());
        zoo1.Excursion();

        animals_general[] arrayTest = new animals_general[]{puma,catBarsic,dreadnought};
        Zoo zoo2 = new Zoo(arrayTest);
        System.out.println(zoo2.getAllAnimals()[1].SayYourThing());

        Stack<animals_general> testStack = new Stack<>();
        testStack.add(puma);
        testStack.add(catBarsic);
        testStack.add(dreadnought);
        Zoo zoo3 = new Zoo(testStack);
        System.out.println(zoo3.getAllAnimals()[2].SayYourThing());

        System.out.println("queue");
        Queue<animals_general> testQueue = new LinkedList<>();
        testQueue.add(puma);
        testQueue.add(catBarsic);
        testQueue.add(dreadnought);
        Zoo zoo4 = new Zoo(testQueue);
        System.out.println(zoo4.getAllAnimals()[0].SayYourThing());
        System.out.println(zoo4.getAllAnimals()[1].SayYourThing());
        System.out.println(zoo4.getAllAnimals()[2].SayYourThing());
        System.out.println("removed");
        zoo4.removeAnimal(1);
        System.out.println(zoo4.getAllAnimals()[0].SayYourThing());
        System.out.println(zoo4.getAllAnimals()[1].SayYourThing());
        // System.out.println(zoo4.getAllAnimals()[2].SayYourThing());
        zoo4.getData(1);
        zoo4.standAndSing(0);
        System.out.println("all sing");
        zoo4.allSing();
}
}