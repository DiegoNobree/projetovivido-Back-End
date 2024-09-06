package nobre.diego.testeAuth.domain;

public enum UserRole {
    ADMIN("Admin"),
    PACIENTE("Paciente"),
    PROFESSOR("Professor"),
    ESTAGIARIO("Estagiario");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}