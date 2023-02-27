package cascadia;

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
    private Habitat primary;
    private Habitat secondary;
    private boolean isKeystone=false;
    private boolean hasWildlife=false;
    private int optionWildlife;


    HabitatTile(Habitat pri, Habitat sec){
        this.primary=pri;
        this.secondary=sec;
        if(pri==sec){
            isKeystone=true;
        }
        this.optionWildlife=(int)Math.floor(Math.random() * (3) + 1);

        
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

    public boolean isSamePri(Habitat tile){
        return this.getPri()==tile;
    }

    public boolean hasWildlife(){
        return hasWildlife;
    }

    public int optionWildlife(){
        return optionWildlife;
    }
}