// CameraService.java
package com.snapsales.camera;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.util.Log;

public class CameraService {
    private CameraManager cameraManager;
    private String cameraId;

    public CameraService(Context context) {
        cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];  // Get the first camera available
        } catch (CameraAccessException e) {
            Log.e("CameraService", "Failed to access camera", e);
        }
    }

    // Method to open camera
    public void openCamera() {
        try {
            cameraManager.openCamera(cameraId, new CameraStateCallback(), null);
        } catch (CameraAccessException | SecurityException e) {
            Log.e("CameraService", "Failed to open camera", e);
        }
    }
}