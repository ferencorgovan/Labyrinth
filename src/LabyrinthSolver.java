import java.util.*;

public class LabyrinthSolver {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static class State {
        private final int x;
        private final int y;
        private final String path;
        private final int cost;

        public State(int x, int y, String path, int cost) {
            this.x = x;
            this.y = y;
            this.path = path;
            this.cost = cost;
        }
    }

    public void solve(char[][] board) {
        Position startingPosition = findPlayerPosition(board);

        if (startingPosition == null) {
            System.out.println("Nem található játékos a táblán.");
            return;
        }

        boolean[][] visited = new boolean[board.length][board[0].length];
        PriorityQueue<State> queue = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost + heuristic(s.x, s.y)));
        queue.add(new State(startingPosition.getX(), startingPosition.getY(), "", 0));

        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            int x = currentState.x;
            int y = currentState.y;

            if (board[x][y] == BoardGenerator.GOAL) {
                System.out.println("Megoldás: " + currentState.path);
                System.out.println("Költség: " + currentState.cost);
                return;
            }

            if (visited[x][y]) {
                continue;
            }

            visited[x][y] = true;

            for (int[] direction : DIRECTIONS) {
                int newX = x + direction[0];
                int newY = y + direction[1];

                if (isValidMove(board, newX, newY)) {
                    int newCost = currentState.cost + 1;
                    queue.add(new State(newX, newY, currentState.path + directionToString(direction), newCost));
                }
            }
        }

        System.out.println("Nincs megoldás a játékhoz.");
    }

    private static boolean isValidMove(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return false;
        }

        char cell = board[x][y];
        return cell == BoardGenerator.EMPTY || cell == BoardGenerator.GOAL;
    }

    private static int heuristic(int x, int y) {
        return Math.abs(x - 2) + Math.abs(y - 7);
    }

    private static String directionToString(int[] direction) {
        if (direction[0] == -1 && direction[1] == 0) {
            return "UP ";
        } else if (direction[0] == 1 && direction[1] == 0) {
            return "DOWN ";
        } else if (direction[0] == 0 && direction[1] == -1) {
            return "LEFT ";
        } else if (direction[0] == 0 && direction[1] == 1) {
            return "RIGHT ";
        } else {
            return "";
        }
    }

    private static Position findPlayerPosition(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == BoardGenerator.PLAYER) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }
}
