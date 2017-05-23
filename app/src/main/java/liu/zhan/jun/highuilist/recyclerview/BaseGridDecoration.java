package liu.zhan.jun.highuilist.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    public static final String TAG = "LOGI";
    private final int[] attr = new int[]{
            android.R.attr.listDivider,
    };
    private Drawable mDivider;
    private Drawable mDivider2;
    private Context mContext;
    private int spanCount;
    private int interval = 15;


    private int oratation = GridLayoutManager.VERTICAL;

    public BaseGridDecoration(Context context, int spanCount) {
        mContext = context;
        this.spanCount = spanCount;
        TypedArray typeArray = context.obtainStyledAttributes(attr);
        mDivider = typeArray.getDrawable(0);
        mDivider2 = typeArray.getDrawable(0);
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
        mDivider2 = mContext.getResources().getDrawable(drawalbeRes);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int itemPosition = parent.getChildAdapterPosition(view);
        if (oratation == GridLayoutManager.VERTICAL) {
            setVerticalOffsets(outRect, itemPosition, interval);
        } else if (oratation == GridLayoutManager.HORIZONTAL) {
            setHorizontalOffsets(outRect, itemPosition, interval);
        }
    }


    /**
     * 设置横向的offset
     *
     * @param outRect
     * @param itemPosition
     */
    private void setHorizontalOffsets(Rect outRect, int itemPosition, int interval) {

        if (itemPosition % spanCount == 0) {
            if (itemPosition == 0) {
                outRect.set(interval, interval, interval, interval);
            } else {
                outRect.set(0, interval, interval, interval);
            }
        } else {
            if (itemPosition > spanCount) {
                outRect.set(0, 0, interval, interval);
            } else {
                outRect.set(interval, 0, interval, interval);
            }
        }
    }

    /**
     * 设置纵向的offset
     *
     * @param outRect
     * @param itemPosition
     */
    private void setVerticalOffsets(Rect outRect, int itemPosition, int interval) {
        if (itemPosition == 0) {
            //第一个的上下左右都有
            outRect.set(interval, interval, interval, interval);
        } else {
            if (itemPosition % spanCount == 0) {
                //这是第一列
                //只有左右下
                outRect.set(interval, 0, interval, interval);
            } else {
                if (itemPosition < spanCount) {
                    outRect.set(0, interval, interval, interval);
                } else {
                    //只有右和下
                    outRect.set(0, 0, interval, interval);
                }
            }
        }

//        if (itemPosition % spanCount == 0) {
//            if (itemPosition == 0) {
//                outRect.set(w, h, w, h);
//            } else {
//                outRect.set(w, 0, w, h);
//            }
//        } else {
//            if (itemPosition > spanCount) {
//                outRect.set(0, 0, w, h);
//            } else {
//                outRect.set(0, h, w, h);
//            }
//        }
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


        //画整个的左边
        drawStartLeft(parent, c);
        //画整个的上边
        drawStartTop(parent, c);


        //画里面元素右边和下边
        drawRight(parent, c);
        drawBottom(parent, c);


    }


    /**
     * 所有的元素下面的线
     *
     * @param parent
     * @param c
     */
    private void drawBottom(RecyclerView parent, Canvas c) {
        int endRight = 0;//表示和最后一个的右边对齐的上一层的那个
        for (int i = 1; i < parent.getChildCount(); i++) {
            View view = parent.getChildAt(i - 1);
            int left = view.getLeft();// + params.leftMargin;
            //下一个的左边
            int right = parent.getChildAt(i).getLeft();
            int top = view.getBottom();// - params.bottomMargin + Math.round(ViewCompat.getTranslationY(view));
            int bottom = top + interval;// + params.topMargin + Math.round(ViewCompat.getTranslationY(view));


            if (i % spanCount == 0) {
                //如果最后一个刚好是第一列的最后一个
                if (i == parent.getChildCount() - 1) {
                    int end=i-(spanCount-1);
                    View v = parent.getChildAt(i);
                    mDivider2.setBounds(v.getLeft(),v.getBottom(),parent.getChildAt(end).getLeft()-interval,v.getBottom()+interval);
                    mDivider2.draw(c);
                }

                endRight = i;
                mDivider.setBounds(view.getLeft() - interval, view.getBottom(), parent.getRight() - interval, view.getBottom() + interval);
            } else if (i < parent.getChildCount() - 1) {
                mDivider.setBounds(left, top, right, bottom);
            } else {
                //最后一个
                mDivider.setBounds(left, top, right, bottom);
                int op = (spanCount - 1 - (i % spanCount));
                View view1 = parent.getChildAt(i);
                if (op == 0) {
                    ////这最后的那个元素就是最右边的那列的最后一个
                    mDivider2.setBounds(view1.getLeft(), view1.getBottom(), parent.getRight() - interval, parent.getBottom());
                } else {
                    endRight = endRight - (spanCount - 1 - (i % spanCount));

                    mDivider2.setBounds(view1.getLeft(), view1.getBottom(), parent.getChildAt(endRight).getLeft(), view1.getBottom() + interval);

                }
                mDivider2.draw(c);
            }
            mDivider.draw(c);
        }
    }

    private void drawRight(RecyclerView parent, Canvas c) {

        //从第2个元素开始根据后面一个元素的left-interval,即当前元素的右边
        int rightEndIndex = 0;
        for (int i = 1; i < parent.getChildCount(); i++) {
            View view = parent.getChildAt(i);
            view.setBackgroundColor(Color.parseColor("#FF0000"));
            int left = view.getLeft() - interval;
            int right = view.getLeft();//+ params.leftMargin;
            int top = view.getTop();// - params.topMargin + Math.round(ViewCompat.getTranslationY(view));
            int bottom = view.getBottom();//+ params.bottomMargin + Math.round(ViewCompat.getTranslationY(view));

            if (i % spanCount == 0) {
                //如果最后一个刚好是第一列的最后一个
                if (i == parent.getChildCount() - 1) {

                    int end=i-(spanCount-1);
                    Log.i(TAG, "drawRight: i="+i+"====end="+end);
                    View v = parent.getChildAt(end);
                    mDivider2.setBounds(v.getLeft()-interval,v.getBottom()+interval,v.getLeft(),view.getBottom()+interval);
                    mDivider2.draw(c);
                }
                rightEndIndex = i;
                mDivider.setBounds(parent.getRight() - interval, parent.getChildAt(i - 1).getTop(), parent.getRight(), parent.getChildAt(i - 1).getBottom() + interval);

            } else if (i < parent.getChildCount() - 1) {
                mDivider.setBounds(left, top, right, bottom);
            } else {
                Log.i("LOGI", "drawRight: i=" + i);
                Log.i(TAG, "drawRight: (i%spanCount)" + (i % spanCount));
                int op = (spanCount - 1 - (i % spanCount));
                mDivider.setBounds(left, top, right, bottom);
                if (op == 0) {
                    //这最后的那个元素就是最右边的那列的最后一个
                    mDivider2.setBounds(parent.getRight() - interval, top, parent.getRight(), bottom + interval);
                } else {
                    rightEndIndex = rightEndIndex - op;
                    Log.i(TAG, "drawRight: rightEndIndex=" + rightEndIndex);
                    mDivider2.setBounds(parent.getChildAt(rightEndIndex).getLeft() - interval, top, parent.getChildAt(rightEndIndex).getLeft(), bottom);

                }
                mDivider2.draw(c);
            }

            mDivider.draw(c);
        }

    }


    /**
     * 初始画最上边的
     *
     * @param parent
     * @param c
     */
    private void drawStartTop(RecyclerView parent, Canvas c) {
        int left = parent.getPaddingLeft() + interval;
        int right = parent.getWidth() - parent.getPaddingRight() + interval;
        int top = parent.getPaddingTop();//+ Math.round(ViewCompat.getTranslationY(view));
        int bottom = interval;// + params.topMargin;
        //上
        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(c);

    }

    /**
     * 初始画最左边的
     *
     * @param parent
     * @param c
     */
    private void drawStartLeft(RecyclerView parent, Canvas c) {
        int left = parent.getPaddingLeft();
        int right = interval;// + params.leftMargin;
        int top = parent.getPaddingTop();
        int bottom = parent.getBottom();
        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(c);
    }
}
