import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner sc = new Scanner(System.in);
            String line = null;

            Interface ui = new Interface(out, in);
            ui.createInterface();

            //new UI(out, in);

            while (!"exit".equalsIgnoreCase(line)) {
                line = sc.nextLine();
                out.println(line);
                out.flush();
                System.out.println("Server replied " + in.readLine());
            }

            socket.close();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

