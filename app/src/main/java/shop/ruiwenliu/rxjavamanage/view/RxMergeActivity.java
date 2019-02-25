package shop.ruiwenliu.rxjavamanage.view;


import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import shop.ruiwenliu.rxjavamanage.R;
import shop.ruiwenliu.rxjavamanage.bean.SexVo;
import shop.ruiwenliu.rxjavamanage.bean.UserVo;

/**
 * Created by Amuse
 * Data:2018/12/3 0003
 * Desc:合并
 */

public class RxMergeActivity extends BaseActivity{

    public static Intent getIntent(Context context) {
        return new Intent(context, RxMergeActivity.class);
    }
    @Override
    public String getRxTitle() {
        return mActivity.getString(R.string.merge);
    }

    @Override
    public void initData() {

        /**
         * 把性别数据合并到用户中去
         */
//        Observable.zip(getSex(), getUser(), new BiFunction<SexVo, UserVo, UserVo>() {
//            @Override
//            public UserVo apply(SexVo sexVo, UserVo userVo) throws Exception {
//                userVo.sex=sexVo.sexName;
//                return userVo;
//            }
//        }).subscribe(new Consumer<UserVo>() {
//            @Override
//            public void accept(UserVo userVo) throws Exception {
//                tvContent.append("用户名："+userVo.name+"；性别："+userVo.sex+"\n");
//            }
//        });

        Observable.zip(getSexListData(), getUserListData(), new BiFunction<SexVo, UserVo, UserVo>() {
            @Override
            public UserVo apply(SexVo sexVo, UserVo userVo) throws Exception {
                userVo.sex=sexVo.sexName;
                return userVo;
            }
        }).subscribe(new Consumer<UserVo>() {
            @Override
            public void accept(UserVo userVo) throws Exception {
                tvContent.append("用户名："+userVo.name+"；性别："+userVo.sex+"\n");
            }
        });

    }
    private Observable<SexVo> getSexListData(){
        return Observable.fromIterable(getSexData());
    }
    private Observable<UserVo> getUserListData(){
        return Observable.fromIterable(getUserData());
    }

    private Observable<SexVo> getSex(){
        return Observable.create(new ObservableOnSubscribe<SexVo>() {
            @Override
            public void subscribe(ObservableEmitter<SexVo> e) throws Exception {
                List<SexVo> list=getSexData();
                e.onNext(list.get(0));
                e.onNext(list.get(1));
                e.onNext(list.get(2));
                e.onNext(list.get(3));
            }
        });
    }
    private Observable<UserVo> getUser(){
        return Observable.create(new ObservableOnSubscribe<UserVo>() {
            @Override
            public void subscribe(ObservableEmitter<UserVo> e) throws Exception {
                List<UserVo> list=getUserData();

                e.onNext(list.get(0));
                e.onNext(list.get(1));
                e.onNext(list.get(2));
                e.onNext(list.get(3));
            }
        });
    }


    /**
     * 模拟从后台获取的列表数据
     * @return
     */
    private List<SexVo> getSexData(){
        List<SexVo> list=new ArrayList<>();
        SexVo sexVo=new SexVo();
        sexVo.sexId=0;
        sexVo.sexName="女";
        SexVo sexVo1=new SexVo();
        sexVo1.sexId=1;
        sexVo1.sexName="男";
        SexVo sexVo2=new SexVo();
        sexVo2.sexId=1;
        sexVo2.sexName="男";
        SexVo sexVo3=new SexVo();
        sexVo3.sexId=0;
        sexVo3.sexName="女";
        list.add(sexVo);
        list.add(sexVo1);
        list.add(sexVo2);
        list.add(sexVo3);
        return  list;
    }

    /**
     * 模拟从后台获取的列表数据
     * @return
     */
    private List<UserVo> getUserData(){
        List<UserVo> list=new ArrayList<>();
        UserVo userVo=new UserVo();
       userVo.name="张三说";
        UserVo userVo1=new UserVo();
        userVo1.name="李四听";
        UserVo userVo2=new UserVo();
        userVo2.name="王二做";
        UserVo userVo3=new UserVo();
        userVo3.name="独孤求败";
        list.add(userVo);
        list.add(userVo1);
        list.add(userVo2);
        list.add(userVo3);
        return  list;
    }

}
