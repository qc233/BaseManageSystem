package pers.qc233.basemanagesystem.Pojo;

import java.util.Random;

public class Enemy {
    private int health;
    private int moveSpeed;
    private int[] position;
    private int[] size;

    public Enemy(){
        this.health = 5;
        this.moveSpeed = 2;
        this.position = new int[]{new Random().nextInt(350) + 50, 0};
        this.size = new int[]{31, 31};
    }

    public boolean injured(){
        this.health--;
        return this.health == 0;
    }
}
