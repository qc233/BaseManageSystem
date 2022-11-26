package pers.qc233.basemanagesystem.Pojo;

public class Bullet {
    private int[] position;
    private int moveSpeed;

    public Bullet(int[] position){
        this.position = position;
        moveSpeed = 20;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }
}
