package com.benxiang.vitamio_video;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * 播放视频Activity
 * Created by ZeQiang Fang on 2018/6/9.
 */

public class PlayActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener,MediaPlayer.OnCompletionListener {

    private VideoView videoView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playvideo_activty);

        videoView = findViewById(R.id.vv_play);
        String str = getIntent().getStringExtra("path");
        videoView.setVideoURI(Uri.parse(str));
//        videoView.setVideoURI(Uri.parse("http://112.253.22.157/17/z/z/y/u/zzyuasjwufnqerzvyxgkuigrkcatxr/hc.yinyuetai.com/D046015255134077DDB3ACA0D7E68D45.flv"));
        videoView.setMediaController(new MediaController(this));

//        mVideoView.setMediaController(new MediaController(this,true,relativeLayout));
        //设置监听
        videoView.setOnPreparedListener(this);
        videoView.setOnErrorListener(this);
        videoView.setOnCompletionListener(this);

    }

    @Override
    protected void onResume() {
        if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
              setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        videoView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        Toast.makeText(this,"准备好了", Toast.LENGTH_LONG).show();
        videoView.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Toast.makeText(this,"播放完成", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Toast.makeText(this,"Error", Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    protected void onDestroy() {
        videoView.stopPlayback();
        super.onDestroy();
    }
}
