package pers.qc233.basemanagesystem.Pojo;

import java.util.Random;

public class Enemy {
    private int health;
    private int moveSpeed;
    private int[] position;
    private int[] size;

    public Enemy(){
        this.health = 1;
        this.moveSpeed = 2;
        this.position = new int[]{new Random().nextInt(350) + 50, 0};
        this.size = new int[]{10, 10};
    }

    public boolean injured(){
        this.health--;
        return this.health == 0;
    }

    public int move(){
        this.position[1] += moveSpeed;
        return this.position[1];
    }

    public int[] getPosition() {
        return position;
    }
}
