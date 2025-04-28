package com.jitcall;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FCMService extends FirebaseMessagingService {

	private static final String CHANNEL_ID = "calls_channel";

	@SuppressLint("MissingPermission")
    @Override public void onMessageReceived(RemoteMessage remoteMessage) {

		if(remoteMessage.getData().containsKey("type") && "incoming_call".equals(remoteMessage.getData().get("type"))){
			
			Intent acceptIntent = new Intent(this, CallReceiver.class).setAction(

				"ACCEPT_ACTION"

			).putExtra(

				"meetingId", remoteMessage.getData().get("meetingId")

			).putExtra(

				"userFrom", remoteMessage.getData().get("userFrom")

			);
			
			PendingIntent acceptPending = PendingIntent.getBroadcast(this, 0, acceptIntent, PendingIntent.FLAG_IMMUTABLE);

			Intent rejectIntent = new Intent(this, CallReceiver.class).setAction(

				"REJECT_ACTION"

			).putExtra(

				"meetingId", remoteMessage.getData().get("meetingId")

			);
			
			PendingIntent rejectPending = PendingIntent.getBroadcast(this, 1, rejectIntent, PendingIntent.FLAG_IMMUTABLE);

			NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(

				R.drawable.ic_call

			).setContentTitle(

				remoteMessage.getData().get("title")

			).setContentText(

				remoteMessage.getData().get("body")

			).setPriority(

				NotificationCompat.PRIORITY_HIGH

			).addAction(

				R.drawable.ic_accept, "Answer", acceptPending

			).addAction(

				R.drawable.ic_reject, "Reject", rejectPending

			).setAutoCancel(true);

			NotificationManagerCompat.from(this).notify(123, builder.build());

		}

	}

}