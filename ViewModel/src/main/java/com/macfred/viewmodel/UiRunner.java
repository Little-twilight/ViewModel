package com.macfred.viewmodel;

import com.macfred.jobschedule.JobSchedule;
import com.macfred.jobschedule.JobScheduler;

public class UiRunner {
    private static JobScheduler sUiJobScheduler;

    public static void assignUiJobScheduler(JobScheduler jobScheduler) {
        sUiJobScheduler = jobScheduler;
    }

    public static JobScheduler getUiJobScheduler() {
        return sUiJobScheduler;
    }

    public static void runOnUI(Runnable runnable) {
        if (sUiJobScheduler.isInExecutorThread()) {
            runnable.run();
            return;
        }
        runOnUI(new JobSchedule(runnable));
    }

    public static void runOnUI2(Runnable runnable) {
        runOnUI(new JobSchedule(runnable));
    }

    public static void runOnUI(JobSchedule jobSchedule) {
        sUiJobScheduler.requestJobSchedule(jobSchedule);
    }
}
