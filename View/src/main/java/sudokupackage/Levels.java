package sudokupackage;

public enum Levels {
    Easy(27),
    Medium(45),
    Hard(63);

    private int liczba;

    Levels(int liczba) {
        this.liczba = liczba;
    }
}
