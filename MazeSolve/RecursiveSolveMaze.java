import java.util.Scanner;

public class RecursiveSolveMaze {
    private static int[][] maze;
    public static int sizeRow;
    public static int sizeCol;

    static final int PATHWAY = 1;
    static final int WALL = 0;
    static final int PATH = 2;
    static final int BLOCKED = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size of maze (n,m): ");
        sizeRow = Integer.parseInt(scanner.next());
        sizeCol = Integer.parseInt(scanner.next());

        maze = new int[sizeRow][sizeCol];

        for (int i = 0; i < sizeRow; i++) {
            for (int j = 0; j < sizeCol; j++) {
                maze[i][j] = (int) (Math.random() * 2);
            }
        }

        printMaze(maze);
        // make maze exit
        maze[sizeRow - 1][sizeCol - 1] = 1;

        findPath(0, 0);
        if (maze[sizeRow - 1][sizeCol - 1] != 2) {
            System.out.println("There is no possible path, Retry!");
        }

        printMaze(maze);
    }

    public static void printMaze(int[][] array) {
        for (int i = 0; i < sizeRow; i++) {
            for (int j = 0; j < sizeCol; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    // reculsive function
    public static boolean findPath(int x, int y) {
        if (x < 0 || y < 0 || x >= sizeRow || y >= sizeCol) { // out of Range maze
            return false;
        }

        if (maze[x][y] != PATHWAY) { // already visit or no way
            return false;

        } else if (x == (sizeRow - 1) && y == (sizeCol - 1)) { // find exit
            System.out.println("Success!");
            maze[x][y] = PATH;
            return true;
        } else {
            maze[x][y] = 2;
            if (findPath(x - 1, y) || findPath(x, y + 1) || findPath(x + 1, y)
                    || findPath(x, y - 1) || findPath(x - 1, y + 1) || findPath(x + 1, y + 1) ||
                    findPath(x + 1, y - 1) || findPath(x - 1, y - 1)) {
                maze[x][y] = PATH;
                return true;
            } else {
                maze[x][y] = BLOCKED;
                return false;
            }
        }
    }
}
