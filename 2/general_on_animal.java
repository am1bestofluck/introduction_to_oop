import java.util.Hashtable;

/**
 * general
 */
public class general_on_animal {

    private Hashtable<String,Object> dossier= new Hashtable<>();

    public general_on_animal(String whereLives,Integer howLongLives, String whatEats,
    String whatTells){
        this.dossier.put("habitant",whereLives);
        this.dossier.put("expectedAge", howLongLives);
        this.dossier.put("preferredFood", whatEats);
        this.dossier.put("feedback",whatTells);

    }
}