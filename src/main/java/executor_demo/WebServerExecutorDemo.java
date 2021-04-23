package executor_demo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServerExecutorDemo {
    private static int nThreads = 100;
    private static ExecutorService service = Executors.newFixedThreadPool(nThreads);

    public static void main(String[] args) throws IOException {
        System.out.println("ServerSocket is available on port 9001");
        ServerSocket socket = new ServerSocket(9001);
        while (true) {
            final Socket connection = socket.accept();
            service.submit(() -> {
               System.out.println("connection: " + connection.getInetAddress() + " : " + connection.getPort());
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                    String word = reader.readLine();
                    System.out.println(word);
                    writer.write("Hello from server : " + word + "\n");
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
