import java.util.Random;

public class BoardGenerator {
    private static final char WALL = '#';
    static final char PLAYER = 'P';
    static final char GOAL = '*';
    static final char EMPTY = '_';

    private static final int INNER_MAP_HEIGHT = 6;
    private static final int INNER_MAP_WIDTH = 6;
    private static final int MIN_WALL = 0;

    public char[][] generateRandomBoard() {
        int MAP_HEIGHT = INNER_MAP_HEIGHT + 2;
        int MAP_WIDTH = INNER_MAP_WIDTH + 2;
        int spaceCount = INNER_MAP_HEIGHT * INNER_MAP_WIDTH;

        char[][] board = new char[MAP_HEIGHT][MAP_WIDTH];
        Random random = new Random();

        for (int i = 0; i < MAP_HEIGHT; i++) {
            for (int j = 0; j < MAP_WIDTH; j++) {
                board[i][j] = (i == 0 || i == MAP_HEIGHT - 1 || j == 0 || j == MAP_WIDTH - 1) ? WALL : EMPTY;
            }
        }

        Position playerPosition = generateRandomPosition(random);
        Position goalPosition;

        do {
            goalPosition = generateRandomPosition(random);
        } while (playerPosition.equals(goalPosition));

        board[playerPosition.getX()][playerPosition.getY()] = PLAYER;
        board[goalPosition.getX()][goalPosition.getY()] = GOAL;

        // int numWalls = 10;
        int numWalls = random.nextInt(MIN_WALL, spaceCount / 2);
        System.out.println("Pálya mérete: " + INNER_MAP_WIDTH + " X " + INNER_MAP_HEIGHT + " (" + spaceCount + " mező)");
        System.out.println("Akadályok száma: " + numWalls + "\n");

        int wallsPlaced = 0;
        while (wallsPlaced < numWalls) {
            Position wallPosition = generateRandomPosition(random);

            if (board[wallPosition.getX()][wallPosition.getY()] != EMPTY) {
                continue;
            }

            board[wallPosition.getX()][wallPosition.getY()] = WALL;
            wallsPlaced++;
        }
        return board;
    }

    private Position generateRandomPosition(Random random) {
        int x = random.nextInt(INNER_MAP_HEIGHT) + 1;
        int y = random.nextInt(INNER_MAP_WIDTH) + 1;
        return new Position(x, y);
    }
}
