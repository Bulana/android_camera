package com.bulana.cam;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final int CONTENT_REQUEST = 1745;
    private File imageFromDirectory = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CONTENT_REQUEST) {
            if (resultCode == RESULT_OK) {
                Intent displayCapturedImageIntent = new Intent(Intent.ACTION_VIEW);
                displayCapturedImageIntent.setDataAndType(Uri.fromFile(imageFromDirectory), "image/jpeg");
                startActivity(displayCapturedImageIntent);
                finish();
            }

        }

    }

    public void scanImage(View view) {

        //intent to start camera as app starts up
        Intent capturedImageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File imageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);

        imageFromDirectory = new File(imageDirectory, "CameraContentDemo.jpeg");
        capturedImageIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFromDirectory));
        startActivityForResult(capturedImageIntent, CONTENT_REQUEST);

    }
}
