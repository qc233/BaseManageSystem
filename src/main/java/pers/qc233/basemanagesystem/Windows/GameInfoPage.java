package pers.qc233.basemanagesystem.Windows;

import pers.qc233.basemanagesystem.Pojo.User;

import javax.swing.*;
import java.awt.*;

public class GameInfoPage {
    private JFrame gameWindow = new JFrame("游戏界面");
    private User user;

    public GameInfoPage(User user){
        this.user = user;
        Init();
    }

    private void Init(){
        gameWindow.setSize(450,700);
        JPanel gamePage = new JPanel();
        JPanel infoPage = new JPanel();

        gamePage.setBounds(0,0,450,700);
        infoPage.setBounds(0,0,450,700);

        gameWindow.add(gamePage);
        gamePage.setVisible(false);
        gameWindow.add(infoPage);

        gamePage.setLayout(null);
        infoPage.setLayout(null);

        // 显示用户的用户名
        JLabel username = new JLabel(this.user.getUsername());
        username.setFont(new Font("", Font.CENTER_BASELINE, 24));
        username.setVerticalAlignment(JLabel.CENTER);
        username.setHorizontalAlignment(JLabel.CENTER);
        username.setBounds(125, 100, 200, 30);
        infoPage.add(username);

        // 显示用户的最高得分
        JLabel maxScore = new JLabel("最高得分：" + this.user.getMaxScore());
        maxScore.setFont(new Font("", Font.CENTER_BASELINE, 24));
        maxScore.setVerticalAlignment(JLabel.TOP);
        maxScore.setHorizontalAlignment(JLabel.CENTER);
        maxScore.setBounds(125,150,200,30);
        infoPage.add(maxScore);

        // 显示开始游戏按钮
        JButton startGame = new JButton("开始游戏");
        startGame.setBounds(150, 350, 150, 40);
        infoPage.add(startGame);

        // 显示排行榜按钮
        JButton leaderBoard = new JButton(" 排行榜 ");
        leaderBoard.setBounds(150, 450, 150, 40);
        infoPage.add(leaderBoard);

        // 显示退出游戏按钮
        JButton exitGame = new JButton("退出游戏");
        exitGame.setBounds(150, 550, 150, 40);
        infoPage.add(exitGame);

    }

    public void show(){
        gameWindow.setVisible(true);
    }

}
