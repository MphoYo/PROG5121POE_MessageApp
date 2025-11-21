package za.ac.iie;

import java.util.ArrayList;
import java.util.List;


public class MessageService {

    // Arrays required by the POE
    // Sent, disregarded and stored messages are separated into their own logical arrays
    private List<String> sentMessages = new ArrayList<>();
    private List<String> sentRecipients = new ArrayList<>();

    private List<String> disregardedMessages = new ArrayList<>();
    private List<String> disregardedRecipients = new ArrayList<>();

    private List<String> storedMessages = new ArrayList<>();
    private List<String> storedRecipients = new ArrayList<>();

    // These arrays contain information for ALL messages (1 - 5)
    private List<String> allMessages = new ArrayList<>();
    private List<String> allRecipients = new ArrayList<>();
    private List<String> messageHash = new ArrayList<>();
    private List<String> messageID = new ArrayList<>();

    /**
     * Populates the arrays with the test data given in the POE document.
     * Test Data Messages 1 - 5.
     */
    public void populateTestData() {
        // Clear everything first in case method is called more than once
        sentMessages.clear();
        sentRecipients.clear();
        disregardedMessages.clear();
        disregardedRecipients.clear();
        storedMessages.clear();
        storedRecipients.clear();
        allMessages.clear();
        allRecipients.clear();
        messageHash.clear();
        messageID.clear();

        // Test Data Message 1
        addMessage("+27834557896", "Did you get the cake?", "Sent", "MSG001");

        // Test Data Message 2
        addMessage("+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored", "MSG002");

        // Test Data Message 3
        addMessage("+27834484567", "Yohoooo, I am at your gate.", "Disregard", "MSG003");

        // Test Data Message 4
        addMessage("0838884567", "It is dinner time !", "Sent", "MSG004");

        // Test Data Message 5
        addMessage("+27838884567", "Ok, I am leaving without you.", "Stored", "MSG005");
    }

    /**
     * Helper method that adds a single message into the correct arrays.
     */
    private void addMessage(String recipient, String message, String flag, String id) {
        // Store in master arrays
        allRecipients.add(recipient);
        allMessages.add(message);
        messageID.add(id);
        messageHash.add(String.valueOf(message.hashCode()));

        // Store in category‑specific arrays
        switch (flag) {
            case "Sent":
                sentRecipients.add(recipient);
                sentMessages.add(message);
                break;
            case "Stored":
                storedRecipients.add(recipient);
                storedMessages.add(message);
                break;
            case "Disregard":
                disregardedRecipients.add(recipient);
                disregardedMessages.add(message);
                break;
            default:
                // Unknown flag – do nothing
                break;
        }
    }

    // ===========================
    //  Required Functionality
    // ===========================

    /**
     * 1) Display the sender and recipient of all sent messages.
     *    Returns a list of formatted strings to keep it easy to test.
     */
    public List<String> getSentMessageDetails() {
        List<String> details = new ArrayList<>();
        for (int i = 0; i < sentMessages.size(); i++) {
            String line = "Recipient: " + sentRecipients.get(i)
                    + " | Message: " + sentMessages.get(i);
            details.add(line);
        }
        return details;
    }

    /**
     * 2) Display the longest message from all test data messages 1‑5.
     */
    public String getLongestMessage() {
        String longest = "";
        for (String msg : allMessages) {
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }
        return longest;
    }

    /**
     * 3) Search for a message ID and display the corresponding recipient and message.
     *    If the message ID does not exist, "Not found" is returned.
     */
    public String searchByMessageID(String id) {
        for (int i = 0; i < messageID.size(); i++) {
            if (messageID.get(i).equals(id)) {
                return "Recipient: " + allRecipients.get(i)
                        + " | Message: " + allMessages.get(i);
            }
        }
        return "Not found";
    }

    /**
     * 4) Search for all the messages (sent or stored) sent to a particular recipient.
     *    Returns a list of message strings.
     */
    public List<String> searchByRecipient(String recipient) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < allRecipients.size(); i++) {
            if (allRecipients.get(i).equals(recipient)) {
                result.add(allMessages.get(i));
            }
        }
        return result;
    }

    /**
     * 5) Delete a message using the message hash.
     *    Returns a user‑friendly success or failure message.
     */
    public String deleteByHash(String hash) {
        for (int i = 0; i < messageHash.size(); i++) {
            if (messageHash.get(i).equals(hash)) {
                String removedMessage = allMessages.remove(i);
                String removedRecipient = allRecipients.remove(i);
                messageHash.remove(i);
                messageID.remove(i);

                // Also remove from any category‑specific array if it exists there
                removeFromCategoryLists(removedRecipient, removedMessage);

              return "Message \"" + removedMessage + "\" successfully deleted.";


            }
        }
        return "Hash not found.";
    }

    /**
     * Helper to keep category‑specific lists in sync when a message is deleted.
     */
    private void removeFromCategoryLists(String recipient, String message) {
        removeFromSpecificCategory(sentRecipients, sentMessages, recipient, message);
        removeFromSpecificCategory(storedRecipients, storedMessages, recipient, message);
        removeFromSpecificCategory(disregardedRecipients, disregardedMessages, recipient, message);
    }

    private void removeFromSpecificCategory(List<String> recipients,
                                            List<String> messages,
                                            String recipient,
                                            String message) {
        for (int i = 0; i < messages.size(); i++) {
            if (recipients.get(i).equals(recipient) && messages.get(i).equals(message)) {
                recipients.remove(i);
                messages.remove(i);
                break;
            }
        }
    }

    /**
     * 6) Display a report that lists the full details of all the sent messages.
     *    The report includes: Message Hash, Recipient, Message.
     */
    public List<String> buildReport() {
        List<String> report = new ArrayList<>();
        for (int i = 0; i < allMessages.size(); i++) {
            String line = "ID: " + messageID.get(i)
                    + " | Hash: " + messageHash.get(i)
                    + " | Recipient: " + allRecipients.get(i)
                    + " | Message: " + allMessages.get(i);
            report.add(line);
        }
        return report;
    }

    // Getters to help with unit tests
    public List<String> getSentMessages() {
        return sentMessages;
    }

    public List<String> getStoredMessages() {
        return storedMessages;
    }

    public List<String> getDisregardedMessages() {
        return disregardedMessages;
    }

    public List<String> getMessageHash() {
        return messageHash;
    }

    public List<String> getMessageID() {
        return messageID;
    }

    public List<String> getAllMessages() {
        return allMessages;
    }

    public List<String> getAllRecipients() {
        return allRecipients;
    }
}
