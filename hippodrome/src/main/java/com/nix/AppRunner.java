package com.nix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

public class AppRunner {

    private static final int NUM_HORSES = 10;

    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Choose your horse");
        int userHorse;
        try {
            userHorse = Integer.parseInt(reader.readLine());
            if (userHorse > 10 || userHorse < 0)
                throw new RuntimeException("Wrong input");
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException("Wrong input");
        }

        Queue<String> horseQueue = getHorseQueue();
        List<Integer> allHorses  = new ArrayList<>();

        for (String nameHorse : horseQueue) {
            allHorses.add(Integer.valueOf(nameHorse.substring(14)));
        }

        for (int i = 0; i < allHorses.size(); i++) {
            if (allHorses.get(i) == userHorse) {
                if (i == 0) {
                    System.out.println("You won!!!");
                } else {
                    System.out.println("You lose(");
                }
                System.out.println("Your horse under number " + userHorse +  " finished " + (i + 1) + " place");
            }
        }
    }

    private static Queue<String> getHorseQueue() {
        ExecutorService executorService  = Executors.newFixedThreadPool(NUM_HORSES);
        HorseRunTask horseRunTask = new HorseRunTask();

        for (int i = 0; i < NUM_HORSES; i++) {
            executorService.submit(horseRunTask);
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

        return horseRunTask.getGlobalQueue();
    }
}
