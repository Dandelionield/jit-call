package com.jitcall;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class JitsiActivity extends AppCompatActivity {

	protected final String JitsiServer = "https://jitsi1.geeksec.de/";

	@Override protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		String meetingId = getIntent().getStringExtra("meetingId");
		String name = getIntent().getStringExtra("name");
		
		try{

			JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder().setServerURL(

				new URL(this.JitsiServer)

			).setRoom(

				meetingId

			).setFeatureFlag(

				"welcomepage.enabled", false

			).build();

			JitsiMeetActivity.launch(this, options);
			
		}catch(MalformedURLException e){

			throw new RuntimeException("Jitsi server's URL is invalid", e);

		}
		
		finish();

	}

}