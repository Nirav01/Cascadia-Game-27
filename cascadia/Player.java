package cascadia;

import java.util.ArrayList;

public class Player {
    String name;
    int score;
    ArrayList<HabitatTile> playerHabitats;
    ArrayList<Wildlife> playerWildlife;
    StarterTile startHab = null;

    public Player(String name){
        this.name=name;
        this.playerHabitats = new ArrayList<>(50);
        this.playerWildlife = new ArrayList<>(50);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void giveStart(StarterTile t){
        this.startHab = t;
    }

    public void addTile(HabitatTile h){
        this.playerHabitats.add(h);
    }

    public void addToken(Wildlife w){
        this.playerWildlife.add(w);
    }

}
