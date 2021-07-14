package pl.kul.todos.adapter.gui.login;

public class PersonToLogIn {

    private String login;
    private String pass;

    public PersonToLogIn(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }
}
