package com.example.xmotion.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;

public class SoundUtil {

	private static boolean initialized = false;
	private static MediaPlayer mp1 = null;
	private static MediaPlayer mp2 = null;
	private static boolean mIsMp1Running = false;
	private static boolean mIsMp2Running = false;
	
	private static MediaPlayer checkInitPlayer(Context context)
	{
		MediaPlayer mp = new MediaPlayer();
		try
		{
			mp.setAudioStreamType(AudioManager.STREAM_ALARM);
			AssetFileDescriptor descriptor = context.getAssets().openFd("sound/step01.mp3");
			mp.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
	        descriptor.close();
	        mp.setLooping(false);
	        mp.prepare();
//	        mp.setVolume(0.05f, 0.05f);
		}
		catch(Exception ex){
			Log.i("step", "sss, " + ex.getMessage());
		}
		return mp;
	}

	private static int mCount = 0;
	
	public static void playSound(Context context)
	{
		if (!initialized)
		{
			mp1 = checkInitPlayer(context);
			mp2 = checkInitPlayer(context);
			
			mp1.setOnCompletionListener(new OnCompletionListener(){

				@Override
				public void onCompletion(MediaPlayer mp) {
//					mp2.stop();
					mIsMp1Running = false;
				}
				
			});
			
			mp2.setOnCompletionListener(new OnCompletionListener(){

				@Override
				public void onCompletion(MediaPlayer mp) {
//					mp1.stop();
					mIsMp2Running = false;
				}
				
			});
		}
		
//		mp1.start();
		
		++mCount;

		if (mCount %2 == 1)
		{
			if (null != mp1 && !mIsMp1Running)
			{
				mIsMp1Running = true;
				mp1.start();
				Log.i("step", "sss, count: " + mCount + " mp: 1");
				return;
			}
		}
		else
		{
			if (null != mp2 && !mIsMp2Running)
			{
				mIsMp2Running = true;
				mp2.start();
				Log.i("step", "sss, count: " + mCount + " mp: 2");
				return;
			}
		}
	}
}
