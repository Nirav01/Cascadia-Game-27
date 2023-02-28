package cascadia;

import java.util.*;


public class TilePile extends Stack<HabitatTile> {
    //Generate 85 Habitat Tiles (25 keystones + 60 dual-habitat tiles)
    TilePile() {
       super();
       //25 keystones = 5 Habitats x 5 Wildlife
       for (Habitat habitat: Habitat.values()){
            for(Wildlife wildlife: Wildlife.values()){
                super.add(new HabitatTile(habitat, wildlife));
            }
       }
       //Remaining 60 tiles
       for(int i=0;i<60;i++){
        super.add(new HabitatTile(Habitat.randomHabitat(), Habitat.randomHabitat()));
       }
    }

    //Shuffle Stack
    public void shuffle() {
        Collections.shuffle(this);
    }

    //Reduce Stack size
    public void reducePile(int players){
        //Calculate amount of useable tokens
        int target = (players*20)+3;
        
        //Take off first element until desired size
        while(this.size()>target){
            this.pop();
        }
    }
}
