package shop.ruiwenliu.rxjavamanage.view;

import android.content.Context;
import android.content.Intent;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import shop.ruiwenliu.rxjavamanage.R;

/**
 * 初体验
 */
public class RxCreateActivity extends BaseActivity {

    public static Intent getIntent(Context context) {
        return new Intent(context, RxCreateActivity.class);
    }

    @Override
    public String getRxTitle() {
        return mActivity.getString(R.string.create);
    }

    @Override
    public void initData() {
        rxJavaCreateOne();
    }


    /**
     * 拆解
     */
    private void rxJavaCreateOne() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            private Disposable mDisposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                tvContent.append("onSubscribe : " + d.isDisposed() + "\n");
                mDisposable = d;
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                tvContent.append("onNext : value : " + integer + "\n");
                if (integer == 3) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
                }
                tvContent.append("onNext : isDisposable : " + mDisposable.isDisposed() + "\n");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                tvContent.append("onError : value : " + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                tvContent.append("onComplete" + "\n");
            }
        };

        observable.subscribe(observer);


    }

    private void rxJavaCreateTwo() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {
            private Disposable mDisposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                tvContent.append("onSubscribe : " + d.isDisposed() + "\n");
                mDisposable = d;
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                tvContent.append("onNext : value : " + integer + "\n");
                if (integer == 3) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
                }
                tvContent.append("onNext : isDisposable : " + mDisposable.isDisposed() + "\n");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                tvContent.append("onError : value : " + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                tvContent.append("onComplete" + "\n");
            }
        });
    }
}
