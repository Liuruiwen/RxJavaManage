package shop.ruiwenliu.rxjavamanage.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import shop.ruiwenliu.rxjavamanage.R;

/**
 * Created by Amuse
 * Data:2018/12/3 0003
 * Desc:计时操作
 */

public class RxIntervalActivity extends BaseActivity {
    private Disposable mDisposable;
    private CompositeDisposable compositeDisposable;
    public static Intent getIntent(Context context) {
        return new Intent(context, RxIntervalActivity.class);
    }

    @Override
    public String getRxTitle() {
        return mActivity.getString(R.string.interval);
    }

    @Override
    public void initData() {
        compositeDisposable=new CompositeDisposable();

//        tvContent.append("interval start : " + getNowStrTime() + "\n");

        mDisposable = Flowable.interval(1, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // 由于interval默认在新线程，所以我们应该切回主线程
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        tvContent.setText("interval :"+aLong);
//                        tvContent.append("interval :" + aLong + " at " + getNowStrTime() + "\n");
                    }
                });
        compositeDisposable.add(mDisposable);
        btn.setVisibility(View.VISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compositeDisposable.remove(mDisposable);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mDisposable != null && !mDisposable.isDisposed()) {
//            mDisposable.dispose();
//            mDisposable=null;
//        }
        if(compositeDisposable!=null){
            compositeDisposable.clear();
        }
    }

    private String getNowStrTime() {
        long time = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(time));
    }
}
