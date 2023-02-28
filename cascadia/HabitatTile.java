package cascadia;

import java.util.ArrayList;

//Class describes the 'Habitat Tiles' used in the the game. 85 tiles, including 25 Keystone Tiles

//At game setup, (1) Determine number of Habitat Tiles needed. (2) Randomly select desired amount. (3) Excluded tiles will not be used and can be discarded.
//Players              Tiles needed (20*Players)+3
//  2                       43
//  3                       63
//  4                       83

//Each environment present as Keystone 5 times
//10 combinations for remaining 60 tiles
//Therefore each dual-combination present 6 times. e.g.River-Wetland, River-Forest etc.

public class HabitatTile{
    //Each tile has 2 environments, keystones have the same on both sides and have different scoring rules
    private Habitat primary;
    private Habitat secondary;
    private boolean isKeystone=false;
    //Each tile can be populated by a Wildlife Token if it matches the wildlife options (ranging from 1-3) present on the HabitatTile
    private boolean hasWildlife=false;
    private int optionWildlife;
    private ArrayList<Wildlife>slot;

    //Constructor
    HabitatTile(Habitat pri, Habitat sec){
        this.primary=pri;
        this.secondary=sec;
        if(pri==sec){
            isKeystone=true;
            //Keystone tiles only have 1 Wildlife option
            optionWildlife=1;
        }else{
            //Generate random amount of options between 1-3
            this.optionWildlife=(int)Math.floor(Math.random() * (3) + 1);
        }        
        //slot array holds name of Wildlife that can inhabit tile      
        slot = new ArrayList<Wildlife>(optionWildlife);
        //TODO
        //check for duplicates
        for(int i=0;i<optionWildlife;i++){
            slot.add(Wildlife.randomWildlife());
        }
    }

    //Construtcor for keystones
    HabitatTile(Habitat keystone, Wildlife animal){
        this.primary=keystone;
        this.secondary=keystone;
        this.isKeystone=true;
        optionWildlife=1;
        slot=new ArrayList<Wildlife>(1);
        slot.add(animal);
    }
    

    //Accessor methods
    public Habitat getPri(){
        return primary;
    }
    public Habitat getSec(){
        return secondary;
    }
    public boolean isKeystone(){
        return isKeystone;
    }

    //Compare HabitatTile to 'this' tile's primary environment
    public boolean sameAsPri(HabitatTile tile){
        if(this.getPri()==tile.getPri()){
            return true;
        }else{
            return this.getPri()==tile.getSec();
        }
    }

    //Compare HabitatTile to 'this' tile's secondary environment
    public boolean sameAsSec(HabitatTile tile){
        if(this.getSec()==tile.getPri()){
            return true;
        }else{
            return this.getSec()==tile.getSec();
        }
    }


    //Does tile have a Wildlife Token on it
    public boolean hasWildlife(){
        return hasWildlife;
    }

    //How many Wildlife options are on Habitat Tile
    public int optionWildlife(){
        return optionWildlife;
    }

    //Returns tile's wildlife options
    public ArrayList<Wildlife> getSlots(){
        return slot;
    }
}