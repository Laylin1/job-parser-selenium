
package com.example.jobparser;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        JobParser parser = new JobParser();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
            try {
                parser.parseJobs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.HOURS);
    }
}
