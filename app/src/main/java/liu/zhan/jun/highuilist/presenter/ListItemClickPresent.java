package liu.zhan.jun.highuilist.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import liu.zhan.jun.highuilist.modelim.ListModleIm;
import liu.zhan.jun.highuilist.modelin.ListModelIn;
import liu.zhan.jun.highuilist.recyclerview.BaseRecyclerAdapter;
import liu.zhan.jun.highuilist.recyclerview.OnRecyclerItemClick;
import liu.zhan.jun.highuilist.uiActivity.AppBarLayoutActivity;
import liu.zhan.jun.highuilist.uiActivity.LinearLayoutCompatActivity;
import liu.zhan.jun.highuilist.uiActivity.RecyclerActivity;
import liu.zhan.jun.highuilist.uiActivity.ToolBarActivity;
import liu.zhan.jun.highuilist.viewIn.ListViewIn;
import liu.zhan.jun.highuilist.widget.BottomMenuDialog;

/**
 * Created by 刘展俊 on 2017/5/7.
 */

public class ListItemClickPresent<T> extends BasePresenter<T> {
    public static final String TAG = "LOGI";
    //持有m,和view
    private ListModelIn listModel;


    public BaseRecyclerAdapter attech(Context mContext) {
        listModel = new ListModleIm();
        BaseRecyclerAdapter adapter = listModel.loadData(mContext);
        adapter.setOnRecyclerItemClick(new OnRecyclerItemClick() {
            @Override
            public void onClick(View veiw, int position) {
                switch (position) {
                    case 0:
                        ((ListViewIn) getView()).jumpToActivity(ToolBarActivity.class);
                        break;
                    case 1:
                        ((ListViewIn) getView()).jumpToActivity(ToolBarActivity.class);
                        break;
                    case 2:
                        ((ListViewIn) getView()).jumpToActivity(LinearLayoutCompatActivity.class);
                        break;
                    case 3:
                        ((ListViewIn) getView()).jumpToActivity(RecyclerActivity.class);
                        break;
                    case 4:
                        ((ListViewIn) getView()).alterDialog();
                        break;
                    case 5:
                        ((ListViewIn) getView()).jumpToActivity(AppBarLayoutActivity.class);


                        break;
                    case 6:
                        ((ListViewIn) getView()).alterDialog1();


                        break;
                }
            }

            @Override
            public void LongPress(View veiw, int position) {

            }
        });

        return adapter;
    }
}
