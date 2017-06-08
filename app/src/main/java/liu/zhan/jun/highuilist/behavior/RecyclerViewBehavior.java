package liu.zhan.jun.highuilist.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by 刘展俊 on 2017/6/8.
 */

/**
 * 作用是实现控件联动效果
 * 1.某个view监听另外一个view的状态（比如位置，大小，显示状态）
 *      使用方式需要重写layoutDependOn和onDependViewChange
 * 2.某个view要监听coordinateLayout里面控件的滑动状态
 *      使用方式需要重写onStartNestedScroll，onNestedScroll或者onNestedPreScroll
 *      能被监听到的只有recyclerView,nestendScroview,viewPageer
 *
 *  然后在布局中使用CoordinatorLayout将要监听的控件作为子控件
 *  其中一个子控件是被监听的，另外是监听者
 *  在监听者控件加入
 *  app:layout_behavior="liu.zhan.jun.highuilist.behavior.RecyclerViewBehavior"
 */
public class RecyclerViewBehavior extends CoordinatorLayout.Behavior {

    private static final String TAG = "LOGI";

    public RecyclerViewBehavior(Context context, AttributeSet attrs) {
        super();
    }

    /**
     * 指定监听的view
     *
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        //例如
        //可以通过tag或者id来区分
        return dependency instanceof RecyclerView||super.layoutDependsOn(parent, child, dependency);
    }

    /**
     * 当监听的view发生改变是调用
     * 可以在此方法内做出一些响应
     *
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        //依赖控件状态改变 时回调
        Log.i(TAG, "onDependentViewChanged: ==========");
        return true;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        //需要指定监听的方向
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        Log.i(TAG, "onNestedScroll: dy===" + dyConsumed);
    }


}
