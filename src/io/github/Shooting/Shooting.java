package io.github.Shooting;

import java.awt.*;

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

            gra.setColor(Color.BLACK);
            gra.fillRect(100, 100, 100, 100);

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
