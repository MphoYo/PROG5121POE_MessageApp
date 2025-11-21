package za.ac.iie;

import java.util.List;

public class MessageApp {

    public static void main(String[] args) {
        MessageService service = new MessageService();
        service.populateTestData();

        System.out.println("=== Sent Message Details ===");
        for (String line : service.getSentMessageDetails()) {
            System.out.println(line);
        }

        System.out.println("\nLongest message: ");
        System.out.println(service.getLongestMessage());

        System.out.println("\nSearch by ID (MSG004):");
        System.out.println(service.searchByMessageID("MSG004"));

        System.out.println("\nMessages for recipient +27838884567:");
        List<String> list = service.searchByRecipient("+27838884567");
        for (String msg : list) {
            System.out.println(msg);
        }

        System.out.println("\n=== Report ===");
        for (String line : service.buildReport()) {
            System.out.println(line);
        }
    }
}
