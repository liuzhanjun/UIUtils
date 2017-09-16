package liu.zhan.jun.highuilist.modelim;

import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import liu.zhan.jun.highuilist.adapter.UiListAdapter;
import liu.zhan.jun.highuilist.modelin.ListModelIn;
import liu.zhan.jun.highuilist.recyclerview.BaseRecyclerAdapter;

/**
 * Created by 刘展俊 on 2017/5/7.
 */

public class ListModleIm implements ListModelIn {
    @Override
    public BaseRecyclerAdapter loadData(Context mContext) {
        List<String> data=new ArrayList<String>();

        data.add("ToolBar");
        data.add("v4包下的RoundeBitmapDrawFactory,将图片设置圆角");
        data.add("LinearlayoutCompat线性布局给子元素加间隔线");
        data.add("recyclerView");
        data.add("BottomMenudialog");
        data.add("CoordinatorLayout,AppBarLayout,CollapsingToolbarLayout，NestedScrollView");
        data.add("dialog1");
        data.add("NavigationView");
        data.add("SnackBar");
        data.add("TextInputLayotu");
        data.add("ToolSeachBarView");
        data.add("Palette");
        data.add("TableLayout");
        data.add("CardView");
        data.add("FloatingActionBar");
        data.add("CoordinatorLayout_Behavior_fab");


        UiListAdapter adapter=new
                UiListAdapter(
                data,new WeakReference<Context>(mContext));
        return adapter;
    }
}
