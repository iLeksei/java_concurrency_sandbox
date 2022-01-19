package other;

import java.util.Timer;
import java.util.TimerTask;

import static java.util.concurrent.TimeUnit.SECONDS;

public class OutOfTimeDemo {

    public static void main(String[] args) throws Exception {
        Timer timer = new Timer();
        timer.schedule(new MyThread(), 1);
        SECONDS.sleep(1);
        timer.schedule(new MyThread(), 1); //  will not be executed, cause: "Timer already canceled"
        SECONDS.sleep(5);
    }

    public static class MyThread extends TimerTask {
        @Override
        public void run() {
            throw new RuntimeException("On purpose error!");
        }
    }

}
