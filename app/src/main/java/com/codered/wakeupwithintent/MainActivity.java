package com.codered.wakeupwithintent;

import java.util.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.TableRow.*;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import android.content.Context;
import android.os.PowerManager.WakeLock;
import junit.framework.*;
import android.content.Intent;
import android.app.usage.*;
import android.media.session.MediaSession;
import android.net.Uri;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.pm.*;

public class MainActivity extends Activity
{

	private void wakeUp() {
		PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
		WakeLock wl = pm.newWakeLock(
			PowerManager.FULL_WAKE_LOCK |
			PowerManager.ACQUIRE_CAUSES_WAKEUP |
			PowerManager.ON_AFTER_RELEASE,
			"MywakeLock"
		);
		wl.acquire();
	}
	private void test(String s) {
		TextView tv = findViewById(R.id.maintext);
		tv.setText(s);
		//wakeUp();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == 4) {
			System.exit(0);
		}
		if (keyCode == 24) {
			test(checkApp("com.yado.sbh") + "");
		}
		return true;
	}

	private boolean isRunning(String app) {
		return false;
	}

	private void keepUpdating() {
		new android.os.Handler().postDelayed(
			new Runnable() {
				public void run() {
					keepUpdating();
				}
			},
			100
		);
	}

	private boolean checkForPermission() {
		Context context = getApplicationContext();
		AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
		int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, android.os.Process.myUid(), context.getPackageName());
		return mode == AppOpsManager.MODE_ALLOWED;
	}

	private List<String> GetAllProcesses() {
		List<String> procs = new ArrayList<>();
		UsageStatsManager usm = (UsageStatsManager)this.getSystemService(Context.USAGE_STATS_SERVICE);
		long time = System.currentTimeMillis();
		List<UsageStats> appList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,  time - 10000, time);
		for (int i = 0; i < appList.size(); i++) {
			procs.add(appList.get(i).getPackageName());
		}
		return procs;
	}

	private boolean checkApp(String name) {
		UsageStatsManager usm = (UsageStatsManager)this.getSystemService(Context.USAGE_STATS_SERVICE);
		return usm.isAppInactive(name);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		if (!checkForPermission()) {
			startActivity(new Intent(android.provider.Settings.ACTION_USAGE_ACCESS_SETTINGS));
		}


		test("Please set procs.");
	}
}



















