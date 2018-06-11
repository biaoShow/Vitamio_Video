package com.benxiang.vitamio_video;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<VideoInfo> list;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            initView();
            initData();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_line);

        new Thread(new Runnable() {
            @Override
            public void run() {
                list = GetLocalVieoInfo.getVideoFromSDCard(MainActivity.this);
                Message message = new Message();
                handler.sendMessage(message);
            }
        }).start();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview);
        recyclerViewAdapter = new RecyclerViewAdapter(this,list);
    }

    private void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerViewAdapter.setItemOnClick(new RecyclerViewItemOnClickListener() {
            @Override
            public void onClickItem(int position) {
                Intent intent = new Intent(MainActivity.this,PlayActivity.class);
                intent.putExtra("path",list.get(position).getPath());
                startActivity(intent);
            }
        });
//        String str = Environment.getExternalStorageDirectory()+"/4444.mp4";
////        mVideoView.setVideoURI(Uri.parse(str));
//        mVideoView.setVideoURI(Uri.parse("http://112.253.22.157/17/z/z/y/u/zzyuasjwufnqerzvyxgkuigrkcatxr/hc.yinyuetai.com/D046015255134077DDB3ACA0D7E68D45.flv"));
//        mVideoView.setMediaController(new MediaController(this));
//
////        mVideoView.setMediaController(new MediaController(this,true,relativeLayout));
//        //设置监听
//        mVideoView.setOnPreparedListener(this);
//        mVideoView.setOnErrorListener(this);
//        mVideoView.setOnCompletionListener(this);
    }

//    @Override
//    protected void onResume() {
//        if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
//              setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        }
//        super.onResume();
//    }
//
//    @Override
//    public void onPrepared(MediaPlayer mp) {
//        mVideoView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
//        Toast.makeText(this,"准备好了", Toast.LENGTH_LONG).show();
//        mVideoView.start();
//    }
//
//    @Override
//    public void onCompletion(MediaPlayer mp) {
//        Toast.makeText(this,"播放完成", Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public boolean onError(MediaPlayer mp, int what, int extra) {
//        Toast.makeText(this,"Error", Toast.LENGTH_LONG).show();
////          返回 true
//        return true;
//    }
//
//
//    @Override
//    protected void onDestroy() {
//        mVideoView.stopPlayback();
//        super.onDestroy();
//    }
}