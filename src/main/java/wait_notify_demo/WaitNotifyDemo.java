package wait_notify_demo;


public class WaitNotifyDemo {

    private static Integer counter = 0;
    Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyDemo wnd = new WaitNotifyDemo();
        wnd.demo();
    }

    private void demo() throws InterruptedException {
        Thread counterThread = new Thread(new CounterThread());
        Thread logThread = new Thread(new LogThread());
        counterThread.start();
        logThread.start();
        counterThread.join();
        logThread.join();
    }

    private class CounterThread implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                    try {
                        while (counter < 10) {
                            Thread.sleep(300);
                            counter += 1;
                            System.out.println(counter);
                            lock.notifyAll();
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                    e.printStackTrace();
                    }
            }
        }

    }

    private class LogThread implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                while (counter <= 10) {
                    try {
//                        Thread.sleep(300);
                        System.out.println("Counter was increased: " + counter);
                        lock.notifyAll();
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }
        }

    }

}
