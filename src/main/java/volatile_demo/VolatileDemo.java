package volatile_demo;


public class VolatileDemo {
    private static volatile boolean sleep = false;

    public static void main(String[] args) throws InterruptedException {

        Thread student = new Student();
        student.start();

        for (int i = 0; i < 5; i ++) {
            Thread.sleep(1000);
            System.out.println("Student will sleep in " + (5 - i) + " seconds");
        }
        VolatileDemo.sleep = true;
        student.join();
    }

    private static class Student extends Thread {

        @Override
        public void run() {
            int count = 0;
            while(!VolatileDemo.sleep) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count += 1;
                System.out.println("I am trying fall a sleep and counting sheep: " + count);
            }
            System.out.println("well done i am falling a sleep");
        }
    }

}
