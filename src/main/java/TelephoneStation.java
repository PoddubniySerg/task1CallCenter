import java.util.concurrent.ConcurrentLinkedQueue;

public class TelephoneStation {
    private static final int MAX_CALLS = 60;
    private static final long WAITING_TIME = 1000;

    private final ConcurrentLinkedQueue<String> incomingCalls1;
    private int countAbonents;


    public TelephoneStation(ConcurrentLinkedQueue<String> incomingCalls1) {
        this.incomingCalls1 = incomingCalls1;
        this.countAbonents = 1;
    }

    public void newCalls() {
        String abonentName;
        try {
            for (int i = 0; i < MAX_CALLS; i++) {
                abonentName = "'Абонент " + this.countAbonents + "'";
                this.incomingCalls1.add(abonentName);
                System.out.println("Входящий звонок от " + abonentName);
                this.countAbonents++;
                Thread.sleep(WAITING_TIME);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
