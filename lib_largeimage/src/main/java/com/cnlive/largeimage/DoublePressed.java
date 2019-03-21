package com.cnlive.largeimage;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/1/25
 * <p>
 * 简介:
 */
public class DoublePressed {

    private static long time_pressed;
    private static long upload_time;

    public DoublePressed() {
    }

    public static boolean onDoublePressed() {
        return onDoublePressed(false, 2000L);
    }

    public static boolean onLongDoublePressed() {
        return onDoublePressed(true, 2000L);
    }

    public static boolean onDoublePressed(boolean isLong, long time) {
        boolean doublePressed = time_pressed + time > System.currentTimeMillis();
        if (isLong) {
            time_pressed = System.currentTimeMillis();
        } else if (upload_time < System.currentTimeMillis()) {
            upload_time = System.currentTimeMillis() + time;
            time_pressed = System.currentTimeMillis();
        }

        return doublePressed;
    }
}
