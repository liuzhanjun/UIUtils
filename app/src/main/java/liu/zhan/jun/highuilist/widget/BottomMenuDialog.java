package liu.zhan.jun.highuilist.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 底部菜单 采用 BottomSheetDialog
 * Created by Gordn on 2017/3/23. */

public class BottomMenuDialog extends BottomSheetDialogFragment implements View.OnClickListener {
    public static final String TAG = "BOTTOM_MENU";

    //必要参数 标题集合
    private ArrayList<String> titles;
    //必要参数 监听器集合
    private Map<String, View.OnClickListener> listeners;
    //菜单文字颜色
    private int textColor = Color.parseColor("#3BCF67");

    private LinearLayout rootView;
    protected Dialog dialog;
    private Context mContext;

    protected BottomSheetBehavior mBehavior;

    public BottomMenuDialog() {
    }

    @SuppressLint("ValidFragment")
    public BottomMenuDialog(BottomMenuBuilder builder) {
        titles = builder.titles;
        listeners = builder.listeners;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        //这里设置透明度
        windowParams.dimAmount = 0.5f;

        window.setAttributes(windowParams);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解除缓存 View 和当前 ViewGroup 的关联
        ((ViewGroup) (rootView.getParent())).removeView(rootView);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new BottomSheetDialog(getContext(), getTheme());
        if (rootView == null) {
            //缓存下来的 View 当为空时才需要初始化 并缓存
            initRootView();
        }
        dialog.setContentView(rootView);
        mBehavior = BottomSheetBehavior.from((View) rootView.getParent());
        ((View) rootView.getParent()).setBackgroundColor(Color.TRANSPARENT);
        rootView.post(new Runnable() {
            @Override
            public void run() {
                /**
                 * PeekHeight 默认高度 256dp 会在该高度上悬浮
                 * 设置等于 view 的高 就不会卡住
                 */
                mBehavior.setPeekHeight(rootView.getHeight());
            }
        });

        return dialog;
    }

    private void initRootView() {
        rootView = new LinearLayout(mContext);
        rootView.setOrientation(LinearLayout.VERTICAL);
        rootView.setPadding(dp2px(mContext, 10), 0, dp2px(mContext, 10), dp2px(mContext, 10));

        if (titles == null || titles.size() == 0) {
            return;
        }
        if (titles.size() == 1) {
            View childView = initView(titles.get(0), 1, false, false);
            childView.setBackgroundDrawable(getDrawableListByType(true, true, true, true));
            return;
        }

        if (titles.size() == 2) {
            View childView = initView(titles.get(0), 1, false, true);
            childView.setBackgroundDrawable(getDrawableListByType(true, true, true, true));
            childView = initView(titles.get(1), 2, false, false);
            childView.setBackgroundDrawable(getDrawableListByType(true, true, true, true));
            return;
        }

        for (int i = 0; i < titles.size(); i++) {
            View childView = null;
            if (i == 0) {
                childView = initView(titles.get(0), i + 1, true, false);
                childView.setBackgroundDrawable(getDrawableListByType(true, true, false, false));
                continue;
            }
            if (i == (titles.size() - 2)) {
                childView = initView(titles.get(i), i + 1, false, true);
                childView.setBackgroundDrawable(getDrawableListByType(false, false, true, true));
                continue;
            }
            if (i == (titles.size() - 1)) {
                //false, false, true, true
                childView = initView(titles.get(i), i + 1, false, false);
                childView.setBackgroundDrawable(getDrawableListByType(true, true, true, true));
                continue;
            }

            childView = initView(titles.get(i), i + 1, true, false);
            childView.setBackgroundDrawable(getDrawableListByType(false, false, false, false));
        }

    }

    private View initView(String button, int position, boolean hasBottomLine, boolean hasBottomGap) {
        TextView childView = new TextView(mContext);
        childView.setText(button);
        childView.setTextSize(18);
        childView.setTextColor(textColor);
        childView.setTag(position);
        childView.setGravity(Gravity.CENTER);
        childView.setOnClickListener(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dp2px(mContext, 50));
        if (hasBottomGap) params.bottomMargin = dp2px(mContext, 10);
        rootView.addView(childView, params);

        if (hasBottomLine) {
            View line = new View(mContext);
            line.setBackgroundColor(Color.LTGRAY);
            rootView.addView(line, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        }

        return childView;
    }


    public void setTextColor(int color) {
        this.textColor = color;
    }

    public StateListDrawable getDrawableListByType(boolean leftTop, boolean rightTop, boolean rightBottom, boolean leftBottom) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable selectDrawable = getWhitShape(5, 0xffCCCCCC, leftTop, rightTop, rightBottom, leftBottom);
        Drawable defaultDrawable = getWhitShape(5, 0xffffffff, leftTop, rightTop, rightBottom, leftBottom);

        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, selectDrawable);
        stateListDrawable.addState(new int[]{}, defaultDrawable);
        return stateListDrawable;
    }

    public Drawable getWhitShape(int radius, int bgColor, boolean leftTop, boolean rightTop, boolean rightBottom, boolean leftBottom) {
        float r = dp2px(getContext(), radius);
        float a1 = 0, a2 = 0, a3 = 0, a4 = 0, a5 = 0, a6 = 0, a7 = 0, a8 = 0;
        if (leftTop) {
            a1 = r;
            a2 = r;
        }
        if (rightTop) {
            a3 = r;
            a4 = r;
        }

        if (rightBottom) {
            a5 = r;
            a6 = r;
        }

        if (leftBottom) {
            a7 = r;
            a8 = r;
        }

        float[] outerRadii = new float[]{a1, a2, a3, a4, a5, a6, a7, a8};
        RoundRectShape rrs = new RoundRectShape(outerRadii, null, null);
        ShapeDrawable sd = new ShapeDrawable(rrs);
        sd.getPaint().setColor(bgColor);
        return sd;
    }

    public void show(FragmentManager manager) {
        if (!this.isAdded())
            show(manager, TAG);
    }

    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    @Override
    public void onClick(View v) {
        Object o = v.getTag();
        if (o == null) {
            return;
        }
        String key = String.valueOf(o);
        if (listeners.get(key) != null) {
            listeners.get(key).onClick(v);
        }
        dismiss();
    }

    public static class BottomMenuBuilder {
        //必要参数 标题集合
        private ArrayList<String> titles;
        //必要参数 监听器集合
        private Map<String, View.OnClickListener> listeners;

        public BottomMenuBuilder() {
            titles = new ArrayList<>();
            listeners = new HashMap<>();
        }

        public BottomMenuDialog build() {
            if (titles == null || titles.isEmpty()) {

                Log.e(TAG, "can not empty titles");
            }

            return new BottomMenuDialog(this);
        }


        public BottomMenuBuilder addItem(String title, View.OnClickListener listener) {
            titles.add(title);
            listeners.put(String.valueOf(titles.size()), listener);
            return this;
        }
    }

}