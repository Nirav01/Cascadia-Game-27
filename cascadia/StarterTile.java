package cascadia;

//5 Starter Habitat Tiles in the game
//Each player receive's a random one at the start of game
//Each Starter Tile:
//  -Is made up of 3 Habitat Tiles, connected together into a triangular shape
//  -Has a keystone tile at the top
//  -Non keystone tiles at the left and right corner
//  -Each Starter Tile keystone has a unique Habitat and Wildlife attribute

//Wetland - Hawk
//Mountain - Bear
//Forest - Elk
//River - Salmon
//Prairie - Fox

public class StarterTile {
    HabitatTile keystone;
    HabitatTile left;
    HabitatTile right;    

    //Constructor checks for wildlife in Keystone tile 
    StarterTile(Wildlife w){
        switch(w){
            case HAWK:
                this.keystone = new HabitatTile(Habitat.WETLAND, w);
                this.left = new HabitatTile(Habitat.RIVER,Habitat.FOREST,Wildlife.SALMON,Wildlife.ELK,Wildlife.HAWK);
                this.right = new HabitatTile(Habitat.PRAIRIE, Habitat.MOUNTAIN, Wildlife.BEAR, Wildlife.FOX);
                break;
            case BEAR:
                this.keystone = new HabitatTile(Habitat.MOUNTAIN, w);
                this.left = new HabitatTile(Habitat.FOREST,Habitat.WETLAND,Wildlife.FOX,Wildlife.ELK,Wildlife.HAWK);
                this.right = new HabitatTile(Habitat.PRAIRIE, Habitat.MOUNTAIN, Wildlife.BEAR, Wildlife.FOX);
                break;
            case FOX:
                this.keystone = new HabitatTile(Habitat.PRAIRIE, w);
                this.left = new HabitatTile(Habitat.WETLAND,Habitat.FOREST,Wildlife.SALMON,Wildlife.FOX,Wildlife.HAWK);
                this.right = new HabitatTile(Habitat.PRAIRIE, Habitat.MOUNTAIN, Wildlife.BEAR, Wildlife.FOX);
                break;
            case SALMON:
                this.keystone = new HabitatTile(Habitat.RIVER, w);
                this.left = new HabitatTile(Habitat.PRAIRIE,Habitat.FOREST,Wildlife.SALMON,Wildlife.ELK,Wildlife.BEAR);
                this.right = new HabitatTile(Habitat.PRAIRIE, Habitat.MOUNTAIN, Wildlife.BEAR, Wildlife.FOX);
                break;
            case ELK:
                this.keystone = new HabitatTile(Habitat.FOREST, w);
                this.left = new HabitatTile(Habitat.MOUNTAIN,Habitat.RIVER,Wildlife.HAWK,Wildlife.ELK,Wildlife.BEAR);
                this.right = new HabitatTile(Habitat.PRAIRIE, Habitat.MOUNTAIN, Wildlife.BEAR, Wildlife.FOX);
                break;
        }
    }
    public HabitatTile getTop(){return keystone;}
    public HabitatTile getLeft(){return left;}
    public HabitatTile getRight(){return right;}
}