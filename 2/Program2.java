import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;

public class Program2 {
    
    public static void main(String[] args) {
        Scanner replLoop= new Scanner( System.in);
        Zoo newZoo = new Zoo();

        //#region randomPets

        general_on_animal_inputClass onPuma_g = new general_on_animal_inputClass("Africa", 18, "slow mammals", "turn back.");
        specifics_on_animal_wild onPuma_s = new specifics_on_animal_wild("Black Puma", "brown", "gruuu", LocalDate.of(2020,3,1), 80, 96);
        WildAnimal puma = new WildAnimal(onPuma_g, onPuma_s);
    
        general_on_animal_inputClass onCatBarsik_g = new general_on_animal_inputClass("around humans", 15, "catfood", "meow");
        specifics_on_animal_domestic_inputClass onCatBarsic_s = new specifics_on_animal_domestic_inputClass("Barsik", "Irish Drunkard", "grey", "white", "oh long johnson", LocalDate.of(2019,3,3), true, 23, 7);
        DomesticAnimal catBarsic = new DomesticAnimal(onCatBarsik_g, onCatBarsic_s);
     
        general_on_bird onParrot = new general_on_bird("parrot Cacadoo", "blue", "LalaLa", 50, 50, 2);
        Parrot dreadnought = new Parrot(onParrot);
        // cat
        general_on_animal_inputClass onBarsik_c= new general_on_animal_inputClass(
            "at home", 7, "cat food", "meow");
        specifics_on_animal_domestic_inputClass onBarsic_s = 
            new specifics_on_animal_domestic_inputClass("Barsik"
            , "polar grey cat", "green", "grey", "nyai", 
            LocalDate.of(2022,1,1), true, 23, 3);
        Cat Barsik = new Cat(onBarsik_c, onBarsic_s, true);

        // Tiger
        general_on_animal_inputClass onTiger_c= new general_on_animal_inputClass(
            "Africa", 20, "slow and weak animals", "leave.");
        specifics_on_animal_wild onTiger_s = 
            new specifics_on_animal_wild(
                "bengal tiger", "pretty!", "gimmeFood!", LocalDate.of(2000,2,2), 80, 90);
        Tiger Jorik = new Tiger(onTiger_c, onTiger_s);
        

        general_on_animal_inputClass onDog_c = new general_on_animal_inputClass("around here", 13, "cats", "bark");
        specifics_on_animal_domestic_inputClass onDog_s_a = new specifics_on_animal_domestic_inputClass(
                "Archi", "dog haskee", "blue and green", "pinkey grey",
                "ohnonnon", LocalDate.of(2003,3,3), false, 50, 8);
                specifics_on_animal_domestic_inputClass onDog_s_r = new specifics_on_animal_domestic_inputClass(
                "Richi", "dog haskee", "blue and green", "pinkey grey",
                "ohnonnon", LocalDate.of(2003,3,3), false, 50, 8);
        Dog Richi = new Dog(onDog_c, onDog_s_r, true);
        Dog Archi = new Dog(onDog_c, onDog_s_a, false);

        general_on_animal_inputClass onWolf_c= new general_on_animal_inputClass("forests", 16, "tigers", "graw");
        specifics_on_animal_wild onWolf_s = new specifics_on_animal_wild("arctic wolf", "blue", "rgh", LocalDate.of(2003,2,2), 5, 5);
        Wolf Nyamka = new Wolf(onWolf_c, onWolf_s, true);
        
        
        general_on_bird onChicken = new general_on_bird("Polish Chicken", "brown", "okkoko", 2, 30, 2);
        Chicken Kurka = new Chicken(onChicken);
        
        
        //Stork
        general_on_bird onStork = new general_on_bird("Pretty Stork", "blue", "tic-tac", 50, 120, 7);
        Stork Stork_bird = new Stork(onStork);
        Stork_bird.getInfo();
        System.out.println(Stork_bird.iFly());


        animals_general[] pool = new animals_general[]{
            puma,catBarsic,dreadnought,Barsik,Jorik,Richi,Archi,Nyamka,Kurka,Stork_bird
        };

        //#endregion
        
        String buffer = "";
        String help = String.join("\n",
            "\"addpet()\" to add new random pet.",//+
            "\"removepet(ind)\" - to remove ind pet",
            "\"info(ind)\" - info on ind pet",
            "\"allInfo()\" - info on all added pets",//+
            "\"wakeUp(ind)\" - ind animal shouts something",
            "\"special(ind)\" - does something specific( as in dog is trained, bird flies"+
            " and domestic animal asks for care",
            "\"meeting()\" - all pets shout and do their thing",//+
            "\"help()\" - shows readme again",//+
            "\"qqq\" stands for exit"//+
            );
            Matcher listener_m;
            Pattern patternRemovepet = Pattern.compile("(^removepet\\()(\\d+)(\\)$)");
            Pattern patternGetInfo = Pattern.compile("(^info\\()(\\d+)(\\)$)");
            Pattern patternWakeUp = Pattern.compile("(^wakeUp\\()(\\d+)(\\)$)");
            Pattern patternSpecialAction = Pattern.compile("(^special\\()(\\d+)(\\)$)");
            // Matcher getInfo_m;
            // Matcher wakeup_m;
            // Matcher special_m;

            System.out.println(help);

        while (true) {
            
            System.out.println("listening...");
            buffer = replLoop.nextLine();
            Matcher matcherRemovepet = patternRemovepet.matcher(buffer);
            Matcher matcherGetInfo = patternGetInfo.matcher(buffer);    
            Matcher matcherWakeUp = patternWakeUp.matcher(buffer);
            Matcher matcherSpecialAction = patternSpecialAction.matcher(buffer);
            if (buffer.equals("qqq")){
                System.out.println("got it!");
                System.exit(0);
            }
            else if (buffer.equals("help()")){
                System.out.println(help);

            }
            else if (buffer.equals("addpet()")){
                Random index_root = new Random();
                int index = index_root.nextInt(pool.length);
                newZoo.appendAnimal(pool[index]);
                System.out.println(String.format("Added %s!",pool[index].getBreed()));
            }
            else if (buffer.equals("allInfo()")){
                newZoo.Excursion();
            }
            else if (buffer.equals("meeting()")){
                newZoo.Meeting();
            }
            
            else if (matcherRemovepet.find()){
                
                System.out.println("logic on removepet");
                newZoo.removeAnimal(Integer.valueOf(matcherRemovepet.group(2)));
            }
            else if (matcherGetInfo.find()){
                System.out.println("logic on info");
                newZoo.getData(Integer.valueOf(matcherGetInfo.group(2)));
            }
            else if (matcherWakeUp.find()){
                System.out.println("logic on voice");
                newZoo.standAndSing(Integer.valueOf(matcherWakeUp.group(2)));
            }
            else if (matcherSpecialAction.find()){
                System.out.println("logic on special action");
                newZoo.trick(Integer.valueOf(matcherSpecialAction.group(2)));
            }
    

            else {
                System.out.println("Yeah, how about no.");
            }
            
        }
    }
    
}
