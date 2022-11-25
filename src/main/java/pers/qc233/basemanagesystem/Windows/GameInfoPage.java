package pers.qc233.basemanagesystem.Windows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.qc233.basemanagesystem.Controller.RecordController;
import pers.qc233.basemanagesystem.Pojo.Record;
import pers.qc233.basemanagesystem.Pojo.Result;
import pers.qc233.basemanagesystem.Pojo.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Component
public class GameInfoPage {
    private JFrame gameWindow = new JFrame("游戏界面");
    private User user;

    @Autowired
    RecordController recordController;

    // 初始化窗口内容
    public void init(User user){
        this.user = user;
        gameWindow.setSize(450,700);
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        leaderBoard.addActionListener(onRank());
        leaderBoard.setBounds(150, 450, 150, 40);
        infoPage.add(leaderBoard);

        // 显示退出游戏按钮
        JButton exitGame = new JButton("退出游戏");
        exitGame.addActionListener(onExit());
        exitGame.setBounds(150, 550, 150, 40);
        infoPage.add(exitGame);

    }

    // 显示窗口
    public void show(){
        gameWindow.setVisible(true);
    }

    // 退出按钮事件
    private ActionListener onExit(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
    }

    // 排行榜按钮事件
    private ActionListener onRank(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final Result result = recordController.selectRecordPage(1, 5);
                if (result.getCode() == 200){
                    String msg = "";
                    int i = 0;
                    for (Record record : (List<Record>) (result.getDate())) {
                        String time = String.format("%ty年%<tm月%<td日 %tH:%tM", record.getTime());
                        msg += String.format(
                                "第%d名：\t%s \t%s \t%s",
                                i,record.getUsername(),record.getScore(),time
                        );
                    }
                    JOptionPane.showMessageDialog(
                            null,msg,"排行榜",JOptionPane.PLAIN_MESSAGE
                    );
                }
            }
        };
    }
}
