// CameraStateCallback.java
package com.snapsales.camera;

import android.hardware.camera2.CameraDevice;
import android.util.Log;

public class CameraStateCallback extends CameraDevice.StateCallback {
    @Override
    public void onOpened(CameraDevice camera) {
        // Camera is opened, set up the capture session here
        Log.d("CameraStateCallback", "Camera opened successfully");
    }

    @Override
    public void onDisconnected(CameraDevice camera) {
        Log.e("CameraStateCallback", "Camera disconnected");
    }

    @Override
    public void onError(CameraDevice camera, int error) {
        Log.e("CameraStateCallback", "Error occurred with the camera: " + error);
    }
}