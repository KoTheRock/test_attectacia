package com.mygdx.game.person;

import com.mygdx.game.behavior.CoordXY;

public class Lancer extends InfantryBase {

    private static final int HEALTH = 1000;
    private static final int POWER = 80;
    private static final int AGILITY = 10;
    private static final int DEFENCE = 12;
    private static final int DISTANCE = 1;


    public Lancer(String name, CoordXY pos)
    {
        super(name, 2, HEALTH, POWER, AGILITY, DEFENCE, DISTANCE, pos);
    }

    @Override
    public String toString() {
        return String.format("[Пикенёр] %s, ❤️=%d, %s", name, health, position.toString());
    }
    @Override
    public String getInfo() {
        return "Пикенер " + history;
    }
    
}