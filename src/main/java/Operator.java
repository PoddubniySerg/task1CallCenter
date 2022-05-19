import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Operator {
    private static final int MAX_TIME_CONNECTING_ABONENT = 5000;
    private static final int MIN_TIME_CONNECTING_ABONENT = 0;
    private final ConcurrentLinkedQueue<String> incomingCalls;
    private final Random random;

    public Operator(ConcurrentLinkedQueue<String> incomingCalls) {
        this.incomingCalls = incomingCalls;
        this.random = new Random();
    }

    public void connectWithOperator() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(random.nextInt(MAX_TIME_CONNECTING_ABONENT + MIN_TIME_CONNECTING_ABONENT));
                if (!this.incomingCalls.isEmpty()) {
                    System.out.println(Thread.currentThread().getName() + " принял звонок от " + this.incomingCalls.poll());
                }
            }
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread().getName() + " закончил работу");
    }
}
