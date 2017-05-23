package liu.zhan.jun.highuilist.uiActivity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import liu.zhan.jun.highuilist.R;
import liu.zhan.jun.highuilist.adapter.ListAdapter2;
import liu.zhan.jun.highuilist.recyclerview.BaseGridDecoration;
import liu.zhan.jun.highuilist.recyclerview.BaseLinearDecoration;

public class RecyclerActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView recycler2;
    private BaseLinearDecoration Verticaldecoration;
    private BaseLinearDecoration Horizontaldecoration;
    private RecyclerView recycler3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        Verticaldecoration = new BaseLinearDecoration(this, LinearLayoutManager.VERTICAL);
        Verticaldecoration.setmDivider(R.drawable.itme_divider);
        Horizontaldecoration = new BaseLinearDecoration(this, LinearLayoutManager.HORIZONTAL);
        Horizontaldecoration.setmDivider(R.drawable.itme_divider);
        init();
        recycler.addItemDecoration(Verticaldecoration);
        recycler2.addItemDecoration(Horizontaldecoration);
        recycler2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        BaseGridDecoration griditem = new BaseGridDecoration(this,3);
        griditem.setOratation(LinearLayoutManager.VERTICAL);
        griditem.setmDivider(R.drawable.itme_divider);
        recycler3.addItemDecoration(griditem);
        recycler3.setLayoutManager(new GridLayoutManager(this,3));
    }

    public void Verticalcovert(View view) {
        recycler.setVisibility(View.VISIBLE);
        recycler2.setVisibility(View.GONE);
        recycler3.setVisibility(View.GONE);
    }

    public void Horizontalcovert(View view) {

        recycler2.setVisibility(View.VISIBLE);
        recycler.setVisibility(View.GONE);
        recycler3.setVisibility(View.GONE);
    }

    public void Verticalwg(View view){
        recycler2.setVisibility(View.GONE);
        recycler.setVisibility(View.GONE);
        recycler3.setVisibility(View.VISIBLE);

    }

    private void init() {
        recycler = (RecyclerView) findViewById(R.id.recycler);

        List<String> datas = getData();
        ListAdapter2 adapter = new ListAdapter2(datas, new WeakReference<Context>(this));
        recycler.setAdapter(adapter);
        recycler2 = (RecyclerView) findViewById(R.id.recycler2);

        recycler2.setAdapter(adapter);
        recycler3 = (RecyclerView) findViewById(R.id.recycler3);

        recycler3.setAdapter(adapter);
    }

    private List<String> getData() {
        List<String> datas = new ArrayList<>();
        datas.add("xxbd");
        datas.add("xx2");
        datas.add("xxbd");
        datas.add("xxbd");
        datas.add("r");
        datas.add("77g");
        datas.add("s3");
        datas.add("dc");
        datas.add("xxbd");
        datas.add("fx");
        datas.add("xxbd");
        datas.add("xcccxbd");
        datas.add("xxbd");
        datas.add("xxeqrbd");
        datas.add("536");
        datas.add("x345xbd");
        datas.add("24b");
        return datas;
    }
}
