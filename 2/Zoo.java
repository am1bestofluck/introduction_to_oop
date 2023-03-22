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