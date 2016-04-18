package com.example.wen.tutorwithparse.Models;

import java.io.Serializable;

/**
 * Created by Benson on 4/14/2016.
 */
public class Tutor implements Serializable {
    private Tutor tutor;
    private String name;
    private String message;
    private String subject;
    private int numOfStudents;
    private String phoneNumber;
    private String eMail;

    public Tutor(String name, String subject, int numOfStudents, String message, String phoneNumber, String eMail) {
        this.message = message;
        this.numOfStudents = numOfStudents;
        this.name = name;
        this.subject = subject;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public String getPhoneNumber(){return phoneNumber;}

    public String geteMail(){return eMail;};

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNumOfStudents() {
        return numOfStudents;
    }

    public void setNumOfStudents(int numOfStudents) {
        this.numOfStudents = numOfStudents;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
