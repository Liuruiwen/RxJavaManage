package shop.ruiwenliu.rxjavamanage.view;

import android.content.Context;
import android.content.Intent;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ArrayCompositeDisposable;
import io.reactivex.subjects.AsyncSubject;
import shop.ruiwenliu.rxjavamanage.R;

/**
 * Created by Amuse
 * Data:2018/12/3 0003
 * Desc:AsyncSubject  只会加载onComplete() 之前的那个数据
 */

public class RxAsyncActivity extends BaseActivity{

    public static Intent getIntent(Context context) {
        return new Intent(context, RxAsyncActivity.class);
    }
    @Override
    public String getRxTitle() {
        return mActivity.getString(R.string.async);
    }

    @Override
    public void initData() {
        AsyncSubject<String> subject= AsyncSubject.create();
        subject.onNext("孙红雷");
        subject.onNext("黄渤");
        subject.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                     tvContent.setText(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        subject.onNext("张慧雯");
        subject.onNext("吴倩");
        subject.onComplete();
        subject.onNext("瑞文");
    }



}
