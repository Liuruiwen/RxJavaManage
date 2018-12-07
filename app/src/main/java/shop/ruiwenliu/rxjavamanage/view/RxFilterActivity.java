package shop.ruiwenliu.rxjavamanage.view;


import android.content.Context;
import android.content.Intent;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import shop.ruiwenliu.rxjavamanage.R;

/**
 * Created by Amuse
 * Data:2018/12/3 0003
 * Desc:过滤
 */

public class RxFilterActivity extends BaseActivity {
    public static Intent getIntent(Context context) {
        return new Intent(context, RxFilterActivity.class);
    }
    @Override
    public String getRxTitle() {
        return mActivity.getString(R.string.filter);
    }

    @Override
    public void initData() {
        Observable.just("刘亦菲", "吴倩", "鞠婧炜", "张慧雯", "杨幂", "李兰迪")
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return s.equals("鞠婧炜") || s.equals("张慧雯");
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                tvContent.append(s + "\n");
            }
        });
    }
}
