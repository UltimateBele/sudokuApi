package de.bele.sudokuapi;

public class SudokuResponse {

    private final int[][] fullBoard;
    private final int[][] puzzleBoard;
    private final int deleted;

    public SudokuResponse(int[][] fullBoard, int[][] puzzleBoard, int deleted) {
        this.fullBoard = fullBoard;
        this.puzzleBoard = puzzleBoard;
        this.deleted = deleted;
    }

    public int[][] getFullBoard() {
        return fullBoard;
    }

    public int[][] getPuzzleBoard() {
        return puzzleBoard;
    }

    public int getDeleted() {
        return deleted;
    }
}
