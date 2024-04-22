// CameraService.java
package com.snapsales.camera;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.util.Log;
import android.view.Surface;

public class CameraService {
    private CameraManager cameraManager;
    private String cameraId;
    private CameraDevice cameraDevice;
    private Surface previewSurface;

    public CameraService(Context context) {
        cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];  // Get the first camera available
            openCamera();
        } catch (CameraAccessException e) {
            Log.e("CameraService", "Failed to access camera", e);
        }
    }

    // Open camera and set up the camera device
    private void openCamera() {
        try {
            cameraManager.openCamera(cameraId, new CameraDevice.StateCallback() {
                @Override
                public void onOpened(CameraDevice camera) {
                    cameraDevice = camera;
                    startPreview();
                }

                @Override
                public void onDisconnected(CameraDevice camera) {
                    Log.e("CameraService", "Camera disconnected");
                }

                @Override
                public void onError(CameraDevice camera, int error) {
                    Log.e("CameraService", "Error occurred with the camera: " + error);
                }
            }, null);
        } catch (CameraAccessException | SecurityException e) {
            Log.e("CameraService", "Failed to open camera", e);
        }
    }

    // Start the camera preview
    private void startPreview() {
        // Code to start the camera preview
    }
}