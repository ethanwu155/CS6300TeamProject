package edu.gatech.seclass.sdpvocabquiz;

public class Application {

    Student currentUser = null;

    public void login(Student student) {
        this.currentUser = student;
    }

}
