package ru.grabovsky.interview.pingpong;

public class Main {
    public static void main(String[] args) {
        Player ping = new Player();
        Thread thread1 = new Thread(() -> {
            try {
                for (int i = 0; i < 30; i++) {
                    ping.play("ping");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                for (int i = 0; i < 30; i++) {
                    ping.play("pong");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread1.start();
        thread2.start();
    }
}
