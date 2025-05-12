import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;

class AccountDAO {
    private final Map<String, Account> accounts = new HashMap<>();
    private final String ACCOUNT_FILE = "accounts.txt";

    public AccountDAO() {
        loadAccountsFromFile();
    }

    private void loadAccountsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) { // added secretQuestion and secretAnswer- Arian
                // Format: id|username|email|password|secretQuestion|secretAnswer 
                String[] parts = line.split("\\|");
                if (parts.length == 6) { // changed from 4 to 6 to accommodate for secret Q and A- Arian
                    String id = parts[0];
                    String username = parts[1];
                    String email = parts[2];
                    String password = parts[3];
                    String secretQuestion = parts[4];
                    String secretAnswer = parts[5];
                    
                    Account account = new Account(id, username, email, password, secretQuestion, secretAnswer);
                    accounts.put(id, account);
                }
            }
        } catch (IOException e) {
            // If file doesn't exist or is corrupted, ignore for now
            System.err.println("Could not load accounts from file: " + e.getMessage());
        }
    }

    private void saveAccountsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNT_FILE))) {
            for (Account account : accounts.values()) {
                String line = String.join("|",
                    account.getId(),
                    account.getUsername(),
                    account.getEmail(),
                    account.getPassword(),
                    account.getSecretQuestion(),    // added 2 extra lines
                    account.getSecretAnswer()       // - Arian
                );
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Could not save accounts to file: " + e.getMessage());
        }
    }

    public void createAccount(Account account) {
        accounts.put(account.getId(), account);
        saveAccountsToFile();
    }

    public Account getAccountById(String id) {
        return accounts.get(id);
    }

    public Account getAccountByUsername(String username) {
        for (Account account : accounts.values()) {
            if (account.getUsername().equals(username)) return account;
        }
        return null;
    }

    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    public void updateAccount(Account account) {
        accounts.put(account.getId(), account);
        saveAccountsToFile();
    }

    public void deleteAccount(String id) {
        accounts.remove(id);
        saveAccountsToFile();
    }
}
