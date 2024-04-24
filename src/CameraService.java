// CameraService.java
package com.snapsales.camera;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.util.Log;
import android.view.Surface;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

public class CameraService {
    private CameraManager cameraManager;
    private String cameraId;
    private CameraDevice cameraDevice;
    private Surface previewSurface;
    private Activity activity; // Context for permission handling

    public CameraService(Activity activity) {
        this.activity = activity;
        this.cameraManager = (CameraManager) activity.getSystemService(Context.CAMERA_SERVICE);
        checkAndRequestPermissions();
    }

    private void checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSIONS_CODE);
        } else {
            initializeCamera();
        }
    }

    private void initializeCamera() {
        try {
            cameraId = cameraManager.getCameraIdList()[0];
            openCamera();
        } catch (CameraAccessException e) {
            Log.e("CameraService", "Failed to access camera", e);
        }
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
