package sudokupackage;

public enum Levels {
    Easy(27),
    Medium(36),
    Hard(45);

    final private int numberOfcells;

    Levels(int numberOfcells) {
        this.numberOfcells = numberOfcells;
    }

    public int getNumberOfcells() {
        return numberOfcells;
    }
}
