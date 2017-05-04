package de.fhws.fiw.pvs.restdemo;

import java.io.Serializable;

/**
 * Created by proj on 5/4/17.
 */
public class Person implements Serializable {
    protected int id;
    protected String name, nachname;
    protected int alter;

    private static int IdCounter = 1;

    public Person(String name, String nachname, int alter) {
        this.name = name;
        this.nachname = nachname;
        this.alter = alter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public int getAlter() {
        return alter;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }

    public void generateId()
    {
        this.id = IdCounter++;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name + " " + nachname + " (" + alter + " Jahre)";
    }
}
