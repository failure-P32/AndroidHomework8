package com.bytedance.camera.demo;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.VideoView;

import java.io.File;

public class VideoPreviewActivity extends Activity {

    private String mPath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_videopreview);

        VideoView videoView = findViewById(R.id.vv);
        mPath = getIntent().getStringExtra("path");
        videoView.setVideoPath(mPath);
        videoView.start();
        videoView.setOnCompletionListener(mediaPlayer -> {
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        });

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

    @Override
    protected void onStart() {
        super.onStart();
        VideoView videoView = findViewById(R.id.vv);
        videoView.start();
        videoView.setOnCompletionListener(mediaPlayer -> {
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        });
    }
}
