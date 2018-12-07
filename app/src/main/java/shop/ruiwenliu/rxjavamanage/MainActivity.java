package shop.ruiwenliu.rxjavamanage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import shop.ruiwenliu.rxjavamanage.adapter.MenuListAdapter;
import shop.ruiwenliu.rxjavamanage.view.RxAsyncActivity;
import shop.ruiwenliu.rxjavamanage.view.RxCreateActivity;
import shop.ruiwenliu.rxjavamanage.view.RxFilterActivity;
import shop.ruiwenliu.rxjavamanage.view.RxFlatMapActivity;
import shop.ruiwenliu.rxjavamanage.view.RxIntervalActivity;
import shop.ruiwenliu.rxjavamanage.view.RxJustActivity;
import shop.ruiwenliu.rxjavamanage.view.RxMergeActivity;

/**
 * https://blog.csdn.net/weixin_36709064/article/details/82919785
 * 学习案列
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_menu)
    RecyclerView mRvMenu;
    private MenuListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAdapter = new MenuListAdapter();
        mRvMenu.setLayoutManager(new LinearLayoutManager(this));
        mRvMenu.setAdapter(mAdapter);
        mAdapter.setNewData(Arrays.asList(getResources().getStringArray(R.array.menu_list)));
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(RxCreateActivity.getIntent(MainActivity.this));
                        break;
                    case 1:
                        startActivity(RxMergeActivity.getIntent(MainActivity.this));
                        break;
                    case 2:
                        startActivity(RxJustActivity.getIntent(MainActivity.this));
                        break;
                    case 3:
                        startActivity(RxFilterActivity.getIntent(MainActivity.this));
                        break;
                    case 4:
                         startActivity(RxIntervalActivity.getIntent(MainActivity.this));
                        break;
                    case 5:
                        startActivity(RxAsyncActivity.getIntent(MainActivity.this));
                        break;
                    case 6:
                        startActivity(RxFlatMapActivity.getIntent(MainActivity.this));
                        break;
                }
            }
        });
    }


}
