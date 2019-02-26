package shop.ruiwenliu.rxjavamanage.view;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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
        compositeDisposable = new CompositeDisposable();
        countTime();
        btn.setVisibility(View.VISIBLE);
        btn.setText("暂停30秒");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compositeDisposable.remove(mDisposable);
                sendCode(30);
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
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    private String getNowStrTime() {
        long time = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(time));
    }

    /**
     * 发送验证码
     *
     * @param timeLong 验证码时长
     */
    private void sendCode(final int timeLong) {
        Observable.interval(0, 1, TimeUnit.SECONDS)//第一个字段是什么时候开始，第二个字段表示时隔多长时间发送，第三个字段是前面两个字段的单位现在是秒
                .take(timeLong)//表示多长时间停止，这里是发送验证码时长
                .map(new Function<Long, Long>() {//map集合对两个数据做处理，不做处理是12345，处理后是54321
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return timeLong - aLong;
                    }
                })
                .observeOn(Schedulers.io())//子线程发送
                .observeOn(AndroidSchedulers.mainThread())//主线程接收
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                        btn.setEnabled(false);
                    }
                })
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        btn.setText(String.valueOf(aLong));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        countTime();
                        btn.setText("暂停30秒");
                        btn.setEnabled(true);
                    }
                });

    }

    /**
     * 计时
     */
    private void countTime(){
        if(mDisposable==null || mDisposable.isDisposed()){
            mDisposable = Flowable.interval(0, 1, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()) // 由于interval默认在新线程，所以我们应该切回主线程
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(@NonNull Long aLong) throws Exception {
                            tvContent.setText("interval :" + aLong);
//                        tvContent.append("interval :" + aLong + " at " + getNowStrTime() + "\n");
                        }
                    });
        }
        compositeDisposable.add(mDisposable);

    }


}
