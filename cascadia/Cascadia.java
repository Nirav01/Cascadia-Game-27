package cascadia;

import java.util.Collections;

public class Cascadia {
    public static void main(String[] args) {
       
        View view = new cascadia.View();
        view.GameStartMessage();                               //Game starts
        int players = view.getPlayers();
        Player[] playerList = new Player[players+1];
        view.namePlayers(playerList);
        //view.turnOrder(playerList);

        //Generate stack of 85 Habitat Tiles
        TilePile habitatPile = new TilePile();
        //Shuffle pile        
        habitatPile.shuffle();
        //Pop elements until correct size is reached    size = (20*players)+3
        habitatPile.reducePile(players);

        //Generate list of 100 Wildlife Tokens
        TokenPile wildlifPile = new TokenPile();
        //Shuffle pile
        wildlifPile.shuffle();
    }
}
