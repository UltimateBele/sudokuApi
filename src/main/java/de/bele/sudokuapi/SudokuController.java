package de.bele.sudokuapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SudokuController {

    private SudokuGenerator sudokuGenerator;

    public SudokuController(SudokuGenerator sudokuGenerator) {
        this.sudokuGenerator = sudokuGenerator;
    }

    @GetMapping("/sudoku/generate")
    public SudokuResponse generateSudoku(@RequestParam(name = "removals", defaultValue = "81") int removals) {
        return sudokuGenerator.generateResponse(removals);
    }
}
