public class Account { // Included both secretQuestion and secretAnswer upon this class
    private String id; // to fix KAN-5 bug- Arian
    private String username;
    private String email;
    private String password;
    private String secretQuestion;
    private String secretAnswer; 

    public Account(String id, String username, String email, String password, String secretQuestion, String secretAnswer) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.secretQuestion = secretQuestion;
        this.secretAnswer = secretAnswer;
    }
    public String getUserDataDir() {
        String baseDir = "users";
        String fullPath = baseDir + "/" + username + "_" + id;
        java.io.File dir = new java.io.File(fullPath);
        if (!dir.exists()) {
            dir.mkdirs(); // create folders if missing
        }
        return fullPath;
    }

    
    // Getters
    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public String getSecretQuestion() { // gets SecretQuestion- Arian
        return secretQuestion;
    }

    public String getSecretAnswer() { // gets SecretAnswer- Arian
        return secretAnswer;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSecretQuestion(String q) { // sets SecretQuestion to q - Arian
        this.secretQuestion = q;
    }

    public void setSecretAnswer(String a) { // sets SecretAnswer to a - Arian
        this.secretAnswer = a;
    }

}
