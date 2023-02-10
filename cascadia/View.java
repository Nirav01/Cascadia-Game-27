package cascadia;
//This class displays to the console and gets user input from keyboard.

import java.util.*;

public class View {
    //Game Introduction
    Scanner in = new Scanner(System.in);
    public void GameStartMessage(){
        System.out.println("\nWELCOME TO CASCADIA!");
        System.out.println("\nAbout the Game:");
        System.out.println("In Cascadia, players compete to create the most diverse environment using Habitat Tiles and Wildlife Tokens.");
        System.out.println("Scoring is calculated from combinations of different objectives linked to each wildlife species.");
        System.out.println("The goal of the game is to build out habitats and populate them with the best wildlife in order to create a harmonious ecosystem.\n");
        System.out.println("\nLet's start! How many players are playing? Please enter number 2-4.");
    }

    //Gets number of players
    public int getPlayers(){
        int playerAmount = 0;
        do{
            playerAmount = in.nextInt();
            if(playerAmount<2||playerAmount>4){
                displayInvalidAction();
            }
        }while(playerAmount<2||playerAmount>4);
        return playerAmount;
    }

    //Error Handling
    public void displayInvalidAction(){
        System.out.println("Invalid input, please try again.");
    }

    //Gets Player names
    public void namePlayers(Player[] playerList) {
        String username;
        System.out.println("Please enter each player's name:");
        for(int i=0;i<playerList.length;i++){
            username = in.nextLine();
            Player p = new Player(username);
            playerList[i] = p;
        }
    }
    /*public void playOrder(){
        Collections.shuffle(playerList);
        for(int i = 1; i <= playerAmount; i++){
            System.out.println(i + ". " + playerList(i));
        }
    }*/
}


