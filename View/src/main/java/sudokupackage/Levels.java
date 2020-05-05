package sudokupackage;

public enum Levels {
    Easy(27),
    Medium(36),
    Hard(75),
    Łatwy(27),
    Normalny(36),
    Trudny(75);

    private final int numberOfcells;

    Levels(int numberOfcells) {
        this.numberOfcells = numberOfcells;
    }

    public int getNumberOfcells() {
        return numberOfcells;
    }
}
