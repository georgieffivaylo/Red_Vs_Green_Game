public class GreenVsRed {

    public static void main(String[] args) {

        //A MatricFactory class is created within the main class so that I can use Dependency Injection.
        MatricFactory mf = new MatricFactoryImpl();

        new Engine(mf).run();
    }
}
