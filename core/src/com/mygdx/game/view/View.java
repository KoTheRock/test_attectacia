package com.mygdx.game.view;

import com.mygdx.game.behavior.CoordXY;
import com.mygdx.game.person.PersonBase;
import com.mygdx.game.controller.Main;

import java.util.Collections;

public class View {
    private static int step = 1;
    private static int maxLengthMsg = 0;
    private static final String top10 = formatDiv("a") + String.join("", Collections.nCopies(9, formatDiv("-b"))) + formatDiv("-c");
    private static final String midl10 = formatDiv("d") + String.join("", Collections.nCopies(9, formatDiv("-e"))) + formatDiv("-f");
    private static final String bottom10 = formatDiv("g") + String.join("", Collections.nCopies(9, formatDiv("-h"))) + formatDiv("-i");

    private static void tabSetter(int cnt, int max)
    {
        int dif = max - cnt + 2;
//      old:
//        if (dif > 0) System.out.printf("%" + dif + "s", ":\t"); else System.out.print(":\t");

        if (dif > 0)
            System.out.printf("%" + dif + "s", " ");
        System.out.print(":\t");
    }

    private static String formatDiv(String str) {
        return str.replace('a', '┌')
                .replace('b', '┬')
                .replace('c', '┐')
                .replace('d', '├')
                .replace('e', '┼')
                .replace('f', '┤')
                .replace('g', '└')
                .replace('h', '┴')
                .replace('i', '┘')
                .replace('-', '─');
    }

    private static String getChar(int x, int y)
    {
        String out = "│ ";
        for (PersonBase human: Main.all)
        {
            CoordXY pos = human.getPosition();
            if (pos.getX() == x && pos.getY() == y)
            {
                if (human.getHealth() == 0)
                {
                    out = "│" + (AnsiView.ANSI_RED + human.getInfo().charAt(0) + AnsiView.ANSI_RESET);
                    break;
                }
                if (Main.red.contains(human)) out = "│" + (AnsiView.ANSI_GREEN + human.getInfo().charAt(0) + AnsiView.ANSI_RESET);
                if (Main.blue.contains(human)) out = "│" + (AnsiView.ANSI_BLUE + human.getInfo().charAt(0) + AnsiView.ANSI_RESET);
                break;
            }
        }
        return out;
    }

    public static void view() {
        if (step == 1 ){
            System.out.print(AnsiView.ANSI_RED + "First step" + AnsiView.ANSI_RESET);
        } else {
            System.out.print(AnsiView.ANSI_RED + "Step:" + step + AnsiView.ANSI_RESET);
        }
        step++;
        Main.all.forEach((v) -> maxLengthMsg = Math.max(maxLengthMsg, v.toString().length()));
        System.out.print("_".repeat(maxLengthMsg *2));
        System.out.println();
        System.out.print(top10 + "    ");
        System.out.print(AnsiView.ANSI_BLUE + "Blue side" + AnsiView.ANSI_RESET);
        tabSetter(9, maxLengthMsg);                 // old: System.out.print(" ".repeat(maxLengthMsg -9));
        System.out.println(AnsiView.ANSI_GREEN +
                "Green side" +
                AnsiView.ANSI_RESET);    // old: System.out.println(":\tGreen side");
        for (int x = 0; x < 10; x++) {
            System.out.print(getChar(x, 0));        // old: System.out.print(getChar(0, x));
        }
        System.out.print("|    ");
        System.out.print(Main.blue.get(0));
        tabSetter(Main.blue.get(0).toString().length(), maxLengthMsg);
        System.out.println(Main.red.get(0));
        System.out.println(midl10);

        for (int y = 1; y < 9; y++)
        {
            for (int x = 0; x < 10; x++) {
                System.out.print(getChar(x, y));    // old: System.out.print(getChar(y, x));
            }
            System.out.print("|    ");
            System.out.print(Main.blue.get(y));
            tabSetter(Main.blue.get(y).toString().length(), maxLengthMsg);
            System.out.println(Main.red.get(y));
            System.out.println(midl10);
        }
        for (int x = 0; x < 10; x++) {
            System.out.print(getChar(x, 9));        // old: System.out.print(getChar(9, x));
        }
        System.out.print("|    ");
        System.out.print(Main.blue.get(9));
        tabSetter(Main.blue.get(9).toString().length(), maxLengthMsg);
        System.out.println(Main.red.get(9));
        System.out.println(bottom10);
    }
}