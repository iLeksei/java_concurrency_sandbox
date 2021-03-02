package consumer_producer_demo;

import java.util.concurrent.ArrayBlockingQueue;

public class Demo {


    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);
        RandomProducerDemo producer = new RandomProducerDemo(queue);
        ConsumerDemo consumer = new ConsumerDemo(queue);
        producer.produce();
        consumer.consume();
    }
}
