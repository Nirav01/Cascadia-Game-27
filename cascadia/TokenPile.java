package cascadia;

//Class generates 100 Wildlife Tokens, 20 of each animal type
//Linked list used to accomodate 'culling' mechanic
//Discarded tokens are added back to the list after player's turn

import java.util.*;

public class TokenPile extends LinkedList<Wildlife> {
    TokenPile(){
        super();
        //Iterate though each animal
        for(Wildlife animal:Wildlife.values()){
            int i=0;
            //Add it to TokenPile 20 times
            while(i<20){
                super.add(animal);
                i++;
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(this);
    }
    
}
