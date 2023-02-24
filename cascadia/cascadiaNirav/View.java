package cascadia;
//This class displays to the console and gets user input from keyboard.

import java.util.*;

public class View {
    //Game Introduction
    Scanner in = new Scanner(System.in);
    public void GameStartMessage(){
        System.out.println("\nWELCOME TO CASCADIA!");
        System.out.println("\nAbout the Game:");
        System.out.println("In Cascadia, players use  Habitat Tiles and Wildlife Tokens to compete to create the most diverse environment.");
        System.out.println("Scoring is calculated from combinations of different objectives linked to each wildlife species.");
        System.out.println("The goal of the game is to build out habitats and populate them with the best wildlife in order to create a harmonious ecosystem.\n");
        System.out.println("\nLet's start! How many players are playing? Please enter number 2-4.");
    }

    //Gets number of players
    public int getPlayers(){
        int playerAmount = 0;
        String test = "";
        int check = 0;
        int check2;
        do{
            System.out.println("Please enter a value: ");
            test = in.nextLine();
            try {
                check2 = Integer.valueOf(test);
                //Integer.parseInt(test);
                if(check2 > 4 || check2 < 2){
                    displayInvalidAction();
                    check = 0;
                }
                else{
                    playerAmount = check2;
                    check = 1;
                }
            } catch (NumberFormatException e) {
                // TODO: handle exception
                displayInvalidAction();
            }
            // if(in.hasNextInt()){
            
            //     if(playerAmount != 2 || playerAmount != 3 || playerAmount != 4){
            //         displayInvalidAction();
            //         check = 0;
            //     }
            //     else{
            //         check = 1;
            //     }
            // }          
            // else{
            //     displayInvalidAction();
            //     System.out.println("Please enter an integer value");
            //     playerAmount = in.nextInt();
            //     check = 0;
            // }
        }while(check == 0); 
        return playerAmount;
    }

    //Error Handling
    public void displayInvalidAction(){
        System.out.println("Invalid input, please try again.");
    }

    //Gets Player names
    public String[] getPlayerList(int numOfPlayers) {

        String[] playerList = new String[numOfPlayers];

        for (int i = 0; i < numOfPlayers; i++) {
            System.out.println("Enter player " + (i + 1) + "'s name:");
            String playerName = in.nextLine();
            playerList[i] = playerName;
        }
        return playerList;
    }


    public String[] playOrder(String[] playerList) {
        Random rand = new Random();
        for (int i = playerList.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            String temp = playerList[index];
            playerList[index] = playerList[i];
            playerList[i] = temp;
        }
        return playerList;
    }

    public void printPlayerList(String[] playerList) {
        System.out.println("The player list is:");
        for (int i = 0; i < playerList.length; i++){
            System.out.println(playerList[i]);
        }
    }

    public void nextPlayerHabitat() {
        System.out.println("Enter \"next\" to see next player's habitat and \"no\" to continue");
        String input = in.nextLine().toString();
        if (input == "next"){
            System.out.println(in.next());
        }
        else if (input == "no"){
            System.out.println(input);
        }
        else{
            displayInvalidAction();
        }
        return input;
    }
}



