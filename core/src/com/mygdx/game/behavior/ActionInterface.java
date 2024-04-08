package com.mygdx.game.behavior;

import com.mygdx.game.person.PersonBase;

import java.util.ArrayList;

public interface ActionInterface {
    void step(ArrayList<PersonBase> enemies, ArrayList<PersonBase> friends);

    String getInfo();
}
