package za.ac.iie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class MessageServiceTest {

    private MessageService service;

    @BeforeEach
    public void setUp() {
        service = new MessageService();
        service.populateTestData();
    }

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        // Test Data: Developer entry for Test data for message 1‑4
        List<String> sent = service.getSentMessages();

        assertEquals(2, sent.size());
        assertTrue(sent.contains("Did you get the cake?"));
        assertTrue(sent.contains("It is dinner time !"));
    }

    @Test
    public void testDisplayLongestMessage() {
        // Test Data: message 1‑4
        String longest = service.getLongestMessage();

        // The system returns:
        // "Where are you? You are late! I have asked you to be on time."
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest);
    }

    @Test
    public void testSearchForMessageID() {
        // Test Data: message 4 (ID = MSG004)
        String result = service.searchByMessageID("MSG004");

        assertTrue(result.contains("0838884567"));
        assertTrue(result.contains("It is dinner time !"));
    }

    @Test
    public void testSearchAllMessagesForRecipient() {
        // Test Data: +27838884567
        List<String> results = service.searchByRecipient("+27838884567");

        // The system returns the two stored messages for that recipient.
        assertTrue(results.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(results.contains("Ok, I am leaving without you."));
        assertEquals(2, results.size());
    }

    @Test
    public void testDeleteMessageUsingHash() {
        // We'll delete Test Message 2 ("Where are you? You are late! ...")

        List<String> allMessages = service.getAllMessages();
        List<String> hashes = service.getMessageHash();

        int indexOfMessage2 = allMessages.indexOf("Where are you? You are late! I have asked you to be on time.");
        String hashToDelete = hashes.get(indexOfMessage2);

        String response = service.deleteByHash(hashToDelete);

       assertEquals(
    "Message \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.",
    response
);

        assertFalse(service.getAllMessages().contains("Where are you? You are late! I have asked you to be on time."));
    }

    @Test
    public void testBuildReport() {
        List<String> report = service.buildReport();

        // Report should contain one line per message
        assertEquals(service.getAllMessages().size(), report.size());

        // Check that a line contains all required parts
        String line = report.get(0);
        assertTrue(line.contains("ID: "));
        assertTrue(line.contains("Hash: "));
        assertTrue(line.contains("Recipient: "));
        assertTrue(line.contains("Message: "));
    }
}
