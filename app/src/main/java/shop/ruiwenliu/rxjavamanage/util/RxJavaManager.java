package shop.ruiwenliu.rxjavamanage.util;

import org.reactivestreams.Publisher;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Amuse
 * Data:2019/2/14 0014
 * Desc:
 */

public class RxJavaManager {
   private static    RxJavaManager  rxJavaManager;

   public static RxJavaManager getInstance(){
       if(rxJavaManager==null){
           rxJavaManager=new RxJavaManager();
       }

       return rxJavaManager;
   }

    /**
     * 获取要解析的数组和数据
     * @param items
     * @param <T>
     * @return
     */
    public <T>Flowable getFromArray(T... items){
        return   Flowable.fromArray(items);
    }

    /**
     * 获取要解析的集合和数据
     * @param list
     * @param <T>
     * @return
     */
    public <T>Flowable getFromIterable(List<T> list){
        return   Flowable.fromIterable(list);
    }


    /**
     * 统一线程处理
     * @param <T>
     * @return
     */
    public  <T> FlowableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    /**
     * 合并数据
     * @param list
     * @param consumer
     * @param <T>
     */
   public <T>void   concat(List<Publisher<T>> list, Consumer<T> consumer){
       Flowable.concat(list).subscribe(consumer);
   }
    /**
     * 解析数组
     * @param consumer
     * @param items
     * @param <T>
     */
   public <T> void  fromArray(Consumer<T> consumer, T... items){
       getFromArray(items).subscribe(consumer);
   }

    /**
     * 解析集合
     * @param consumer
     * @param items
     * @param <T>
     */
    public <T> void  fromIterable(Consumer<T> consumer, List<T> items){
        getFromIterable(items).subscribe(consumer);
    }

    /**
     * 数组转List
     * @param consumer
     * @param items
     * @param <T>
     */
    public <T>void  toList(Consumer<List<T>> consumer, T... items){
        getFromArray(items).toList().subscribe(consumer);
    }



}

