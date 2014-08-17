package com.example.xmotion.util;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MotionUtil {
	private SensorManager sensorManager;
	
	private static MotionUtil instance = null;
	
	public static MotionUtil getInstance()
	{
		if (null == instance)
		{
			instance = new MotionUtil();
		}
		return instance;
	}
	
	public interface OnStepChanged
	{
		public void onChanged();
	}
	public void initialize(Context context, final OnStepChanged listener)
	{
		sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
		sensorManager.registerListener(new SensorEventListener(){

			@Override
			public void onSensorChanged(SensorEvent event) {
				// TODO Auto-generated method stub
				listener.onChanged();
			}

			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				// TODO Auto-generated method stub
//				listener.onChanged();
			}
			
		}, countSensor, SensorManager.SENSOR_DELAY_FASTEST);
	}
}
