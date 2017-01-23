package com.ittianyu.pocenter.features.home;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ittianyu.pocenter.R;
import com.ittianyu.pocenter.common.base.BaseApplication;
import com.ittianyu.pocenter.common.bean.ProjectBean;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by yu on 2017/1/17.
 */

public class HomeAdapter extends BaseQuickAdapter<ProjectBean, BaseViewHolder> {
    public HomeAdapter(List<ProjectBean> data) {
        super(R.layout.item_home, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ProjectBean projectBean) {
        baseViewHolder.setText(R.id.tv_title, projectBean.title);
        baseViewHolder.setText(R.id.tv_price, projectBean.price);
        baseViewHolder.setText(R.id.tv_reference, projectBean.reference);
        baseViewHolder.setText(R.id.tv_type, transferType(projectBean.type));
        baseViewHolder.setText(R.id.tv_description, projectBean.description);
        baseViewHolder.setText(R.id.tv_date, new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE).format(projectBean.time));
        baseViewHolder.setText(R.id.tv_people_count, projectBean.people + mContext.getString(R.string.unit_people));
        baseViewHolder.setText(R.id.tv_cycle, projectBean.cycle + mContext.getString(R.string.unit_day));
    }

    /**
     * transfer type from code to string
     * @param type
     * @return
     */
    private String transferType(int type) {
        List<String> types = BaseApplication.getRepertories().getAllTypes();
        if (type < types.size())
            return types.get(type);
        return "";
    }
}
