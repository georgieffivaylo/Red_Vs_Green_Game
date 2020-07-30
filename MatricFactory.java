public interface MatricFactory {

    char[][] buildInitialMatric(Integer[] matricSize, String[] matricAsString);

    void processGenerations(char[][] matricToProcess, int[] lastInputRow);
}
