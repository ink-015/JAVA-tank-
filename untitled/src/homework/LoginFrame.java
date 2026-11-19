package homework;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    public LoginFrame(String title) {
        super(title);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FlowLayout fl=new FlowLayout();
        setLayout(fl);

        JLabel usernameLabel = new JLabel("登录名：");
        add(usernameLabel);

        JTextField usernameField = new JTextField(20);
        add(usernameField);

        JLabel passwordLabel = new JLabel("密码：");
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField(20);
        add(passwordField);

        JButton loginButton = new JButton("确定");
        add(loginButton);

        JButton cancelButton = new JButton("取消");
        add(cancelButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password =new String(passwordField.getPassword());

            if ("admin".equals(username) && "123".equals(password)) {
                JOptionPane.showMessageDialog(this, "登录成功", "成功", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "登录名或密码错误", "失败", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
