package com.jitcall;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.messaging.FirebaseMessaging;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

@CapacitorPlugin(

	name = "jitCall"

) public class jitCallPlugin extends Plugin {

	private jitCall implementation = new jitCall();

	private String currentMeetingId;

	@PluginMethod public void echo(PluginCall call) {

		String value = call.getString("value");

		JSObject ret = new JSObject();
		ret.put("value", implementation.echo(value));
		call.resolve(ret);

	}

	@PluginMethod public void initialize(PluginCall call){

		FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {

			if(task.isSuccessful()){

				String token = task.getResult();
				call.resolve(new JSObject().put("token", token));

			}else{

				call.reject("Error getting FCM token");

			}

		});

	}

	@PluginMethod public void startCall(PluginCall call){

		String meetingId = call.getString("meetingId");
		String name = call.getString("name");
		
		Intent intent = new Intent(getContext(), com.jitcall.JitsiActivity.class);
		intent.putExtra("meetingId", meetingId);
		intent.putExtra("name", name);
		getActivity().startActivity(intent);
		call.resolve();

	}

	@Override protected void handleOnNewIntent(Intent intent){

		super.handleOnNewIntent(intent);

		if(intent.getExtras()!=null){

			handleNotificationData(intent.getExtras());

		}

	}

	private void handleNotificationData(Bundle data) {

		if(data.containsKey("type") && "incoming_call".equals(data.getString("type"))) {

			JSObject callData = new JSObject();
			callData.put("meetingId", data.getString("meetingId"));
			callData.put("userFrom", data.getString("userFrom"));
			currentMeetingId = data.getString("meetingId");
			
			notifyListeners("incomingCall", callData);

		}

	}

}