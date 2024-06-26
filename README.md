# Snap-Sales

## Overview
**Snap-Sales** is an Android application designed for sellers to efficiently price products through an innovative and dynamic approach. By enabling users to continually snap pictures, the app utilizes advanced APIs to perform real-time image searches, gathering crucial product details like names, images, prices, and descriptions from multiple online sources. This streamlined process allows sellers to compile and review product data effectively with a simple click on a 'done' button, enhancing productivity in environments like outdoor markets or trade shows.

## Key Features
- **Continuous Product Photography**: Capture continuous images of products using the smartphone camera.
- **Real-time Image Search**: Utilize images captured by the user to perform real-time searches, identifying similar products online.
- **Comprehensive Product Data Retrieval**: Fetch detailed product information from multiple APIs based on the results of the image search, allowing for a thorough review of product details before pricing.

## Technologies Used
- **Android Development**: Utilizing a modern Android development framework.
- **Artificial Intelligence**: Implementing AI and machine learning for real-time image processing and text generation.
- **Data Management**: Robust backend API support for efficient data handling and storage.

## Initial Setup
1. Create a new Android project in Android Studio.
2. Set the minimum SDK and add necessary permissions for camera and internet access in the AndroidManifest.xml:
   ```xml
   <uses-permission android:name="android.permission.CAMERA"/>
   <uses-permission android:name="android.permission.INTERNET"/>
   <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
   ```
3. Develop basic camera functionality using the Camera2 API. Implement a simple button to allow users to take pictures and setup a camera preview using a TextureView or SurfaceView.
4. Integrate basic image processing with Google Vision API and fetch product information from UPCitemdb.
5. Setup a basic UI to display camera preview and product information.

## Getting Started
1. Clone the repository:
   `git clone https://github.com/Nivek-C94/snap-sales.git`
2. Navigate to the project directory:
   `cd snap-sales`
3. Install dependencies:
   `npm install` or equivalent
4. Launch the application:
   `npm start` or equivalent

## Contributions
We welcome contributions! Please consult the contributing guide for details on how to participate in making Snap-Sales even better.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.