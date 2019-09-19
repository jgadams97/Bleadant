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

public class IntentActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		MainActivity.intentCount += 1;
	}
}



















