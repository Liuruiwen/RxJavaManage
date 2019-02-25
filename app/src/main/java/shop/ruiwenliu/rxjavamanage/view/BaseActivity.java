package shop.ruiwenliu.rxjavamanage.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shop.ruiwenliu.rxjavamanage.R;

/**
 * Created by Amuse
 * Data:2018/11/30 0030
 * Desc:简写基类
 */

public abstract class BaseActivity extends AppCompatActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.btn)
    Button btn;

    public   final String TAG = "RxJava";
    public Activity mActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mActivity=this;
        ButterKnife.bind(this);
        tvTitle.setText(getRxTitle());
        initData();
    }

    @OnClick({R.id.tv_back, R.id.tv_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_content:
                break;
        }
    }

    public abstract String getRxTitle();
    public abstract void initData();
}
