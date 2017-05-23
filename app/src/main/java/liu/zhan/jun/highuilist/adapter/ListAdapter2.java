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
 * Created by 刘展俊 on 2017/5/23.
 */

public class ListAdapter2 extends BaseRecyclerAdapter {
    List<String> date=new ArrayList<String>();
    private WeakReference<Context> weakContext;

    public ListAdapter2(List<String> date,WeakReference<Context> weakContext) {
        this.date = date;
        this.weakContext=weakContext;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(weakContext.get()).inflate(R.layout.text_item,parent,false);
        return new BaseViewHolder(view,onRecyclerItemClick,weakContext.get());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setBackgroundColor(Color.parseColor("#548822"));
            TextView textVie= (TextView) holder.itemView.findViewById(R.id.content);
            textVie.setText(date.get(position));
    }

    @Override
    public int getItemCount() {
        return date.size();
    }
}
