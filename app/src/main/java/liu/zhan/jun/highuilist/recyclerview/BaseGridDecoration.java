package liu.zhan.jun.highuilist.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 最边上有线
 * 如果为Vertaical的时候，适配器的Item布局的父布局必须为充满
 * android:layout_width="match_parent"
 * android:layout_height="wrap_content"
 * <p/>
 * 如果为Horzontal的时候
 * android:layout_width="wrap_content"
 * android:layout_height="match_parent"
 * <p/>
 * 子控件全部充满
 */
public class BaseGridDecoration extends RecyclerView.ItemDecoration {

    private final int[] attr = new int[]{
            android.R.attr.listDivider,
    };
    private Drawable mDivider;
    private Context mContext;
    private int spanCount;


    private int oratation = GridLayoutManager.VERTICAL;
    private RecyclerView.LayoutParams params;

    public BaseGridDecoration(Context context, int spanCount) {
        mContext = context;
        this.spanCount = spanCount;
        TypedArray typeArray = context.obtainStyledAttributes(attr);
        mDivider = typeArray.getDrawable(0);
        typeArray.recycle();
    }

    public void setOratation(int oratation) {
        if (oratation != GridLayoutManager.VERTICAL && oratation != GridLayoutManager.HORIZONTAL) {
            throw new IllegalArgumentException("设置错误的oratation");
        }
        this.oratation = oratation;
    }

    public void setmDivider(int drawalbeRes) {
        mDivider = mContext.getResources().getDrawable(drawalbeRes);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int itemPosition = parent.getChildAdapterPosition(view);
        int w = mDivider.getIntrinsicWidth();
        int h = mDivider.getIntrinsicHeight();
        if (oratation == GridLayoutManager.VERTICAL) {
            setVerticalOffsets(outRect, itemPosition, w, h);
        } else if (oratation == GridLayoutManager.HORIZONTAL) {
            setHorizontalOffsets(outRect, itemPosition, w, h);
        }
    }


    /**
     * 设置横向的offset
     *
     * @param outRect
     * @param itemPosition
     * @param w
     * @param h
     */
    private void setHorizontalOffsets(Rect outRect, int itemPosition, int w, int h) {

        if (itemPosition % spanCount == 0) {
            if (itemPosition == 0) {
                outRect.set(w, h, w, h);
            } else {
                outRect.set(0, h, w, h);
            }
        } else {
            if (itemPosition > spanCount) {
                outRect.set(0, 0, w, h);
            } else {
                outRect.set(w, 0, w, h);
            }
        }
    }

    /**
     * 设置纵向的offset
     *
     * @param outRect
     * @param itemPosition
     * @param w
     * @param h
     */
    private void setVerticalOffsets(Rect outRect, int itemPosition, int w, int h) {
        if (itemPosition % spanCount == 0) {
            if (itemPosition == 0) {
                outRect.set(w, h, w, h);
            } else {
                outRect.set(w, 0, w, h);
            }
        } else {
            if (itemPosition > spanCount) {
                outRect.set(0, 0, w, h);
            } else {
                outRect.set(0, h, w, h);
            }
        }
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

//        if (oratation == GridLayoutManager.VERTICAL) {
        DrawVertical(c, parent, state);
//        }else if(oratation == GridLayoutManager.HORIZONTAL){
//            DrawHorizontal(c, parent,state);
//        }


        super.onDraw(c, parent, state);
    }

    /**
     * 横向时绘制
     *
     * @param c
     * @param parent
     * @param state
     */
    private void DrawHorizontal(Canvas c, RecyclerView parent, RecyclerView.State state) {

    }

    /**
     * 纵向时绘制
     *
     * @param c
     * @param parent
     * @param state
     */
    private void DrawVertical(Canvas c, RecyclerView parent, RecyclerView.State state) {

        View view = parent.getChildAt(0);
        //画左上
        params = (RecyclerView.LayoutParams) view.getLayoutParams();
        drawStartLeft(parent, view, c);
        drawStartTop(parent, view, c);
        //画右边和下边
        drawRight(parent, c, params);
        drawBottom(parent, c, params);

    }

    private void drawBottom(RecyclerView parent, Canvas c, RecyclerView.LayoutParams params) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            View view = parent.getChildAt(i);
            int left = view.getLeft() + params.leftMargin;
            int right = left + view.getWidth() + params.rightMargin + mDivider.getIntrinsicWidth();
            int top = view.getBottom() - params.bottomMargin + Math.round(ViewCompat.getTranslationY(view));
            int bottom = top + mDivider.getIntrinsicHeight() + params.topMargin + Math.round(ViewCompat.getTranslationY(view));
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private void drawRight(RecyclerView parent, Canvas c, RecyclerView.LayoutParams params) {


        for (int i = 0; i < parent.getChildCount(); i++) {
            View view = parent.getChildAt(i);
            int left = view.getRight() + params.rightMargin;
            int right = left + mDivider.getIntrinsicWidth() + params.leftMargin;
            int top = view.getTop() - params.topMargin + Math.round(ViewCompat.getTranslationY(view));
            int bottom = top + view.getHeight() + params.bottomMargin + Math.round(ViewCompat.getTranslationY(view));
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }

    }


    /**
     * 初始画最上边的
     *
     * @param parent
     * @param view
     * @param c
     */
    private void drawStartTop(RecyclerView parent, View view, Canvas c) {
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        int left = parent.getPaddingLeft() + mDivider.getIntrinsicWidth();
        int right = parent.getWidth() - parent.getPaddingRight()+mDivider.getIntrinsicWidth();
        int top = parent.getPaddingTop() + Math.round(ViewCompat.getTranslationY(view));
        int bottom = mDivider.getIntrinsicHeight() + params.topMargin;
        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(c);
    }

    /**
     * 初始画最左边的
     *
     * @param parent
     * @param view
     * @param c
     */
    private void drawStartLeft(RecyclerView parent, View view, Canvas c) {
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        int left = parent.getPaddingLeft();
        int right = mDivider.getIntrinsicWidth() + params.leftMargin;
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight();
        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(c);
    }
}
