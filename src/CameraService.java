// CameraService.java
package com.snapsales.camera;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CameraCaptureSession;
import android.util.Log;
import android.view.Surface;
import java.util.Arrays;
import androidx.annotation.NonNull;

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

    // Method to start continuous capture
    public void startContinuousCapture() {
        try {
            // Create a new capture request for continuous capture
            CaptureRequest.Builder captureBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            captureBuilder.addTarget(previewSurface); // Assuming previewSurface is correctly set up
            captureBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);

            // Start the camera preview session for continuous capture
            cameraDevice.createCaptureSession(Arrays.asList(previewSurface), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(@NonNull CameraCaptureSession session) {
                    try {
                        session.setRepeatingRequest(captureBuilder.build(), null, null);
                    } catch (CameraAccessException e) {
                        Log.e("CameraService", "Failed to start continuous capture", e);
                    }
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession session) {
                    Log.e("CameraService", "Configuration change failed");
                }
            }, null);
        } catch (CameraAccessException e) {
            Log.e("CameraService", "Failed to access camera for continuous capture", e);
        }
    }
}