package shop.ruiwenliu.rxjavamanage.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import shop.ruiwenliu.rxjavamanage.R;

/**
 * Created by Amuse
 * Data:2018/12/3 0003
 * Desc:菜单列表
 */

public class MenuListAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

    public MenuListAdapter() {
        super(R.layout.item_menu);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.btn_menu,item);
    }
}
