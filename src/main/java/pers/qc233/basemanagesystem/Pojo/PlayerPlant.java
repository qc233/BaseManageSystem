package pers.qc233.basemanagesystem.Pojo;

public class PlayerPlant {
    private int score;
    private final int maxHealth = 10;
    private int health;
    private float moveSpeed;
    private int weaponLevel;
    private int position[];
    final int[] SIZE = {20, 20};

    // 无参构造方法
    public PlayerPlant(){
        this.health = this.maxHealth;
        this.moveSpeed = 5.0f;
        this.weaponLevel = 1;
        this.position = new int[]{225, 450};
    }

    // 增加分数
    public int addScore(int score){
        this.score += score;
        return this.score;
    }

    // 控制玩家飞机移动
    public int[] move(Direction direction){
        switch (direction){
            case FORWARD -> position[1] -= this.position[1] - moveSpeed <= 0 ? 0 : moveSpeed;
            case LEFT -> position[0] -= this.position[0] - moveSpeed <= 0 ? 0 : moveSpeed;
            case RIGHT -> position[0] += this.position[0] + moveSpeed >= 420 ? 0 : moveSpeed;
            case BACKWARD -> position[1] += this.position[1] + moveSpeed >= 600 ? 0 : moveSpeed;
        }
        return this.position;
    }

    public int[] getPosition() {
        return position;
    }

    // 处理玩家飞机受伤事件
    public int injured(){
        this.health -= 1;
        return this.health;
    }

    // 处理玩家飞机受到治疗事件
    public int heal(){
        this.health += this.health == maxHealth ? 0 : 1;
        return this.health;
    }

    public int getHealth() {
        return health;
    }
}
