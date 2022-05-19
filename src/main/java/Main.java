import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {

    public static final int OPERATORS_NUMBER = 3;

    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> incomingCalls = new ConcurrentLinkedQueue<>();
        Thread phoneStation = new Thread(() -> new TelephoneStation(incomingCalls).newCalls());
        Thread[] operators = new Thread[OPERATORS_NUMBER];
        for (int i = 0; i < OPERATORS_NUMBER; i++) {
            operators[i] = new Thread(() -> new Operator(incomingCalls).connectWithOperator(), "Оператор " + i);
        }
        phoneStation.start();
        for (Thread operator : operators) {
            operator.start();
        }
        try {
            phoneStation.join();
            while (!incomingCalls.isEmpty()) ;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (Thread operator : operators) {
            operator.interrupt();
        }
    }
}