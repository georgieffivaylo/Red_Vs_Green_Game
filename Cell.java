class Cell {

    private int x;
    private int y;

    private char state;

    private static int greenStatesCounter = 0;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    char getState() {
        return state;
    }

    void setState(char state) {
        this.state = state;
    }

    void toggleState() {
        if (this.state == '1') {
            this.setState('0');
        } else if (this.state == '0') {
            this.setState('1');
        }
    }

    static void increaseGreenStateCounter() {
        greenStatesCounter++;
    }

    static int getGreenStatesValue() {
        return greenStatesCounter;
    }

}
