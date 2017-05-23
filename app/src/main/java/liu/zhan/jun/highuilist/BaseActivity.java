package liu.zhan.jun.highuilist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import liu.zhan.jun.highuilist.presenter.BasePresenter;

/**
 * Created by 刘展俊 on 2017/5/7.
 */

public abstract class BaseActivity<V,P extends BasePresenter<V>> extends AppCompatActivity {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=createPresenter();
    }


    protected abstract P createPresenter();
}
