package cascadia;

public class Cascadia {
    public static void main(String[] args) {
        View view = new cascadia.View();
        view.GameStartMessage();                               //Game starts
        Player[] playerList = new Player[view.getPlayers()];
        view.namePlayers(playerList);

    }
}
