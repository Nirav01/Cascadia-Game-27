package cascadia;

public class Cascadia {
    public static void main(String[] args) {
        View view = new cascadia.View();
        view.GameStartMessage();                               //Game starts
        int size = view.getPlayers();
        String[] playerList = new String[size];
        playerList = view.getPlayerList(size);

        playerList = view.playOrder(playerList);
        view.printPlayerList(playerList);
    }
}
