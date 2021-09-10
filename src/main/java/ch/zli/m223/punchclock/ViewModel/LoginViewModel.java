package ch.zli.m223.punchclock.ViewModel;

public class LoginViewModel {

    private String email;

    private String password;

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
