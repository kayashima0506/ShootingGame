package io.github.Shooting;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

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

        EnumShootingScreen screen = EnumShootingScreen.START; // 列挙型なので代入するだけでいい
        
        // ゲーム画面
        int playerX = 0;
        int playerY = 0;
        int bulletInterval = 0;
        int score = 0;
        ArrayList<Bullet> bullets_player = new ArrayList<>();
        ArrayList<Bullet> bullets_enemy = new ArrayList<>();
        ArrayList<Enemy> enemies = new ArrayList<>();
        Random random = new Random();


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
                        bullets_player = new ArrayList<>();
                        enemies = new ArrayList<>();
                        playerX = 235;
                        playerY = 430;
                    }

                    break;


                case GAME:
                    // プレイヤーを描画
                    gra.setColor(Color.BLUE);
                    gra.fillRect(playerX + 10, playerY, 10, 10);
                    gra.fillRect(playerX, playerY + 10, 30, 10);

                    // 弾の情報をループさせて表示する(プレイヤー)
                    for (int i = 0; i < bullets_player.size(); i++) {
                        Bullet bullet = bullets_player.get(i);
                        gra.setColor(Color.BLUE);
                        gra.fillRect(bullet.x, bullet.y, 5, 5);
                        bullet.y -= 10;
                        // 弾が画面外にいったら消す
                        if (bullet.y < 0) {
                            bullets_player.remove(i);
                            i--;
                        }

                        // 敵に弾が当たった時
                        for (int j = 0; j < enemies.size(); j++) {
                            Enemy enemy = enemies.get(j);
                            if (bullet.x >= enemy.x && bullet.x <= enemy.x + 30 && bullet.y >= enemy.y
                                    && bullet.y <= enemy.y + 20) {
                                enemies.remove(j);
                                score += 10;
                            }
                        }
                    }
                    // System.out.println(bullets_player.size());


                    // 弾の情報をループさせて表示する(敵)
                    gra.setColor(Color.RED);

                    for (int i = 0; i < enemies.size(); i++) {
                        Enemy enemy = enemies.get(i);
                        gra.fillRect(enemy.x, enemy.y, 30, 10);
                        gra.fillRect(enemy.x + 10, enemy.y + 10, 10, 10);
                        enemy.y += 3;
                        if (enemy.y > 500) {
                            enemies.remove(i);
                        }
                        // 敵がランダムに弾を発射する
                        if (random.nextInt(80) == 1) {
                            bullets_enemy.add(new Bullet(enemy.x, enemy.y));
                        }
                    }
                    if (random.nextInt(30) == 1) {
                        enemies.add(new Enemy(random.nextInt(470), 0));
                    }

                    for (int i = 0; i < bullets_enemy.size(); i++) {
                        Bullet bullet = bullets_enemy.get(i);
                        // gra.setColor(Color.BLUE);
                        gra.fillRect(bullet.x, bullet.y, 5, 5);
                        bullet.y += 10;
                        // 弾が画面外にいったら消す
                        if (bullet.y > 500) {
                            bullets_enemy.remove(i);
                            i--;
                        }
                    }

                    // プレイヤーの移動&移動範囲の指定
                    if (Keyboard.isKeyPressed(KeyEvent.VK_LEFT)&&playerX>0) playerX -= 10;
                    if (Keyboard.isKeyPressed(KeyEvent.VK_RIGHT) && playerX < 455) playerX += 10;
                    if (Keyboard.isKeyPressed(KeyEvent.VK_UP)&&playerY>100) playerY -= 10;
                    if (Keyboard.isKeyPressed(KeyEvent.VK_DOWN) && playerY < 440) playerY += 10;

                    // 弾
                    if (Keyboard.isKeyPressed(KeyEvent.VK_SPACE) && bulletInterval == 0) {
                        bullets_player.add(new Bullet(playerX + 12, playerY));
                        bulletInterval = 8;
                    }

                    if (bulletInterval > 0) {
                        bulletInterval--;
                    }
                        
                    break;

                case GAME_OVER:
                    break;

            }

            // FPSを画面に描画する
            gra.setColor(Color.BLACK);  
            gra.setFont(new Font("SansSerif", Font.PLAIN, 10));
            gra.drawString(FPS + "FPS", 0, 460);

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
