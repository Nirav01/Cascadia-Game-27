package cascadia;

//Class describes the 'Wildlife Tokens' used in the game. 100 altogether, 20 tokens of each type: Bear, Elk, Salmon, Hawk, Fox

//At game setup, all tokens shuffled in 'bag' 
public enum Wildlife {
    HAWK("H "),
    BEAR("B "),
    ELK("E "),
    SALMON("S "),
    FOX("F ");

    private String symbol;

    Wildlife(String symbol){
        this.symbol = symbol;
    }

    public String toString(){
        return symbol;
    }

}
