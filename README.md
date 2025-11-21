

## Project Structure

- `src/main/java/za/ac/iie/MessageService.java`  
  Core class that stores arrays and provides all required functionality:
  - Arrays for sent, stored and disregarded messages
  - Arrays for message hashes and IDs
  - Methods to:
    - Display all sent messages (sender + recipient)
    - Display the longest message
    - Search for a message by ID
    - Search for messages by recipient
    - Delete a message using the message hash
    - Display a full report of all messages

- `src/main/java/za/ac/iie/MessageApp.java`  
  Simple console `main` method that demonstrates the features.

- `src/main/java/za/ac/iie/JsonReader.java`  
  Class (created with the assistance of **OpenAI ChatGPT**, as required by the POE)
  which shows how to read a JSON file and load stored messages into arrays.

- `src/test/java/za/ac/iie/MessageServiceTest.java`  
  JUnit 5 test class that:
  - Uses **assertEquals** and other assertions
  - Tests all required behaviours as described in the POE tables.

- `.github/workflows/TestJava.yml`  
  GitHub Actions workflow that automatically compiles the project and runs
  all unit tests whenever you push to GitHub.

- `storedMessages.json`  
  Example JSON file that can be used with `JsonReader`.

## How to Run (NetBeans / IntelliJ)

1. Open the project as a **Maven project**.
2. Run the tests:
   - From the IDE: right‑click on project → `Run Tests`
   - Or from command line inside the project folder:

   ```bash
   mvn test
   ```


  - Searching
  - Deleting using hash
  - Displaying report
  - Unit tests
  - Automated tests (GitHub Actions)
  are implemented and can be verified directly from the code and tests.
