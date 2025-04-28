package com.jitcall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.jitcall.JitsiActivity;

public class CallReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String meetingId = intent.getStringExtra("meetingId");
		String userFrom = intent.getStringExtra("userFrom");

		if ("ACCEPT_ACTION".equals(intent.getAction())) {

			Intent jitsiIntent = new Intent(context, JitsiActivity.class);
			jitsiIntent.putExtra("meetingId", meetingId);
			jitsiIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(jitsiIntent);
			
		} else if ("REJECT_ACTION".equals(intent.getAction())) {

		}

		NotificationManagerCompat.from(context).cancel(123);
	}
}