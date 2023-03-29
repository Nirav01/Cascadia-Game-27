import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Habitat {
    public Habitat(Casscadia.Habitatselect ex1, Casscadia.Habitatselect ex2) throws IOException {
    this.habitat1=ex1;
    this.habitat2=ex2;
        switch (habitat1){//sets name and picture for habitat based on constructor variables
            case forest -> {
                switch (habitat2){
                    case mountain -> {
                        HabitatName="forest/mountain";
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/MountainForest0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case river -> {
                        HabitatName="forest/river";
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/ForestRiver0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case wetland -> {
                        HabitatName="forest/wetland";
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandRiver0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case prairie -> {
                        HabitatName="forest/prairie";
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/SandForest0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case none -> {
                        HabitatName="forest";
                        habitatImage = ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/Forest.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }

                }
            }
            case mountain -> {
                switch (habitat2) {
                        case river -> {
                            HabitatName="mountain/river";
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/MountainRiver0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case wetland -> {
                            HabitatName="mountain/wetland";
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandMountain0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case prairie -> {
                            HabitatName="mountain/prairie";
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/SandMountain0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                        case forest -> {
                            HabitatName="forest/mountain";
                            habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/MountainForest0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                        }
                       case none -> {
                        HabitatName="mountain";
                        habitatImage = ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/Mountain.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }

                    }
                }

            case river -> {
                switch (habitat2) {
                    case wetland -> {
                        HabitatName="river/wetland";
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandRiver0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case prairie -> {
                        HabitatName="river/prairie";
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/SandRiver0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case forest -> {
                        HabitatName="forest/river";
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/ForestRiver0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case mountain -> {
                        HabitatName="mountain/river";
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/MountainRiver0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case none -> {
                        HabitatName="river";
                        habitatImage = ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/River.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }



                }
            }
            case wetland -> {
            switch (habitat2) {
                case river -> {
                    HabitatName="river/wetland";
                    habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandRiver0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                }
                case prairie -> {
                    HabitatName="river/prairie";
                    habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandSand0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                }
                case mountain -> {
                    HabitatName="mountain/wetland";
                    habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandMountain0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                }
                case forest -> {
                    HabitatName="forest/wetland";
                    habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandRiver0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                }
                case none -> {
                    HabitatName="wetland";
                    habitatImage = ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/Wetland.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                }

        }}
            case prairie -> {
                switch (habitat2) {
                    case wetland -> {
                        HabitatName="wetland/prairie";
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/WetlandSand0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case river -> {
                        HabitatName="river/prairie";
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/SandRiver0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case mountain -> {
                        HabitatName="mountain/prairie";
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/SandMountain0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case forest -> {
                        HabitatName="forest/prairie";
                        habitatImage=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/SandForest0.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }
                    case none -> {
                        HabitatName="prairie";
                        habitatImage = ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/Sand.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                    }

                }
            }
        }




            //chooses animals which can be placed on the habitat
            Random rand = new Random();
            numPossibleAnimals = rand.nextInt(3-1+1)+1;
            while (possibleAnimals.size()<numPossibleAnimals) {
                int pick = new Random().nextInt(5);
                Animal newpick = new Animal(Casscadia.Animalselect.values()[pick]);
                if (isPresent(newpick)==false) {
                    possibleAnimals.add(newpick);
                }
            }
        }