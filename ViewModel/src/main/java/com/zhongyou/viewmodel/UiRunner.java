package com.zhongyou.viewmodel;

import com.zhongyou.jobschedule.JobSchedule;
import com.zhongyou.jobschedule.JobScheduler;

public class UiRunner {
	private static JobScheduler sUiJobScheduler;

	public static void assignUiJobScheduler(JobScheduler jobScheduler) {
		sUiJobScheduler = jobScheduler;
	}

	public static void runOnUI(Runnable runnable) {
		runOnUI(new JobSchedule(runnable));
	}

	public static void runOnUI(JobSchedule jobSchedule) {
		sUiJobScheduler.requestJobSchedule(jobSchedule);
	}
}
