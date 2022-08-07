package com.codezilla.basicnote.DataNote;

public class Script {
    public int id;
    public String nText;

    public Script(int id, String nText) {
        this.id = id;
        this.nText = nText;
    }
    public Script()
    {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnText() {
        return nText;
    }

    public void setnText(String nText) {
        this.nText = nText;
    }
}
