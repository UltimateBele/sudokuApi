# Sudoku API

Kleine Spring Boot REST API, die vollständige Sudoku-Boards erzeugt und daraus eindeutige Rätsel (Puzzle) erzeugt.

## Features
- Erzeugt ein vollständiges Sudoku (9×9).
- Entfernt bis zu `removals` Felder, prüft dabei, dass das Puzzle **genau eine Lösung** hat.
- Endpoint: `GET /sudoku/generate?removals={n}` — gibt `fullBoard`, `puzzleBoard` und `deleted` als JSON zurück.

---

## API
**GET /sudoku/generate**

Parameter
- removals (optional, default 81) - Wie viele Zellen er maximal beim Puzzle entfernen soll. Kann auch weniger sein, da auf "Einzigartigkeit" priorität gelegt wird.

Beispiel:
```bash
curl "http://localhost:8080/sudoku/generate?removals=40"
```

Beispiel Antwort (JSON):
```json
{
  "fullBoard": [[3,2,6,7,5,1,4,8,9], [...], ...],
  "puzzleBoard": [[0,0,6,0,0,0,0,8,0], [...], ...],
  "deleted": 55
}
```

---

# Projekt Struktur:
```bash
src/main/java/de/bele/sudokuapi/
├─ SudokuApiApplication.java      # Spring Boot main
├─ SudokuController.java          # REST Controller (GET /sudoku/generate)
├─ SudokuGenerator.java           # Service mit Generator-Logik
└─ SudokuResponse.java            # DTO für JSON-Response
```

---

# Lizenz
MIT

