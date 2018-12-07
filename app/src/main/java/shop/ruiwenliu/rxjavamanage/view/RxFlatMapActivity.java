package shop.ruiwenliu.rxjavamanage.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.transform.Source;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.internal.operators.observable.ObserverResourceWrapper;
import io.reactivex.schedulers.Schedulers;
import shop.ruiwenliu.rxjavamanage.R;
import shop.ruiwenliu.rxjavamanage.bean.SexVo;
import shop.ruiwenliu.rxjavamanage.bean.StudentVo;
import shop.ruiwenliu.rxjavamanage.bean.UserVo;

/**
 * Created by Amuse
 * Data:2018/12/6 0006
 * Desc:FlatMap
 */

public class RxFlatMapActivity extends BaseActivity{

    public static Intent getIntent(Context context) {
        return new Intent(context, RxFlatMapActivity.class);
    }
    @Override
    public String getRxTitle() {
        return mActivity.getString(R.string.flatMap);
    }

    @Override
    public void initData() {
//        ssss();

       final StringBuffer sb=new StringBuffer();
        Flowable.fromIterable(getStudentData())
                .flatMap(new Function< StudentVo, Publisher<StudentVo.Source>>() {
                    @Override
                    public Publisher<StudentVo.Source> apply(@NonNull StudentVo student) throws Exception {
                        sb.append("\n");
                        sb.append("名字："+student.name);
                        return Flowable.fromIterable(student.list);
                    }
                })
                .subscribe(new Consumer<StudentVo.Source>() {
                    @Override
                    public void accept(@NonNull StudentVo.Source source) throws Exception {
                        sb.append("科目："+source.sourceName);
                        sb.append(",分数："+source.sourceScore);
                        Log.i("rxjava======",sb.toString());
                    }
                });


        tvContent.setText(sb.toString());





    }





    /**
     * 有序的排列
     */
    private void concatMap(){


        Observable.fromArray(1,2,3,4,5)
                .concatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(@NonNull Integer integer) throws Exception {

                        int delay = 0;
                        if(integer == 3){
                            delay = 500;//延迟500ms发射
                        }
                        return Observable.just(integer *10).delay(delay, TimeUnit.MILLISECONDS);
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        tvContent.append("accept:"+integer+"\n");
//                        Log.e("zhouwei","accept:"+integer);
                    }
                });

    }

    /**
     * 无序的排列
     */
    private void flatMap(){
        Observable.fromArray(1,2,3,4,5)
                .flatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(@NonNull Integer integer) throws Exception {

                        int delay = 0;
                        if(integer == 3){
                            delay = 500;//延迟500ms发射
                        }
                        return Observable.just(integer *10).delay(delay, TimeUnit.MILLISECONDS);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        tvContent.append("accept:"+integer+"\n");
//                        Log.e("zhouwei","accept:"+integer);
                    }
                });

    }



    /**
     * 模拟学生数据
     * @return
     */
    private List<StudentVo> getStudentData(){


        List<StudentVo> list=new ArrayList<>();

        for(int i=0;i<6;i++){
            StudentVo studentVo=new StudentVo();
            studentVo.name="张三"+i;
            studentVo.list=getSourceData(i);
            list.add(studentVo);
        }
        return  list;
    }

    /**
     * 模拟从后台获取的列表数据
     * @return
     */
    private List<StudentVo.Source> getSourceData(int c){
        List<StudentVo.Source> list=new ArrayList<>();

        StudentVo.Source source=new  StudentVo.Source();
        source.sourceName="语文";
        source.sourceScore="6"+c;
        StudentVo.Source source1=new  StudentVo.Source();
        source1.sourceName="数学";
        source1.sourceScore="7"+c;
        StudentVo.Source source2=new  StudentVo.Source();
        source2.sourceName="英语";
        source2.sourceScore="8"+c;
        list.add(source);
        list.add(source1);
        list.add(source2);
        return  list;
    }

}
