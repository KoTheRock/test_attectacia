package com.mygdx.game.person;

import com.mygdx.game.behavior.CoordXY;

import java.util.ArrayList;
import java.util.stream.Collectors;


public abstract class MagicianBase extends PersonBase {



    private static final int costHeal = 10;
    private static final int manaRec =  5;

    private static final int MANA_TO_HEAL = 3;
    protected int mana;
    protected int maxMana;
    private final int resMana;
    private PersonBase resTarget;

    protected MagicianBase(String name, int priority, int health, int power, int agility, int defence, int distance, int mana, CoordXY pos) {
        super(name, priority, health, power, agility, defence, distance, pos);
        this.mana = mana;
        this.maxMana = mana;
        this.resMana = mana / 2;
        this.resTarget = null;
    }
    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public void step(ArrayList<PersonBase> enemies, ArrayList<PersonBase> friends) {

        history = "";

        if (health < 0)
            return;
        mana = Math.min(mana + manaRec, maxMana);

        if (waitRes(friends))
            return;

        if (getNumOfDead(friends, mana >= resMana) > 3)
        {
            startRes(friends);     // воскрешаем
        } else {
            doHealed(friends);                // лечим
        }
    }

    private void startRes(ArrayList<PersonBase> friends)
    {

        PersonBase p = null;
        int max = -1;
        for (PersonBase person : friends)
        {
            if (person.getHealth() < 0 && mana >= resMana)
            {
                p = person;
                break;
            }
            if (person.getHealth() == 0 && max < person.getPriority())
            {
                p = person;
                max = person.getPriority();
            }
        }

        if (p != null)
        {
            resTarget = p;
            if (mana >= resMana)
            {
                resPerson(p);
            } else {
                resTarget.health = -1;
                history = String.format(" копим ману на рес %s", resTarget);
            }
        }
    }

    private boolean waitRes (ArrayList<PersonBase> friends){
        if (resTarget == null || resTarget.getHealth() >= 0){
            resTarget = null;
            return false;
        }
        if (mana >= resMana){
            resPerson(resTarget);
        } else {
            history = String.format(" копим ману на рес %s", resTarget);
        }
        return true;
    }
    private void resPerson(PersonBase person){
        if (resTarget.getHealth() <= 0){
            person.healed(resTarget.getMaxHealth());
            mana -= resMana;
            history = String.format(" реснули %s", resTarget);
        } else  {
            history = String.format(" некого воскрешать");
        }
        resTarget = null;
    }

    private void doHealed(ArrayList<PersonBase> friends) {
        PersonBase p = null;
        int min = Integer.MAX_VALUE;
        for (PersonBase friend : friends) {
            int hp = friend.getHealth();
            if (hp > 0 && hp < friend.maxHealth && hp < min) {
                hp = hp * 100 / maxHealth;
                if (hp < min) {
                    min = hp;
                    p = friend;
                }
            }
        }
        if (p != null && min < 100) {
            int hp = p.getHealth();
            int needMana = (p.getMaxHealth() - hp) / MANA_TO_HEAL;
            int n = Math.min(mana, Math.min(needMana, costHeal));
            mana -= n;
            p.healed(n * MANA_TO_HEAL);
            history = String.format(" вылечил %s на %d хп", p, p.getHealth() - hp);
        } else {
            history = String.format(" пропускает ход.");
        }
    }

    private int getNumOfDead(ArrayList<PersonBase> friends, boolean isReservation) {
        int count = 0;
        for (PersonBase friend : friends) {
            if (friend.getHealth() == 0)
                count++;
            else if (friend.getHealth() < 0 && isReservation)
                count++;
        }
        return count;
    }
    @Override
    public int getDamage(int damage){
        int hp = super.getDamage(damage);
        if (health <= 0){
            if (resTarget != null){
                if (resTarget.getHealth() < 0)
                    resTarget.health = 0;
                resTarget = null;
            }
        }
        return hp;
    }
}