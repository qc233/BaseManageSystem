package pers.qc233.basemanagesystem.Windows;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.qc233.basemanagesystem.Controller.LoginController;
import pers.qc233.basemanagesystem.Pojo.Result;
import pers.qc233.basemanagesystem.Pojo.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

@Component
public class Login{

    @Autowired
    LoginController loginController;

     public Login() throws HeadlessException {
         // 创建 JFrame 实例
         JFrame frame = new JFrame("登录");
         frame.setSize(300, 180);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         // 构建面板
         JPanel panel = new JPanel();
         frame.add(panel);
         // 为面板添加内容
         placeComponents(panel);

         frame.setVisible(true);
     }

    private void placeComponents(JPanel panel) {

        panel.setLayout(null);
        // 用户名标签
        JLabel userLabel = new JLabel("用户名:");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        // 输入用户名的文本域
        JTextField userText = new JTextField(32);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        // 密码标签
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        // 输入密码的文本域
        JPasswordField passwordText = new JPasswordField(32);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        // 创建登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.addActionListener(onLogin(userText, passwordText));
        loginButton.setBounds(10, 100, 80, 25);
        panel.add(loginButton);

        // 创建注册按钮
        JButton registerButton = new JButton("注册");
        registerButton.setBounds(195, 100, 80, 25);
        registerButton.addActionListener(onRegister(userText, passwordText));
        panel.add(registerButton);
    }
    private AbstractAction onLogin(JTextField username, JPasswordField password){
         return new AbstractAction() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 String usernameT = username.getText();
                 String passwordT = String.valueOf(password.getPassword());
                 final Result result = loginController.login(usernameT,passwordT);
                 if (result.getCode() == 200){
                    JOptionPane.showMessageDialog(
                            null,
                            result.getMsg(),
                            "成功",
                            JOptionPane.PLAIN_MESSAGE
                    );
                 }else {
                     JOptionPane.showMessageDialog(
                             null,
                             result.getMsg(),
                             "失败",
                             JOptionPane.ERROR_MESSAGE
                     );
                 }
             }
         };
    }
    private AbstractAction onRegister(JTextField username, JPasswordField password){
         return new AbstractAction() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Result result;
                 String usernameT = username.getText();
                 String passwordT = String.valueOf(password.getPassword());
                 if (usernameT == null || passwordT == null){
                     JOptionPane.showMessageDialog(
                             null,
                             "用户名或密码不能为空",
                             "失败",
                             JOptionPane.ERROR_MESSAGE
                     );
                 }else {
                     result = loginController.register(usernameT, passwordT);
                     if(result.getCode() == 200){
                         JOptionPane.showMessageDialog(
                                 null,
                                 result.getMsg(),
                                 "成功",
                                 JOptionPane.PLAIN_MESSAGE
                         );
                     }else {
                         JOptionPane.showMessageDialog(
                                 null,
                                 result.getMsg(),
                                 "失败",
                                 JOptionPane.ERROR_MESSAGE
                         );
                     }
                 }
             }
         };
    }
}
