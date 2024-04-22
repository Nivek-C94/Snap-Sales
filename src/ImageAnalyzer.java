// ImageAnalyzer.java
package com.snapsales.imageprocessing;

import android.graphics.Bitmap;
import android.util.Log;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.android.gms.vision.text.TextBlock;
import android.util.SparseArray;

public class ImageAnalyzer {
    private TextRecognizer recognizer;

    public ImageAnalyzer() {
        recognizer = new TextRecognizer.Builder(context).build();
    }

    public void analyzeImage(Bitmap imageBitmap) {
        if (!recognizer.isOperational()) {
            Log.w("ImageAnalyzer", "Detector dependencies are not yet available. Please check your configuration.");
            return;
        }

        Frame frame = new Frame.Builder().setBitmap(imageBitmap).build();
        SparseArray<TextBlock> items = recognizer.detect(frame);
        if (items.size() == 0) {
            Log.i("ImageAnalyzer", "No text detected in the image.");
            return;
        }
        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);
            Log.d("ImageAnalyzer", "Detected text: " + item.getValue());
        }
    }
}