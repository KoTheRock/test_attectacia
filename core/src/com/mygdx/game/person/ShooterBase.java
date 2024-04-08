package com.mygdx.game.person;

import com.mygdx.game.behavior.CoordXY;

import java.util.ArrayList;


public abstract class ShooterBase extends PersonBase {

    protected int ammo;
    protected int maxAmmo;
    protected int level;                       
    protected int effectiveDistance;        
    protected ShooterBase(String name, int priority, int health, int power, int agility, int defence, int distance, int ammo, int effectiveDistance, CoordXY pos)
    {
        super(name, priority, health, power, agility, defence, distance, pos);
        this.ammo = ammo;
        this.effectiveDistance = effectiveDistance;
        this.level = 1;
    }

    protected void shot(PersonBase target)
    {
//        System.out.print(" Стреляет по " + target);
        ammo--;
        float dist = position.distanceTo(target.position);
        int damage = getRound(power, 10) + (power / 10) * level;
        if (dist > effectiveDistance)
            damage *= 0.5f;
        else if (dist < effectiveDistance)
            damage *= 1.2f;

        boolean critical = (this.agility/3) >= rnd.nextInt(100);
        if (critical)
        {
            damage *= 2.0f;
        }
        int res = target.getDamage(damage);
        history = history + name + " стреляет в " + target.name + " наносит " + res + " урона";
        if (target.health <= 0)
        {
            System.out.print("\n" + target + "F");
        }
    }

    public int getAmmo() {
        return ammo;
    }
    public int getMaxAmmo(){
        return maxAmmo;
    }
    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    @Override
    public void step(ArrayList<PersonBase> enemies, ArrayList<PersonBase> friends)
    {

        System.out.println(history);
        history = "";
        if (health <= 0 || ammo <= 0)
        {
            if (ammo <= 0)
            {
                System.out.print(name + ": " + "нужны стрелы! ");
            }
            return;
        }
        PersonBase target = this.findNearestPerson(enemies);
        if (target != null)
        {
            shot(target);
        }

    }

}