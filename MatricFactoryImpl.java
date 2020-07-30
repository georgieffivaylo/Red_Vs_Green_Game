import java.io.*;
import java.util.*;

public class MatricFactoryImpl implements MatricFactory {

    public char[][] buildInitialMatric(Integer[] matricSize, String[] matricAsString) {

        int x = matricSize[1];
        int y = matricSize[0];

        char[][] generationZero = new char[x][y];

        for (int i = 0; i < generationZero.length; i++) {
            char[] row = matricAsString[i].toCharArray();
            for (int j = 0; j < generationZero[i].length; j++) {
                generationZero[i][j] = row[j];
            }
        }

        return generationZero;


    }

    public void processGenerations(char[][] matricToProcess, int[] lastInputRow) {

        int generations = lastInputRow[2];

        //The purpose of the deque is to load all the cells that should change their state for the next generation.
        Deque<Cell> cellsToToggleTheirState = new ArrayDeque<>();

        //For loop that goes through all generations.
        for (int k = 0; k <= generations; k++) {

            //Increases GreenStateCounter if the cell for the current generation is green.
            tryToIncreaseGreenStateCounter(matricToProcess, lastInputRow);

            //Fills the deque with all the cells that will need to toggle their state for the next generation.
            fillTheDeque(matricToProcess, cellsToToggleTheirState);

            //Updates the matric for the next generation and sets the length of the deque to 0 so that it is ready to use.
            updateMatric(cellsToToggleTheirState, matricToProcess);

        }

    }

    private void tryToIncreaseGreenStateCounter(char[][] matric, int[] lastInputRow) {

        //I think that there is an error with the document, because the X and Y parameters had to be swapped.
        //Otherwise the result is not correct. X is set as the second argument and Y as the first one.
        int x = lastInputRow[1];
        int y = lastInputRow[0];

        if (matric[x][y] == '1') {
            Cell.increaseGreenStateCounter();
        }
    }

    private void fillTheDeque(char[][] initialMatric, Deque<Cell> cellsToToggle) {
        for (int i = 0; i < initialMatric.length; i++) {
            for (int j = 0; j < initialMatric[i].length; j++) {

                Character[] greenNeighboursOfCurrentCell = getAllNeighbours(initialMatric, i, j);

                char currentChar = initialMatric[i][j];

                Cell cell;
                switch (currentChar) {

                    case '1': //green

                        if (greenNeighboursOfCurrentCell.length != 2
                                && greenNeighboursOfCurrentCell.length != 3
                                && greenNeighboursOfCurrentCell.length != 6) {
                            cell = new Cell(i, j);
                            cell.setState(currentChar);
                            cellsToToggle.addFirst(cell);
                        }
                        break;
                    case '0': // red

                        if (greenNeighboursOfCurrentCell.length == 3
                                || greenNeighboursOfCurrentCell.length == 6) {
                            cell = new Cell(i, j);
                            cell.setState(currentChar);
                            cellsToToggle.addFirst(cell);
                        }
                        break;
                }
            }

        }
    }

    private void updateMatric(Deque<Cell> cellsToToggle, char[][] initialMatric) {
        while (cellsToToggle.size() > 0) {
            Cell c = cellsToToggle.pollFirst();
            c.toggleState();

            initialMatric[c.getX()][c.getY()] = c.getState();
        }
    }

    //Returns an array of Characters with all neighbours of the current cell that is currently checked and filters all unnecessary fields.
    private Character[] getAllNeighbours(char[][] initialMatric, int i, int j) {
        Character[] surroundingCells = new Character[8];

        //I decided to list all the positions within the Character[] surroundingCells, because they are only 8 in total.
        //By doing so, it is easier to catch the IndexOutOfBoundsExceptions for every cell whenever the checked cell is outside the border.
        surroundingCells[0] = returnValueOfSurroundingCell(initialMatric, i - 1, j - 1);
        surroundingCells[1] = returnValueOfSurroundingCell(initialMatric, i - 1, j);
        surroundingCells[2] = returnValueOfSurroundingCell(initialMatric, i - 1, j + 1);
        surroundingCells[3] = returnValueOfSurroundingCell(initialMatric, i, j - 1);
        surroundingCells[4] = returnValueOfSurroundingCell(initialMatric, i, j + 1);
        surroundingCells[5] = returnValueOfSurroundingCell(initialMatric, i + 1, j - 1);
        surroundingCells[6] = returnValueOfSurroundingCell(initialMatric, i + 1, j);
        surroundingCells[7] = returnValueOfSurroundingCell(initialMatric, i + 1, j + 1);

        //Returns and array of characters so that all positions are represented by char '1' /green/ only.
        return Arrays.stream(surroundingCells).filter(c -> c != null && c == '1').toArray(Character[]::new);
    }

    //If the checked cell is within the border, the method returns a result, otherwise returns null value.
    private Character returnValueOfSurroundingCell(char[][] matric, int i, int j) {
        try {
            return matric[i][j];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }


}
