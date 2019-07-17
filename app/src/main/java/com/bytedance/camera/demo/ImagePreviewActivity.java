package com.bytedance.camera.demo;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import java.io.File;

public class ImagePreviewActivity extends Activity {

    private String mPath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_imagepreview);

        ImageView imageView = findViewById(R.id.iv);
        mPath = getIntent().getStringExtra("path");
        imageView.setImageURI(Uri.fromFile(new File(mPath)));

        findViewById(R.id.ib_ok).setOnClickListener(v -> finish());
        findViewById(R.id.ib_c).setOnClickListener(v -> {
            File file = new File(mPath);
            if (file.exists() && file.isFile()) {
                if (file.delete()) { // 删除后相册重新扫描
                    MediaScannerConnection.scanFile(this, new String[]{mPath}, null, null);
                }
            }
            finish();
        });
    }
}
