package liu.zhan.jun.highuilist.presenter;

import java.lang.ref.WeakReference;

/**
 * Created by 刘展俊 on 2017/5/7.
 */

public class BasePresenter<T> {
    //使用弱引用持有view
    protected T view;
    /**
     * 当内存不足释放内存
     */
    protected WeakReference<T> mViewRef;
    /**
     * 关联view
     * @param view
     */
    public void attachView(T view){
        mViewRef=new WeakReference<T>(view);
    }

    /**
     * 解除关联
     *
     */
    public void detachView(T view){
        if (mViewRef!=null){
            mViewRef.clear();;
            mViewRef=null;
        }
    }

    /**
     * 获取View
     */
    protected T getView(){
        return mViewRef.get();
    }
}
