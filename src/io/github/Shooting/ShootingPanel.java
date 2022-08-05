package io.github.Shooting;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class ShootingPanel extends JPanel {
    // コンポーネントが描画されるときに呼び出される
    public BufferedImage image;

    // 描画は自作するので継承
    public ShootingPanel() {
        super();
        this.image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        g.drawImage(image, 0, 0, this);
    }

    // 結果を表示する関数
    public void draw() {
        // 画面更新される
        this.repaint();
    }

}
