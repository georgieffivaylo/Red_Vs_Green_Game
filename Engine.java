public class Engine implements Runnable {

    private MatricFactory matricFactory;

    public Engine(MatricFactory mf) {
        this.matricFactory = mf;
    }

    @Override
    public void run() {

        System.out.println("Enter input parameters:");

        //Validates all the input parameters.
        InputDataValidator inputDataValidator = new InputDataValidatorImpl();
        Integer[] matricSize = inputDataValidator.validateMatricSize();
        String[] matricAsArr = inputDataValidator.validateGenerationZero(matricSize);
        int[] lastInputRow = inputDataValidator.validateLastInputRow(matricSize);

        //Creates the initial state of the matric - Generation Zero.
        char[][] generationZeroMatric = this.matricFactory.buildInitialMatric(matricSize, matricAsArr);

        //The method does all the spins /generations/ in order to complete the game
        // and increases a static integer variable (greenStatesCounter) within the Cell class.
        this.matricFactory.processGenerations(generationZeroMatric, lastInputRow);

        //Prints the final result from the game.
        System.out.println(String.format("The selected cell was green %d times.", Cell.getGreenStatesValue()));

    }


}
