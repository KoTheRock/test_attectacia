package com.mygdx.game.behavior;

import java.util.Random;

public class HeroesNames {
    private static Random rnd;
    static String[] names = {"Иван", "Антон", "Артём", "Ольга", "Светлана", "Лариса"};
    static {
        rnd = new Random();
    }

    public static String getName(int index)
    {
        if (index >= names.length)
            index = 0;
        return names[index];
    }

    public static String getRandomName()
    {
        return names[rnd.nextInt(names.length)];
    }
}