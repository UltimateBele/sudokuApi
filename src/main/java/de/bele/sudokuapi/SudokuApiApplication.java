package de.bele.sudokuapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class SudokuApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SudokuApiApplication.class, args);
    }

}
