import java.util.UUID;

class AuthService {
    private final AccountDAO accountDAO;

    public AuthService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public boolean register(String username, String email, String password , String secretQuestion, String secretAnswer) {
        
        if (!username.matches("^[a-zA-Z0-9_-]{3,20}$")) { // added if statement to fix KAN-4 bug - Arian
            System.out.println("Username must be 3-20 characters long and can only contain letters, numbers, underscores, or hyphens.");
            return false;
        }
        if (accountDAO.getAccountByUsername(username) != null) {
            System.out.println("Username already exists.");   // added the println statement - Arian
            return false; // Username already exists
        }
            
        String id = UUID.randomUUID().toString(); // updated to accept question/answer- Arian
        Account account = new Account(id, username, email, password, secretQuestion, secretAnswer);
        accountDAO.createAccount(account);
        return true;
    }

    public Account login(String username, String password) {
        Account account = accountDAO.getAccountByUsername(username);
        if (account != null && password.equals(account.getPassword())) {
            return account;
        }
        return null;
    }

    public boolean isAuthenticated(Account account) {
        return account != null;
    }
}
