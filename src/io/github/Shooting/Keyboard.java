package io.github.Shooting;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/*
 * キーボード入力を受け取る
 */
public class Keyboard extends KeyAdapter {

    // 入力しているキーを配列で保存する
    private static ArrayList<Integer> pressedButtons = new ArrayList<>();

    // キーが押されているかどうかの判定
    public static boolean isKeyPressed(int keyCode) {
        // if (pressedButtons.contains(keyCode)) {
        //     return true;
        // }
        // return false;
        return pressedButtons.contains(keyCode);
    }

    /**
     * "keyPressed"をオーバーライド
     * キーを押下している間は配列にキー番号が入るようにする
     * 重複しないよう既にあるかどうか判定する
     * 
     */
    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if (!pressedButtons.contains(e.getKeyCode()))
            pressedButtons.add(e.getKeyCode());
    }

    /*
     * "keyReleased"をオーバーライド
     * キーを離したときは削除
     * キー番号はintなので、インデックスで消す方だと認識されないようIntegerにキャストする
     */
    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        pressedButtons.remove((Integer) e.getKeyCode());
    }
    
}
