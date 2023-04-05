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

        