package com.codered.wakeupwithintent;

import java.util.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.content.BroadcastReceiver;
import java.awt.event.KeyEvent;

public class BluetoothActivity extends BroadcastReceiver
{
	public static int received = 0;

	@Override
	public void onReceive(Context context, Intent intent) {
		
		KeyEvent event = (KeyEvent)intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
		BluetoothActivity.received = event.getKeyCode();
		//if (KeyEvent.KEYCODE_MEDIA_PLAY == event.getKeyCode()) {    
                //call my method    
		//}    
		//if (Intent.ACTION_MEDIA_BUTTON.equals(intent.getAction())) {    
		 
		//}    
	}    
}