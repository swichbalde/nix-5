package com.nix;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class HorseRunTask implements Runnable {

    private final Queue<String> globalQueue = new ConcurrentLinkedQueue<>();

    @Override
    public void run() {

        int currentDistance = 0;
        Random rand = new Random();

        int distance = 1000;
        while (currentDistance < distance) {
            currentDistance += rand.nextInt((200 - 100) + 200);
            try {
                Thread.sleep(rand.nextInt((500 - 400) + 500));
            } catch (InterruptedException e) {
                throw new RuntimeException("InterruptedException");
            }
        }

        globalQueue.add(Thread.currentThread().getName());
    }

    public Queue<String> getGlobalQueue() {
        return globalQueue;
    }
}
