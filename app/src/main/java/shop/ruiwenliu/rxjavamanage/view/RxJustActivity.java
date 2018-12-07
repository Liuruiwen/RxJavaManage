package shop.ruiwenliu.rxjavamanage.view;


import android.content.Context;
import android.content.Intent;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import shop.ruiwenliu.rxjavamanage.R;
import shop.ruiwenliu.rxjavamanage.bean.JustVo;

/**
 * Created by Amuse
 * Data:2018/12/3 0003
 * Desc:Just
 */

public class RxJustActivity extends BaseActivity{

    public static Intent getIntent(Context context) {
        return new Intent(context, RxJustActivity.class);
    }

    @Override
    public String getRxTitle() {
        return mActivity.getString(R.string.just);
    }

    @Override
    public void initData() {
     final   String[] group={"张三","李四","王二"};

        Observable.just(6,group, new JustVo(),6)
                .distinct()//去掉重复项
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                         if(o instanceof Integer){
                             tvContent.append(String.valueOf(o)+"\n");
                         }else if(o instanceof String[]){
                             tvContent.append(group[0]+"\n");
                             tvContent.append(group[1]+"\n");
                             tvContent.append(group[2]+"\n");
                         } else if(o instanceof JustVo){
                             JustVo justVo= (JustVo) o;
                             tvContent.append(justVo.name+"\n");
                             tvContent.append(justVo.content+"\n");
                         }
                    }
                });
    }




}
