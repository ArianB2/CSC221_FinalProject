import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        AccountDAO accountDAO = new AccountDAO();
        AuthService authService = new AuthService(accountDAO);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            System.out.print("Select an option: ");
            int choice = Integer.parseInt(scanner.nextLine());
            
            // added a trim in input for username, email, pass, question and answer
            if (choice == 1) { //  to fix KAN-3 and KAN-5 error- Arian
                System.out.print("Username: ");
                String username = scanner.nextLine().trim(); // .trim() added
                System.out.print("Email: ");                 
                String email = scanner.nextLine().trim(); // .trim() added
                System.out.print("Password or PIN: ");
                String password = scanner.nextLine().trim(); // .trim() added
                System.out.print("Secret Question: "); // secretQuestion added
                String question = scanner.nextLine().trim();
                System.out.print("Secret Answer: ");    // secretAnswer added
                String answer = scanner.nextLine().trim();


                boolean success = authService.register(username, email, password, question, answer);
                System.out.println(success ? "Account created successfully!" : "Username already exists.");
            } else if (choice == 2) {
                System.out.print("Username: "); 
                String username = scanner.nextLine().trim(); // .trim() added
                System.out.print("Password or PIN: ");
                String password = scanner.nextLine().trim(); // .trim() added

                Account account = authService.login(username, password);
                if (account != null) {
                    System.out.println("Welcome, " + account.getUsername() + "!");
                } else {
                    System.out.println("Invalid username or password.");
                }
            } else {
                System.out.println("Exiting...");
                break;
            }
        }

        scanner.close();
    }
}
