public interface InputDataValidator {

    Integer[] validateMatricSize();

    String[] validateGenerationZero(Integer[] matricSize);

    public int[] validateLastInputRow(Integer[] matricSize);
}
