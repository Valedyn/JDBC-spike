package at.spengergasse.valedyn.model;

import at.spengergasse.valedyn.annotations.Column;

public class Entity {
    @Column
    protected int id;
    public int id() {
        return id;
    }


}
