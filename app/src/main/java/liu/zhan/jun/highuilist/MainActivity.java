package liu.zhan.jun.highuilist;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import liu.zhan.jun.highuilist.presenter.ListItemClickPresent;
import liu.zhan.jun.highuilist.recyclerview.BaseLinearDecoration;
import liu.zhan.jun.highuilist.viewIn.ListViewIn;
import liu.zhan.jun.highuilist.widget.BottomMenuDialog;
import liu.zhan.jun.highuilist.widget.SaveOrderDialg;

public class MainActivity extends BaseActivity<ListViewIn, ListItemClickPresent<ListViewIn>> implements ListViewIn {

    private RecyclerView uILists;
    private String TAG = "LOGI";
    private ListItemClickPresent presentIn;
    private BottomMenuDialog dialog;
    private SaveOrderDialg dialg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uILists = (RecyclerView) findViewById(R.id.uILists);
        //presenter持有M and view
        mPresenter.attachView(this);
        uILists.setAdapter(mPresenter.attech(this));

        BaseLinearDecoration decoration = new BaseLinearDecoration(this, LinearLayoutManager.VERTICAL);
        //自定义的间隔线
//        decoration.setmDivider(R.drawable.itme_divider);
        uILists.addItemDecoration(decoration);
        uILists.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        dialog = new BottomMenuDialog.BottomMenuBuilder()
                .addItem("拍照", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i(TAG, "onClick: 拍照");
                    }
                })
                .addItem("从相册中选择", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i(TAG, "onClick: 从相册中选择");
                    }
                }).build();
    }

    @Override
    protected ListItemClickPresent<ListViewIn> createPresenter() {
        return mPresenter = new ListItemClickPresent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView(this);
    }

    @Override
    public void jumpToActivity(Class<?> zclass) {
        Intent intent = new Intent(this, zclass);
        Intent intent2 = new Intent(this, BaseActivity.class);
        startActivity(intent);
    }

    @Override
    public void alterDialog() {
        dialog.show(getSupportFragmentManager());
    }

    @Override
    public void alterDialog1() {
        SaveOrderDialg.OnCloseListener listener = new SaveOrderDialg.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if (confirm) {
                    Toast.makeText(getApplicationContext(), "保存信息", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "取消", Toast.LENGTH_SHORT).show();
                }
                dialg.dismiss();
            }
        };
        dialg = new SaveOrderDialg(this, "是否保存内容?");
        dialg.setListener(listener);
        dialg.show();
    }
}
