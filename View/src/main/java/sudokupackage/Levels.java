package sudokupackage;

public enum Levels {
    Easy(18),
    Medium(36),
    Hard(45),
    Łatwy(18),
    Normalny(36),
    Trudny(45);

    private final int numberOfcells;

    Levels(int numberOfcells) {
        this.numberOfcells = numberOfcells;
    }

    public int getNumberOfcells() {
        return numberOfcells;
    }
}
