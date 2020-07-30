import java.io.*;
import java.util.*;

public class InputDataValidatorImpl implements InputDataValidator {

    private BufferedReader br;

    public InputDataValidatorImpl() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public Integer[] validateMatricSize() {
        try {
            String firstRow;

            while (null != (firstRow = this.br.readLine())) {
                String[] firstRowAsArr = firstRow.split(", ");

                int x = Integer.parseInt(firstRowAsArr[0]);
                int y = Integer.parseInt(firstRowAsArr[1]);

                if ((x >= 1 && x < 1000) && (y >= 1 && y < 1000)) {
                    Integer[] matricSize = new Integer[2];
                    matricSize[0] = y;
                    matricSize[1] = x;

                    return matricSize;
                } else {
                    System.out.println("Wrong parameters ! Please check and enter valid input data.");
                }
            }

            return null;


        } catch (IOException NumberFormatException) {
            System.out.println("Wrong parameters ! Please check and enter valid input data.");
            return null;
        }
    }

    @Override
    public String[] validateGenerationZero(Integer[] matricSize) {
        StringBuilder sb = new StringBuilder();

        String line;

        int rowCounter = 0;
        try {
            while (null != (line = this.br.readLine())) {

                if (line.length() < matricSize[0]) { //Check if matricSize[0 or 1] should be used !
                    System.out.println(String.format("Line's length is too short. You need %d more symbols", matricSize[0] - line.length()));
                    System.out.println("Enter correct row again.");
                } else if (line.length() > matricSize[0]) {
                    System.out.println(String.format("Line's length is too long. Only first %d symbols will be taken.", matricSize[0]));
                    sb.append(line).append(System.lineSeparator());
                    rowCounter++;
                } else {
                    sb.append(line).append(System.lineSeparator());
                    rowCounter++;
                }

                if (rowCounter == matricSize[1]) { //Check if matricSize[0 or 1] should be used !
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return (sb.toString().trim()).split(System.lineSeparator());
    }

    @Override
    public int[] validateLastInputRow(Integer[] matricSize) {
        try {
            String line;
            while (null != (line = this.br.readLine())) {
                int[] lastInputRow = Arrays.stream(line.split(", ")).mapToInt(Integer::parseInt).toArray();
                int x = lastInputRow[0];
                int y = lastInputRow[1];
                if (x < matricSize[0] && x >= 0 && y < matricSize[1] && y >= 0) {
                    return lastInputRow;
                } else {
                    System.out.println("Cell is outside of border. Enter the last row again.");
                }

            }

        } catch (IOException e) {
            System.out.println("Wrong last input row ! Try another one.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}
