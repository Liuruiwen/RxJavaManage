package shop.ruiwenliu.rxjavamanage.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import shop.ruiwenliu.rxjavamanage.R;

/**
 * Created by Amuse
 * Data:2018/12/17 0017
 * Desc:数据类型转换
 */

public class RxDataTypeActivity extends BaseActivity {

    public static Intent getIntent(Context context) {
        return new Intent(context, RxDataTypeActivity.class);
    }

    @Override
    public String getRxTitle() {
        return mActivity.getString(R.string.dataType);
    }

    @Override
    public void initData() {
        sendRange();
    }


    /**
     * 把数据转List集合
     */
    private void dataToList() {
        Flowable.just(1, 2, 3, 4, 5)
                .toList()
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        tvContent.setText("accept: " + integers);
                    }
                });
    }

    /**
     * 把数组转List集合
     */
    private void arrayToList() {
        Integer[] items = {5, 6, 7, 9, 16, 100};
        Flowable.fromArray(items)
                .toList()
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        tvContent.setText("accept: " + integers);
                    }
                });
    }

    /**
     * 遍历数组
     */
    private void fromArray() {
        Integer[] items = {5, 6, 7, 9, 16, 100};
        Flowable.fromArray(items).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                tvContent.append("Array: " + integer + "\n");
            }
        });

    }

    /**
     * 遍历集合
     */
    private void fromIterable() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("h");
        list.add("r");
        list.add("b");
        Flowable.fromIterable(list).subscribe(new Consumer<String>() {
            @Override
            public void accept(String item) throws Exception {
                tvContent.append("Iterable: " + item + "\n");
            }
        });

    }

    /**
     * 重复发送特定的整数序列
     */
    private void sendRange() {
        Flowable
                .range(1, 5)
                .repeat(2)//重复的次数
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        tvContent.append("range: " + integer + "\n");
                    }
                });

    }


}
