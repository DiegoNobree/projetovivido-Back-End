package nobre.diego.testeAuth.domain;

public enum UserRole {
    ADMIN("Admin"),
    FUNCIONARIO("Funcionário"),
    CLIENTE("Cliente");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}