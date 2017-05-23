package liu.zhan.jun.highuilist.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import liu.zhan.jun.highuilist.R;
import liu.zhan.jun.highuilist.recyclerview.BaseRecyclerAdapter;
import liu.zhan.jun.highuilist.recyclerview.BaseViewHolder;

/**
 * Created by 刘展俊 on 2017/5/7.
 */

public class UiListAdapter extends BaseRecyclerAdapter {
    List<String> date=new ArrayList<String>();
    private WeakReference<Context> weakContext;

    public UiListAdapter(List<String> date,WeakReference<Context> weakContext) {
        this.date = date;
        this.weakContext=weakContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(weakContext.get()).inflate(R.layout.recycler_show_ui_item,parent,false);
        return new BaseViewHolder(view,onRecyclerItemClick,weakContext.get());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        View view=holder.itemView;
        TextView contentView= (TextView) view.findViewById(R.id.content);
        String str=date.get(position);
        contentView.setText(str);
    }

    @Override
    public int getItemCount() {
        return date.size();
    }
}
