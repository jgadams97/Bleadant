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

public class MainActivity extends Activity
{
	public static int intentCount = 0;
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
		return true;
	}

	private boolean isRunning(String app) {
		return false;
	}

	private void keepUpdating() {
		test(MainActivity.intentCount + "");
		new android.os.Handler().postDelayed(
			new Runnable() {
				public void run() {
					keepUpdating();
				}
			},
			100
		);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		String proc = "";
		ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);

		for (RunningAppProcessInfo pid : am.getRunningAppProcesses()) {
			if (!proc.equals("")) proc += "|";
			proc += pid.processName;
			//    am.killBackgroundProcesses(pid.processName);
		}
		test(proc);
		/*if (MainActivity.intentCount == 0) {
			keepUpdating();
		}
		MainActivity.intentCount += 1;*/
	}
}



















