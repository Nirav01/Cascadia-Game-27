package cascadia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Cascadia {
    public static void main(String[] args) {
       
        View view = new cascadia.View();
        view.GameStartMessage();                               //Game starts
        int players = view.getPlayers();
        Player[] playerList = new Player[players+1];
        view.namePlayers(playerList);

        //Generate stack of 85 Habitat Tiles
        TilePile habitatPile = new TilePile();
        //Shuffle pile        
        habitatPile.shuffle();
        //Pop elements until correct size is reached    size = (20*players)+3
        habitatPile.reducePile(players);

        //Generate list of 100 Wildlife Tokens
        TokenPile wildlifePile = new TokenPile();
        //Shuffle pile
        wildlifePile.shuffle();

        //Generate Starter Tiles
        ArrayList<StarterTile> starterTiles = new ArrayList<>();
        for(Wildlife w:Wildlife.values()){
            starterTiles.add(new StarterTile(w));
        }

        //Distribute Starter Tile to every player
        Collections.shuffle(starterTiles);
        int index=0;
        for(Player p:playerList){
            p.giveStart(starterTiles.get(index++));
        }

        //---Game Loop Starts---
        //Each player picks 1 of 4 Habitat Tiles available and receives the Wildlife token of the
        //same index.
    
    }
}
