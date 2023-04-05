import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.io.*;


public class Casscadia implements ActionListener {

String userName;
    public int NumhabitatTiles; //used to store number of habitat tiles that will be used depending on number of players

    public Casscadia() throws IOException {
    }

    static String CurrentText = "Click any of the 4 habitats above to begin"; //this is the text that is displayed at the bottom of the window. 
    static String turnInfo = "Hello Player "; //this is the text that is displayed on the bottom right of the window

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    enum Habitatselect {forest, wetland, river, mountain, prairie,none} //used when implementing habitat class. See "Habitat.java".

    enum Animalselect {Hawk, Bear, Elk, Salmon, Fox, none} //used when implementing animal class. See "Animal.java".

    ArrayList<Player> Players = new ArrayList<Player>(); //this is a list of the players playing the game
    ArrayList<Animal> AnimalCards = new ArrayList<Animal>(); //this is the pot of animal tokens from which the player selects one each rounds
    ArrayList<Habitat> HabitatCards = new ArrayList<Habitat>(); //this is the pot of habitat tiles from which the player selects one each rounds
    Habitat currentHabitat = null; //this is the current habitat the user has in his hand.
    Animal currentAnimal = null; //this is the current animal token the user has in his hand.
    boolean useWildlifeToken = false; //boolean used when the user wants to use a wildlife token.
    boolean canReshuffle = false; //boolean value to check if a reshuffle is possible
    int currentRotation = 0; //this variable is used when a tile is being rotated.
    int PlayerTurn = 0; //variable that stores which players turn it is
    int turns = 1; //keeps track of how many turns have happened
    int NumPLayers; //number of players in the game
    static int animalIndex;

    public static void main(String[] args) throws IOException {
        Casscadia hello = new Casscadia(); //new instance of the casscadia class which the whole game runs on
        hello.Initialprompt(); //gets data needed to start the game
        hello.Turn();

        //new JFRAME to run game
        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 1000, 650);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);  //sets jframe to middle of screen
        JPanel pn = new JPanel() {

            @Override
            public void paint(Graphics g) {
                //draws square grid/board
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 15; x++) {

                        if (y % 2 != 0) {
                            g.drawRect(x * 64 + 32, y * 64, 64, 64);
                        } else {
                            g.drawRect(x * 64, y * 64, 64, 64);
                        }
                    }
                }
                //ends game after 40 turns
                if (hello.turns>40){
                    return;
                }

                //Displays the 4 tiles and animals that you can choose for each turn
                for (int i = 0; i < 4; i++) {
                    g.drawImage(hello.AnimalCards.get(i).animalImage, 154 + (64 * i), 576, this);
                    g.drawImage(hello.HabitatCards.get(i).habitatImage, 154 + (64 * i), 518, this);
                    for (int l = 0; l < hello.HabitatCards.get(i).numPossibleAnimals; l++) {
                        g.drawImage(hello.HabitatCards.get(i).possibleAnimals.get(l).possibleImage, 154 + (64 * i) + (15 * l), 518, this);
                    }
                }

                //Displays the current board of the current players turn with all of his/her tiles
                for (int z = 0; z < hello.Players.get(hello.PlayerTurn).allTiles.size(); z++) {
                    g.drawImage(hello.Players.get(hello.PlayerTurn).allTiles.get(z).habitat.habitatImage, hello.Players.get(hello.PlayerTurn).allTiles.get(z).xPosition, hello.Players.get(hello.PlayerTurn).allTiles.get(z).yPosition, this);
                    if (hello.Players.get(hello.PlayerTurn).allTiles.get(z).isAnimal == true) {
                        g.drawImage(hello.Players.get(hello.PlayerTurn).allTiles.get(z).animal.animalImage, hello.Players.get(hello.PlayerTurn).allTiles.get(z).xPosition+16, hello.Players.get(hello.PlayerTurn).allTiles.get(z).yPosition+16, this);
                    } else {
                        for (int l = 0; l < hello.Players.get(hello.PlayerTurn).allTiles.get(z).habitat.numPossibleAnimals; l++) {
                            g.drawImage(hello.Players.get(hello.PlayerTurn).allTiles.get(z).habitat.possibleAnimals.get(l).possibleImage, hello.Players.get(hello.PlayerTurn).allTiles.get(z).xPosition + (l * 15), hello.Players.get(hello.PlayerTurn).allTiles.get(z).yPosition , this);
                        }
                    }
                }


                hello.ReshuffleCheck();
                //button that can be used if a reshuffle is available otherwise useless
                JButton yes = new JButton("Cull");
                JButton next = new JButton("Next");
                JButton quit = new JButton("Quit");

                yes.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (Boolean.compare(hello.canReshuffle, true) == 0) {
                            Collections.shuffle(hello.AnimalCards);
                            frame.repaint();
                        }
                    }
                });
                yes.setBounds(0, 512, 100, 50);
                next.setBounds(530,515,100,50);
                quit.setBounds(530,565,100,50);
                frame.add(quit);
                frame.add(yes);
                yes.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        hello.currentRotation=1;
                    }
                });
                frame.add(next);
                quit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        System.exit(1);
                    }
                });
                next.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        hello.plusPlayerTurn();

                    }
                });

                //button that can be used to use a wildlife token if the current player has more than 0 wildlife tokens
                    JButton wildlife_token = new JButton("Wildlife Token");
                    wildlife_token.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (hello.Players.get(hello.PlayerTurn).wildlifeTokens>0) {
                                hello.useWildlifeToken = true;
                                hello.currentAnimal = null;
                                hello.CurrentText="Please choose an animal";
                                frame.repaint();
                            }
                        }
                    });
                    wildlife_token.setBounds(0, 562, 120, 50);
                    frame.add(wildlife_token);

                //button used to rotate the tile that is currently selected otherwise of no tile is currently selected it will do nothing
                JButton rotate = new JButton("Rotate");
                rotate.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (hello.currentHabitat!=null) {
                            hello.plusRotation();
                            try {
                                hello.currentHabitat.rotateHabitat(hello.currentRotation);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            frame.repaint();
                        }
                    }
                });
                rotate.setBounds(412, 562, 100, 50);
                frame.add(rotate);

                //text box at the bottom of the window that displays what is in the CurrentText variable. 
                JTextField textbox = new JTextField();
                textbox.setSize(new Dimension(500, 38));
                textbox.setBounds(0, 612, 500, 38);
                textbox.setText(CurrentText);

                add(textbox);

                //text box at the bottom right of the window that displays what is in the turnInfo variable.
                JTextField turnInfoBox = new JTextField();
                turnInfoBox.setSize(new Dimension(102, 50));
                turnInfoBox.setBounds(410, 512, 102, 50);
                turnInfoBox.setText(turnInfo);

                add(turnInfoBox);

                //text box beside ethe wildlife token button that displays number of wildlife tokens the current player has. 
                JTextField numWildlifeTokens = new JTextField();
                numWildlifeTokens.setSize(new Dimension(50, 50));
                numWildlifeTokens.setBounds(120, 562, 20, 50);
                numWildlifeTokens.setText(String.valueOf(hello.Players.get(hello.PlayerTurn).wildlifeTokens));

                add(numWildlifeTokens);

                //text box beside reshuffle button that displays whether you can reshuffle.
                JTextField CanReshuffle = new JTextField();
                CanReshuffle.setSize(new Dimension(50, 50));
                CanReshuffle.setBounds(100, 512, 50, 50);
                if (hello.canReshuffle==true){
                CanReshuffle.setText("Yes");
                }
                else {CanReshuffle.setText("No");}
                add(CanReshuffle);
            }
        };

        frame.add(pn);
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
            @Override
            public void mousePressed(MouseEvent e) { //code that controls what happens when the mouse if pressed at different locations in the window
                int x;  //used to find x co-ordinate of the tile you have clicked on the grid
                int y = Math.floorDiv((e.getY()), 64); //used to find y co-ordinate of the tile you have clicked on the grid
                if (e.getX()<32){
                    x=9;
                }
                else if (y%2!=0){
                    x = Math.floorDiv(e.getX()-32, 64);
                }
                else{x = Math.floorDiv(e.getX(), 64);}

              if (hello.currentHabitat != null && x < 8 && y < 8 && hello.Players.get(hello.PlayerTurn).Board[x][y] == null && hello.Players.get(hello.PlayerTurn).canPlace(x,y)) { //code to place a habitat card
                    BoardTile newtile = null;
                    try {
                        newtile = new BoardTile(hello.currentHabitat, x, y,hello.currentRotation);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    hello.Players.get(hello.PlayerTurn).Board[x][y] = newtile;
                    hello.Players.get(hello.PlayerTurn).allTiles.add(newtile);
                    hello.currentAnimal = hello.AnimalCards.get(animalIndex);
                    frame.repaint();
                    hello.currentHabitat = null;
                    hello.currentRotation=0;
                    hello.CurrentText = ("Please place " + hello.currentAnimal.AnimalName+" or use a wildlife token");
                } else if (hello.currentAnimal != null && x < 8 && y < 8 && hello.Players.get(hello.PlayerTurn).Board[x][y] != null && hello.Players.get(hello.PlayerTurn).Board[x][y].isAnimal == false) { //code to place an animal card
                    if (hello.Players.get(hello.PlayerTurn).Board[x][y].habitat.isPossible((hello.currentAnimal)) == true) {
                        hello.Players.get(hello.PlayerTurn).Board[x][y].addAnimal(hello.currentAnimal);
                        for (int z = 0; z < hello.Players.get(hello.PlayerTurn).allTiles.size(); z++) {
                            if (hello.Players.get(hello.PlayerTurn).allTiles.get(z).X == x && hello.Players.get(hello.PlayerTurn).allTiles.get(z).Y == y) {
                                hello.Players.get(hello.PlayerTurn).allTiles.get(z).addAnimal(hello.currentAnimal);
                                hello.Players.get(hello.PlayerTurn).allTiles.get(z).isAnimal = true;
                            }
                        }
                        if (hello.Players.get(hello.PlayerTurn).Board[x][y].habitat.habitat2==Habitatselect.none){
                            hello.Players.get(hello.PlayerTurn).wildlifeTokens++;
                        }
                        hello.HabitatCards.remove(animalIndex);
                        hello.AnimalCards.remove(animalIndex);
                        frame.repaint();
                        hello.currentAnimal = null;
                        hello.plusPlayerTurn();

                    } else {
                        hello.CurrentText=("You can't Place "+ hello.currentAnimal.AnimalName+ " here");
                        frame.repaint();
                    }

                } else if (hello.currentAnimal == null && hello.useWildlifeToken==false) { //code to select current habitat
                    if (e.getX() > 154 && e.getX() < 216 && e.getY() > 518 && e.getY() < 580) {
                        hello.currentHabitat = hello.HabitatCards.get(0);
                        animalIndex = 0;
                        hello.CurrentText = (hello.currentHabitat.getHabitatName() + " is selected");


                        frame.repaint();
                    } else if (e.getX() > 218 && e.getX() < 280 && e.getY() > 518 && e.getY() < 580) {
                        hello.currentHabitat = hello.HabitatCards.get(1);
                        animalIndex = 1;
                        hello.CurrentText = (hello.currentHabitat.getHabitatName() + " is selected");
                        frame.repaint();
                    } else if (e.getX() > 282 && e.getX() < 348 && e.getY() > 518 && e.getY() < 580) {
                        hello.currentHabitat = hello.HabitatCards.get(2);
                        animalIndex = 2;
                        hello.CurrentText = (hello.currentHabitat.getHabitatName() + " is selected");
                        frame.repaint();
                    } else if (e.getX() > 350 && e.getX() < 412 && e.getY() > 518 && e.getY() < 580) {
                        hello.currentHabitat = hello.HabitatCards.get(3);
                        animalIndex = 3;
                        hello.CurrentText = (hello.currentHabitat.getHabitatName() + " is selected");
                        frame.repaint();
                    }
                }
                else if (hello.currentAnimal == null && hello.useWildlifeToken==true) { //code when a wildlife token is used
                    if (e.getX() > 154 && e.getX() < 186 && e.getY() > 576 && e.getY() < 608) {
                        hello.currentAnimal = hello.AnimalCards.get(0);
                        animalIndex = 0;
                        hello.CurrentText = (hello.currentAnimal.getAnimalName() + " is selected");
                        frame.repaint();
                    } else if (e.getX() > 218 && e.getX() < 250 && e.getY() > 576 && e.getY() < 608) {
                        hello.currentAnimal = hello.AnimalCards.get(1);
                        animalIndex = 1;
                        hello.CurrentText = (hello.currentAnimal.getAnimalName() + " is selected");
                        frame.repaint();
                    } else if (e.getX() > 282 && e.getX() < 304 && e.getY() > 576 && e.getY() < 608) {
                        hello.currentAnimal = hello.AnimalCards.get(2);
                        animalIndex = 2;
                        hello.CurrentText = (hello.currentAnimal.getAnimalName() + " is selected");
                        frame.repaint();
                    } else if (e.getX() > 350 && e.getX() < 382 && e.getY() > 576 && e.getY() < 608) {
                        hello.currentAnimal = hello.AnimalCards.get(3);
                        animalIndex = 3;
                        hello.CurrentText = (hello.currentAnimal.getAnimalName() + " is selected");
                        frame.repaint();
                    }
                    hello.useWildlifeToken=false;
                    hello.Players.get(hello.PlayerTurn).wildlifeTokens--;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        frame.setVisible(true);

    }

    public void Initialprompt() throws IOException {   //Game Set-up
        Scanner NumPlayersScanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter number of players (2-4): ");
        NumPLayers = NumPlayersScanner.nextInt(); //asks user to input number of players
        if (NumPLayers < 2 || NumPLayers > 4) {
            throw new IllegalArgumentException("Incorrect number of users");
        }
        switch (NumPLayers) { //stores number of habitat tiles neccessary
            case 2:
                NumhabitatTiles = 43;
            case 3:
                NumhabitatTiles = 63;
            case 4:
                NumhabitatTiles = 83;
        }

        CreateTiles();

        for (int i = 0; i < NumPLayers; i++) {
if (i<1) {
    Scanner PlayerNameScanner = new Scanner(System.in);  //Create a Scanner object
    System.out.println("Enter the name for player " + (i + 1) + ":"); //User inputs Name
    userName = PlayerNameScanner.nextLine();
    Players.add(new Player(userName));
    if (userName.equals("S") || userName.equals("s")) {
        System.out.println("Player " + (i + 1) + "'s name is: " + userName + "\n");  //Output user input

    } else {
        System.out.println("Player " + (i + 1) + "'s name is: " + userName + "\n");  //Output user input
    }
    turnInfo=userName+"'s turn ";
}
else {
    Scanner PlayerNameScanner = new Scanner(System.in);  // Create a Scanner object
    System.out.println("Enter the name for player " + (i + 1) +" :"); //User inputs Name
    userName = PlayerNameScanner.nextLine();
    Players.add(new Player(userName));
    if (userName.equals("S") || userName.equals("s")) {
        System.out.println("Player " + (i + 1) + "'s name is: " + userName + "\n");  //Output user input
    } else {
        System.out.println("Player " + (i + 1) + "'s name is: " + userName + "\n");  //Output user input
    }
}
        }
        Collections.shuffle(Players);
        System.out.println("Order of Players:");
        for (int i = 0; i < NumPLayers; i++) {
            System.out.println(Players.get(i).getName()); //Shows order of Players
        }
    }

    public void CreateTiles() throws IOException {          //Creates Array lists for animal and habitat Tiles needed to play Casscadia
        for (int i = 0; i < 20; i++) { //adding animal tiles to list
            AnimalCards.add(new Animal(Animalselect.Hawk));
            AnimalCards.add(new Animal(Animalselect.Bear));
            AnimalCards.add(new Animal(Animalselect.Elk));
            AnimalCards.add(new Animal(Animalselect.Salmon));
            AnimalCards.add(new Animal(Animalselect.Fox));
        }
        for (int l = 0; l < 5; l++) { //adding keystone tiles to list
            HabitatCards.add(new Habitat(Habitatselect.forest, Habitatselect.none));
            HabitatCards.add(new Habitat(Habitatselect.wetland, Habitatselect.none));
            HabitatCards.add(new Habitat(Habitatselect.river, Habitatselect.none));
            HabitatCards.add(new Habitat(Habitatselect.mountain, Habitatselect.none));
            HabitatCards.add(new Habitat(Habitatselect.prairie, Habitatselect.none));
        }
        int m=0;
        while (m<NumhabitatTiles-25){ //creates random habitat tiles with more than 1 habitat on them
            Random rand = new Random();
            int selectingHabitat1 = rand.nextInt(4);
            int selectingHabitat2 = rand.nextInt(4);
            if (selectingHabitat1!=selectingHabitat2){
            Habitat newpick = new Habitat(Casscadia.Habitatselect.values()[selectingHabitat1],Casscadia.Habitatselect.values()[selectingHabitat2]);
            HabitatCards.add(newpick);
            m++;
            }
        }
    }

    public void Turn() { //code that at the begining of the game which shuffles cards and checks if a reshuffle is available
        Collections.shuffle(HabitatCards);
        int animalReshuffleCheck = 0;
        Collections.shuffle(AnimalCards);
        for (int l = 0; l < 4; l++) {
            animalReshuffleCheck = 0;
            for (int i = 0; i < 2; i++) {
                if (AnimalCards.get(l).getAnimalName().equals(AnimalCards.get(i).AnimalName)) {
                    animalReshuffleCheck++;     //checks how many of each animal appear each turn
                }
            }
            if (animalReshuffleCheck == 4 || animalReshuffleCheck == 3) {
                canReshuffle = true;
            }
        }
    }

    public void ReshuffleCheck() {
        int animalReshuffleCheck = 0;
        for (int l = 0; l < 2; l++) {
            animalReshuffleCheck = 0;
            for (int i = 0; i < 4; i++) {
                if (AnimalCards.get(l).getAnimalName().equals(AnimalCards.get(i).AnimalName)) {
                    animalReshuffleCheck++;     //checks how many of each animal appear each turn
                }
            }
            if (animalReshuffleCheck == 4 || animalReshuffleCheck == 3) {
                canReshuffle = true;
            }
            else {
                canReshuffle=false;
            }
        }

    }

    public void plusPlayerTurn() { //shuffles cards and moves on to the next player after every turn
        if (this.PlayerTurn < NumPLayers - 1) {
            this.PlayerTurn++;
        } else {
            PlayerTurn = 0;
        }

        this.turnInfo = (this.Players.get(this.PlayerTurn).getName() + "'s turn ");

        Collections.shuffle(this.HabitatCards);
        Collections.shuffle(this.AnimalCards);
        this.CurrentText="Select a Habitat and Animal or place a habitat and use a wildlife token to choose an animal";
        turns++;
    }
    public void plusRotation() {//code increments current rotation variable and resets to 0 once the number 4 is reached as the cards can only be rotated 4 times(360 degrees)
        if (this.currentRotation < 3) {
            this.currentRotation++;
        } else {
            currentRotation = 0;
        }
    }
}