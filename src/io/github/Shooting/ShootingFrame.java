package io.github.Shooting;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ShootingFrame extends JFrame {

    public ShootingPanel panel;

    public ShootingFrame() {

        panel = new ShootingPanel();

        // ウィンドウに追加
        this.add(panel);

        this.addWindowListener(new WindowAdapter(){
            public void windowClosed(WindowEvent e){
                super.windowClosed(e);
                Shooting.loop = true;
            }
        });

        // ウィンドウをクローズしたときの処理（EXIT_ON_CLOSE：終了）
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // タイトルを設定
        this.setTitle("Shooting");
        // ウィンドウの大きさ
        this.setSize(500, 500);
        // ウィンドウを中央に出現
        this.setLocationRelativeTo(null);
        // リサイズを不可能に設定
        this.setResizable(false);
        // ウィンドウの表示・非表示を設定
        this.setVisible(true);
    }
    
}
