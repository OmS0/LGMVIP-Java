import java.util.Scanner;

public class TicTacToe {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        boolean playAgain = true;
        Scanner scanner = new Scanner(System.in);

        while (playAgain) {
            initializeBoard();
            boolean gameWon = false;

            while (!gameWon) {
                displayBoard();
                int[] move = getPlayerMove();
                int row = move[0];
                int col = move[1];

                if (isValidMove(row, col)) {
                    makeMove(row, col);
                    gameWon = checkForWin(row, col);
                    if (!gameWon) {
                        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    }
                } else {
                    System.out.println("Invalid move. Please try again.");
                }
            }

            displayBoard();
            System.out.println("Player " + currentPlayer + " wins!");

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainResponse = scanner.next().toLowerCase();
            playAgain = playAgainResponse.equals("yes");
        }

        System.out.println("Thanks for playing!");
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    private static int[] getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        int[] move = new int[2];

        System.out.print("Player " + currentPlayer + ", enter your move (row and column): ");
        move[0] = scanner.nextInt();
        move[1] = scanner.nextInt();

        return move;
    }

    private static boolean isValidMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != ' ') {
            return false;
        }
        return true;
    }

    private static void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
    }

    private static boolean checkForWin(int row, int col) {
        // Check rows, columns, and diagonals for a win
        return (checkRow(row) || checkColumn(col) || checkDiagonal() || checkAntiDiagonal());
    }

    private static boolean checkRow(int row) {
        return (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer);
    }

    private static boolean checkColumn(int col) {
        return (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer);
    }

    private static boolean checkDiagonal() {
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer);
    }

    private static boolean checkAntiDiagonal() {
        return (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }
}

