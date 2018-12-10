package shop.ruiwenliu.rxjavamanage.view;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZUserAction;
import cn.jzvd.JZVideoPlayer;
import shop.ruiwenliu.rxjavamanage.R;
import shop.ruiwenliu.rxjavamanage.util.JzViewOutlineProvider;
import shop.ruiwenliu.rxjavamanage.weight.FullJVideoPlayer;

/**
 * Created by Amuse
 * Data:2018/12/10 0010
 * Desc:视屏播放
 */

public class VideoActivity extends AppCompatActivity {
    @BindView(R.id.full_video)
    FullJVideoPlayer player;
    public static Intent getIntent(Context context) {
        return new Intent(context, VideoActivity.class);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);

//        player.thumbImageView  加载的缩略图片
        /**
         * JZVideoPlayer.SCREEN_LAYOUT_LIST 列表视频播放
         *
         */
        player.setUp("http://vjs.zencdn.net/v/oceans.mp4", JZVideoPlayer.SCREEN_LAYOUT_NORMAL, "video"); //设置视频地址、和标题
        player.setPressed(false);
        player.widthRatio = 16;//播放比例,可以设置为16:9,4:3
        player.heightRatio = 9;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            player.setOutlineProvider(new JzViewOutlineProvider(20));
            player.setClipToOutline(true);
        }

        player.setJzUserAction(new JZUserAction() {
            @Override
            public void onEvent(int type, String url, int screen, Object... objects) {
                switch (type) {
                    case JZUserAction.ON_CLICK_START_ICON://开始播放监听
                        Toast.makeText(VideoActivity.this, "开始播放", Toast.LENGTH_SHORT).show();
                        break;
                    case JZUserAction.ON_AUTO_COMPLETE://完成播放
                        Toast.makeText(VideoActivity.this, "播放完成", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
//        JZVideoPlayer.releaseAllVideos();
        player.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (player.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        player.setJzUserAction(null);
        super.onDestroy();
    }


}
