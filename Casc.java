public class Board {
    private int size;
    private Cell[][] cells;

    public Board(int size) {
        this.size = size;
        cells = new Cell[size][size];
    }

    // Getters and setters for size and cells
}

public class Cell {
    private boolean occupied;

    public Cell() {
        occupied = false;
    }

    // Getters and setters for occupied
}

public class Player {
    private String name;
    private int x, y;

    public Player(String name) {
        this.name = name;
    }

    // Getters and setters for name, x, and y
}

public class Game {
    private Board board;
    private Player[] players;
    private int currentPlayer;

    public Game(int boardSize, int numPlayers) {
        board = new Board(boardSize);
        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player("Player " + (i + 1));
        }
        currentPlayer = 0;
    }

    // Methods for moving players and checking for game over
}

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Game game = new Game(10, 2);

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 500, 500);

        // Set up the game board display
        GridPane boardDisplay = new GridPane();
        for (int i = 0; i < game.getBoard().getSize(); i++) {
            for (int j = 0; j < game.getBoard().getSize(); j++) {
                Rectangle cellDisplay = new Rectangle(50, 50);
                cellDisplay.setFill(Color.WHITE);
                boardDisplay.add(cellDisplay, i, j);
            }
        }
        root.setCenter(boardDisplay);

        // Set up the player display
        VBox playerDisplay = new VBox();
        for (Player player : game.getPlayers()) {
            Label label = new Label(player.getName());
            playerDisplay.getChildren().add(label);
        }
        root.setRight(playerDisplay);

        primaryStage.setTitle("Cascadia");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
