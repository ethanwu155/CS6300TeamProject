package edu.gatech.seclass.sdpvocabquiz;

public class Application {

    private String currentUser = null;

    public void login(String username) {
        this.currentUser = username;
    }

    public void logout() {
        this.currentUser = null;
    }

}
