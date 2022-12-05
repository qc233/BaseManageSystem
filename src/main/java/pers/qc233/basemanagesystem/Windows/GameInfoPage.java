package pers.qc233.basemanagesystem.Windows;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.qc233.basemanagesystem.Controller.RecordController;
import pers.qc233.basemanagesystem.Controller.UserController;
import pers.qc233.basemanagesystem.Pojo.*;
import pers.qc233.basemanagesystem.Pojo.Record;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class GameInfoPage{
    private final JFrame gameWindow = new JFrame("游戏界面");
    private User user;

    final int SCREEN_X = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    final int SCREEN_Y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private boolean isGameRunning = false;
    private boolean isUp = false;
    private boolean isDown = false;
    private boolean isLeft = false;
    private boolean isRight = false;

    private final GamePanel gamePanel = new GamePanel();
    private final JLabel scoreValue = new JLabel(String.valueOf(0));
    private final JLabel healthValue = new JLabel(String.valueOf(10));
    private final JLabel maxScore = new JLabel();
    private final JPanel gamePage = new JPanel();
    private final JPanel infoPage = new JPanel();


    private PlayerPlant player = new PlayerPlant();
    private List<Enemy> enemies = new ArrayList<>();
    private List<Bullet> bullets = new ArrayList<>();

    @Autowired
    private RecordController recordController;

    @Autowired
    private UserController userController;

    class GamePanel extends JPanel{
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2 =(Graphics2D) g;
            List<Shape> shapes = new ArrayList<>();
            shapes.add(new Ellipse2D.Float(player.getPosition()[0], player.getPosition()[1], 20, 20));
            for (Bullet bullet : bullets){
                shapes.add(new Ellipse2D.Float(bullet.getPosition()[0], bullet.getPosition()[1], 5, 5));
            }
            for (Enemy enemy : enemies){
                shapes.add(new Ellipse2D.Float(enemy.getPosition()[0], enemy.getPosition()[1], 10, 10));
            }
            for (Shape shape:shapes){
                g2.fill(shape);
            }
        }
    }

    // 处理逻辑帧
    class GameRunnable extends Thread{

        private int countTime;

        @SneakyThrows
        @Override
        public void run() {

            int fireCount = 0;
            int enemyCount = 0;

            gameWindow.requestFocus();
            while (isGameRunning){
                Thread.sleep(16);
                if(isUp) player.move(Direction.FORWARD);
                if(isDown) player.move(Direction.BACKWARD);
                if(isLeft) player.move(Direction.LEFT);
                if(isRight) player.move(Direction.RIGHT);

                if (fireCount == 6){
                    fireCount = 0;
                    bullets.add(new Bullet(new int[]{player.getPosition()[0]+7, player.getPosition()[1]}));
                }
                bullets.removeIf(bullet -> bullet.move() < 10);
                fireCount++;

                if (enemyCount == 45){
                    enemyCount = 0;
                    enemies.add(new Enemy());
                }
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    if (enemy.move() > 700){
                        enemies.remove(enemy);
                        healthValue.setText(String.valueOf(player.injured()));
                    }
                }
                enemyCount++;


                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    int[] ep = enemy.getPosition();
                    for (int j = 0; j < bullets.size(); j++) {
                        Bullet bullet = bullets.get(j);
                        int[] bp = bullet.getPosition();
                        // 判断子弹和敌人相撞
                        if ((bp[0] > ep[0]-10&&bp[0]<ep[0]+20) && (bp[1]<ep[1] && bp[1]+10 > ep[1])){
                            if (enemy.injured()){
                                 scoreValue.setText(String.valueOf(player.addScore(ep[1] * 50 / 700)));
                                enemies.remove(enemy);
                            }
                            bullets.remove(bullet);
                            break;
                        }

                    }
                }

                gamePanel.repaint();
                if (player.getHealth() == 0){
                    Record record = new Record();
                    record.setTime(new Timestamp(new Date().getTime()));
                    record.setUsername(user.getUsername());
                    record.setScore(player.addScore(0));

                    isGameRunning = false;
                    enemies = new ArrayList<>();
                    bullets = new ArrayList<>();
                    recordController.insertRecord(record);
                    JOptionPane.showMessageDialog(
                            null, "你的得分为"+String.valueOf(player.addScore(0)),
                            "游戏结束",JOptionPane.PLAIN_MESSAGE
                    );

                    if (player.addScore(0) > user.getMaxScore()){
                        user.setMaxScore(player.addScore(0));
                        userController.update(user);
                    }

                    player = new PlayerPlant();
                    user = userController.getUserById(user);
                    maxScore.setText("最高得分：" + user.getMaxScore());

                    gamePage.setVisible(false);
                    infoPage.setVisible(true);
                }
            }
        }
    }

    // 初始化窗口内容
    public void init(User user){
        this.user = user;
        gameWindow.setSize(450,700);
        gameWindow.setLocation((SCREEN_X-450)/2, (SCREEN_Y-700)/2);
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.addKeyListener(onKey());

        gamePage.setBounds(0,0,450,700);
        infoPage.setBounds(0,0,450,700);

        gameWindow.add(gamePage);
        gamePage.setVisible(false);
        gameWindow.add(infoPage);

        gamePage.setLayout(null);
        infoPage.setLayout(null);

        //------------------------------信息界面布局--------------------------------------

        // 显示用户的用户名
        JLabel username = new JLabel(this.user.getUsername());
        username.setFont(new Font("", Font.BOLD, 24));
        username.setVerticalAlignment(JLabel.CENTER);
        username.setHorizontalAlignment(JLabel.CENTER);
        username.setBounds(125, 100, 200, 30);
        infoPage.add(username);

        // 显示用户的最高得分
        maxScore.setText("最高得分："+ user.getMaxScore());
        maxScore.setFont(new Font("", Font.BOLD, 24));
        maxScore.setVerticalAlignment(JLabel.TOP);
        maxScore.setHorizontalAlignment(JLabel.CENTER);
        maxScore.setBounds(125,150,200,30);
        infoPage.add(maxScore);

        // 显示开始游戏按钮
        JButton startGame = new JButton("开始游戏");
        startGame.addActionListener(onStart(infoPage, gamePage));
        startGame.addKeyListener(onKey());
        startGame.setBounds(150, 350, 150, 40);
        infoPage.add(startGame);

        // 显示排行榜按钮
        JButton leaderBoard = new JButton(" 排行榜 ");
        leaderBoard.addActionListener(onRank());
        leaderBoard.setBounds(150, 450, 150, 40);
        infoPage.add(leaderBoard);

        // 显示退出游戏按钮
        JButton exitGame = new JButton("退出游戏");
        exitGame.addActionListener(onExit());
        exitGame.setBounds(150, 550, 150, 40);
        infoPage.add(exitGame);

        //--------------------------------游戏界面布局-------------------------------

        gamePanel.setBounds(0, 50, 450, 650);
        gamePage.add(gamePanel);

        // 显示当前得分
        JLabel scoreText = new JLabel("当前得分： ");
        scoreText.setBounds(10, 20, 100, 30);
        gamePage.add(scoreText);
        scoreValue.setBounds(110, 20, 50, 30);
        gamePage.add(scoreValue);

        // 显示当前生命值
        JLabel healthText = new JLabel("当前生命值： ");
        healthText.setBounds(290, 20, 100, 30);
        gamePage.add(healthText);
        healthValue.setBounds(390, 20, 50, 30);
        gamePage.add(healthValue);

    }

    // 显示窗口
    public void show(){
        gameWindow.setVisible(true);
    }

    // 退出按钮事件
    private ActionListener onExit(){
        return e -> System.exit(0);
    }

    // 排行榜按钮事件
    private ActionListener onRank(){
        return e -> {
            final Result result = recordController.selectRecordPage(1, 5);
            if (result.getCode() == 200){
                String msg = "";
                int i = 0;
                for (Record record : (List<Record>) (result.getDate())) {
                    i++;
                    String time = String.format("%ty年%<tm月%<td日", record.getTime());
                    msg += String.format(
                            "第%d名：\t%s \t%s \t%s \n",
                            i,record.getUsername(),record.getScore(),time
                    );
                }
                JOptionPane.showMessageDialog(
                        null,msg,"排行榜",JOptionPane.PLAIN_MESSAGE
                );
            }
        };
    }

    // 开始游戏按钮事件
    private ActionListener onStart(JPanel info, JPanel game){
        return e -> {
            info.setVisible(false);
            game.setVisible(true);

            Thread gameThread = new GameRunnable();
            gameThread.start();
            isGameRunning = true;
        };
    }

    // 游戏界面键盘监听事件
    private KeyListener onKey(){
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP -> isUp = true;
                    case KeyEvent.VK_DOWN -> isDown = true;
                    case KeyEvent.VK_LEFT -> isLeft = true;
                    case KeyEvent.VK_RIGHT -> isRight = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP -> isUp = false;
                    case KeyEvent.VK_DOWN -> isDown = false;
                    case KeyEvent.VK_LEFT -> isLeft = false;
                    case KeyEvent.VK_RIGHT -> isRight = false;
                }
            }
        };
    }
}
