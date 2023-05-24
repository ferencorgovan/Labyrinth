public class Main {

    public static void main(String[] args) {

        BoardGenerator boardGenerator = new BoardGenerator();
        BoardPrinter boardPrinter = new BoardPrinter();
        LabyrinthSolver labyrinthSolver = new LabyrinthSolver();

        char[][] board = boardGenerator.generateRandomBoard();
        boardPrinter.printBoard(board);
        labyrinthSolver.solve(board);
    }
}
