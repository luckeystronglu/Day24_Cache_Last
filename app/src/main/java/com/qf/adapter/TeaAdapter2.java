package com.qf.adapter;

import android.content.Context;

import com.qf.day24_cache.R;
import com.qf.entity.TeaEntity;

/**
 * Created by Ken on 2016/9/19.16:27
 */
public class TeaAdapter2 extends AbsBaseAdapter<TeaEntity> {

    public TeaAdapter2(Context context) {
        super(context, R.layout.item_layout);
    }

    @Override
    public void bindView(ViewHolder viewHolder, TeaEntity data) {
        viewHolder.setTextView(R.id.tv_title, data.getTitle())
                .setTextView(R.id.tv_other, data.getSource())
                .setImageView(R.id.iv_pic, data.getWap_thumb());
    }
}
