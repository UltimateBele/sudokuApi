package de.bele.sudokuapi;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class SudokuGenerator {

    public SudokuResponse generateResponse(int removals) {
        int[][] fullBoard = createFullBoard();
        int[][] puzzle = copyBoard(fullBoard);
        int deleted = createPuzzle(puzzle, removals);
        return new SudokuResponse(fullBoard, puzzle, deleted);
    }

    private int[][] createFullBoard() {
        int[][] board = new int[9][9];
        initBoard(board);
        createBoard(board);
        return board;
    }

    private boolean createBoard(int[][] board) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        shuffleArray(numbers);
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 0) {
                    for (int i = 0; i < 9; i++) {
                        int randomNumber = numbers[i];
                        if (isLegal(board, row, col, randomNumber)) {
                            board[row][col] = randomNumber;
                            if (createBoard(board)) {
                                return true;
                            }
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }

        return true;
    }

    private int createPuzzle(int[][] board, int removals) {
        int maxCells = board.length * board[0].length;
        removals = Math.min(removals, maxCells);

        int[] cells = new int[81];
        for (int i = 0; i < maxCells; i++) {
            cells[i] = i;
        }
        shuffleArray(cells);

        int deleted = 0;
        for (int idx = 0; idx < maxCells && deleted < removals; idx++) {
            int cell = cells[idx];
            int row = cell / board.length;
            int column = cell % board[0].length;
            if (board[row][column] != 0) {
                int saveNumber = board[row][column];
                board[row][column] = 0;

                int[][] copy = copyBoard(board);
                int solutions = countSolutions(copy, 2);
                if (solutions == 1) {
                    deleted++;
                } else {
                    board[row][column] = saveNumber;
                }
            }
        }
        return deleted;
    }

    private int countSolutions(int[][] board, int limit) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 0) {
                    int count = 0;
                    for (int i = 1; i <= 9; i++) {
                        if (isLegal(board, row, col, i)) {
                            board[row][col] = i;
                            count += countSolutions(board, limit);
                            board[row][col] = 0;
                            if (count >= limit) {
                                return count;
                            }
                        }
                    }
                    return count;
                }
            }
        }

        return 1;
    }

    // Fisher-Yates shuffle
    private void shuffleArray(int[] arr)
    {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = arr.length - 1; i > 0; i--)
        {
            int index = random.nextInt(i + 1);
            int a = arr[index];
            arr[index] = arr[i];
            arr[i] = a;
        }
    }

    private boolean isLegal(int[][] board, int row, int col, int value) {
        // Check if the same value is in the same row
        for (int c = 0; c < board[row].length; c++) {
            if (c != col && board[row][c] == value) {
                return false;
            }
        }

        // Check if the same value is in the same column
        for (int r = 0; r < board.length; r++) {
            if (r != row && board[r][col] == value) {
                return false;
            }
        }

        int fieldRow = (row / 3) * 3;
        int fieldCol = (col / 3) * 3;

        // Check if the same value is in the same 3x3 square
        for (int r = fieldRow; r < fieldRow + 3; r++) {
            for (int c = fieldCol; c < fieldCol + 3; c++) {
                if (!(r == row && c == col) && board[r][c] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    private int[][] copyBoard(int[][] board) {
        int[][] copy = new int[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                copy[row][col] = board[row][col];
            }
        }
        return copy;
    }

    private void initBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            }
        }
    }
}
