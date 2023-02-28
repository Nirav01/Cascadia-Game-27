package cascadia;

//5 Environments:   River, Wetland, Forest, Prairie, Mountain
import java.util.Random; 

public enum Habitat {
    RIVER,
    WETLAND,
    FOREST,
    PRAIRIE,
    MOUNTAIN;

    //Returns a random Wildlife enum
    public static Habitat randomHabitat(){
        Habitat[] options = Habitat.values();
        int randomIndex = new Random().nextInt(options.length);
        return options[randomIndex];
    }
}