package thread_demo;

public class InterruptThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        thread.start();
        Thread.sleep(2000);
        System.out.println("thread will be interrupted");
        thread.stopImmediate();

        thread.join();
    }

    private static class MyThread extends Thread {
        private boolean alive = true;
        private int counter = 0;

        public void stopImmediate() {
            this.alive = false;
        }

        @Override
        public void run() {
            try {
                while (counter <= 100 && alive) {
                    Thread.sleep(300);
                    ++counter;
                    System.out.println("Counter was increased: " + counter);
                }
            } catch (InterruptedException ex) {
                this.interrupt();
                ex.printStackTrace();
            }
        }
    }

}
