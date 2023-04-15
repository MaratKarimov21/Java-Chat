package ru.smak.net;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class MainWindow extends JFrame {
    private JLabel lbl;
    private JTextField tf;
    private JButton btn;
    private JScrollPane sp;
    public JTextArea outputTarget;
    public MainWindow(IOAdapter adapter) throws IOException {
        setSize(600, 450);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        lbl = new JLabel("123");
        tf = new JTextField();
        btn = new JButton("Отправить");
        outputTarget = new JTextArea();
        sp = new JScrollPane(outputTarget);
        GroupLayout gl = new GroupLayout(getContentPane());
        setLayout(gl);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adapter.enter(tf.getText());
                tf.setText("");
            }
        });

        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
//                for (ActionListener al : btn.getActionListeners()) {
//                    btn.removeActionListener(al);
//                }
                adapter.closeClient();
                System.exit(0);
            }
        });

        gl.setHorizontalGroup(
                gl.createSequentialGroup()
                                .addGap(8)
                        .addGroup(gl.createParallelGroup()
                        .addComponent(lbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        .addGroup(
                                gl.createSequentialGroup()
                                        .addComponent(tf, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                                        .addGap(4)
                                        .addComponent(btn, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
                        .addComponent(sp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        )
                        .addGap(8)
        );
        gl.setVerticalGroup(
                gl.createSequentialGroup()
                        .addGap(8)
                        .addComponent(lbl, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                        .addGap(8)
                        .addGroup(
                                gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(tf, 27, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(8)
                        .addComponent(sp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        .addGap(8)
        );
    }
}
