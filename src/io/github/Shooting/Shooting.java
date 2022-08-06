package io.github.Shooting;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Shooting {
    // ShootingFrameの変数を作成
    public static ShootingFrame shootingFrame;
    // 
    public static boolean loop;

    public static void main(String args[]) {
        shootingFrame = new ShootingFrame();
        loop = true;

        //　図形を描画するグラフィックスを取得
        Graphics gra = shootingFrame.panel.image.createGraphics();

        // FPS制限
        long startTime;
        long fpsTime = 0;
        int fps = 30;
        int FPS = 0;
        int FPSCount = 0;

        EnumShootingScreen screen = EnumShootingScreen.START;   // 列挙型なので代入するだけでいい

        while (loop) {
            // Unix時間が1000以上 == 1秒経過
            if ((System.currentTimeMillis() - fpsTime) >= 1000) {
                fpsTime = System.currentTimeMillis();   // 処理開始時間を取得
                FPS = FPSCount;
                FPSCount = 0;
                // System.out.println(FPS);
            }
            FPSCount++;
            startTime = System.currentTimeMillis();

            // 画面が描画されたときに白で塗りつぶし
            gra.setColor(Color.WHITE);
            gra.fillRect(0, 0, 500, 500);

            // gra.setColor(Color.BLACK);
            // gra.fillRect(100, 100, 100, 100);

            switch (screen) {
                case START:
                    gra.setColor(Color.BLACK);
                    // 中央に表示させるため変数に代入し、FontMetricsを使う
                    Font font = new Font("SansSerif", Font.PLAIN, 50);
                    gra.setFont(font);
                    FontMetrics metrics = gra.getFontMetrics(font);
                    gra.drawString("Shooting", 250 - (metrics.stringWidth("Shooting") / 2), 100);

                    // スペースキーでスタートさせる
                    font = new Font("SansSerif", Font.PLAIN, 20);
                    gra.setFont(font);
                    metrics = gra.getFontMetrics(font);
                    gra.drawString("Press SPACE to Start", 250 - (metrics.stringWidth("Press SPACE to Start") / 2),
                            150);
                    // 押されたら遷移する
                    if (Keyboard.isKeyPressed(KeyEvent.VK_SPACE)) {
                        screen = EnumShootingScreen.GAME;
                    }

                    break;
                case GAME:
                    break;

                case GAME_OVER:
                    break;

            }

            // FPSを画面に描画する
            gra.setColor(Color.BLACK);  
            gra.setFont(new Font("SansSerif", Font.PLAIN, 10));
            gra.drawString(FPS + "FPS", 0, 470);

            // バッファした結果を表示する
            shootingFrame.panel.draw();

            try {
                long runTime = System.currentTimeMillis() - startTime;
                // 33ms以下かどうか
                if (runTime < (1000 / fps)) {
                    // プログラムを停止させる
                    Thread.sleep((1000 / fps) - (runTime));
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // System.out.println(System.currentTimeMillis() - startTime);
        }

    }
    
}
