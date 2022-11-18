package ru.grabovsky.interview.pingpong;

public class Player {
    private String lastMessage = "pong";

    public synchronized void play(String text) throws InterruptedException {
        while (text.equals(lastMessage)) {
            this.wait();
        }
        System.out.println(text);
        lastMessage = text;
        this.notifyAll();
    }
}

