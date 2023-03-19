import java.util.Scanner;
public class Program {
    
    public static void main(String[] args) {
        Scanner replLoop= new Scanner( System.in);
        Zoo newZoo = new Zoo();
        String buffer = "";
        while (!buffer.equals("quit")) {
            System.out.println(String.join("\n",
            "\"addpet\" to add new random pet.",
            "\"removepet(ind)\" - to remove ind pet",
            "\"info(ind)\" - info on ind pet",
            "\"allInfo\" - info on all added pets",
            "\"wakeUp(ind)\" - ind animal shouts something",
            "\"special(ind)\" - does something specific( as in dog is trained, bird flies"+
            " and domestic animal asks for care"

            ));
            System.out.println("listening...");
            buffer = replLoop.nextLine();
            System.out.println(buffer);
            
        }
    }
    
}
