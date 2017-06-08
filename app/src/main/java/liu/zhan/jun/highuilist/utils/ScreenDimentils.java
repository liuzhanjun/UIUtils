package liu.zhan.jun.highuilist.utils;

import android.content.Context;
import android.util.DisplayMetrics;

import java.text.DecimalFormat;

/**
 * Created by 刘展俊 on 2017/3/18.
 */

public enum ScreenDimentils {


    //获得手机屏幕的尺寸，密度和 分辨率

    deminUtils() {
        @Override
        public ScreenDimentils getInstance(Context context) {
            displayMetrics = context.getResources().getDisplayMetrics();
            return deminUtils;
        }
    };;
    DisplayMetrics displayMetrics;

    public abstract ScreenDimentils getInstance(Context context);

    private ScreenDimentils() {

    }

    private final String TAG = "ScreenDimentils";

    //获得屏幕的像素宽度单位px
    public int getScreenWidthPx(){
        return displayMetrics.widthPixels;
    }
    //获得屏幕的像素高度
    public int getScreenHeightPx(){
        return displayMetrics.heightPixels;
    }
    //获得屏幕的宽度单位dp
    public float getScreenWidthDp(){
        return getPxMapDp(displayMetrics.widthPixels);
    }
    //获得屏幕的像素高度
    public float getScreenHeightDp(){
        return getPxMapDp(displayMetrics.heightPixels);
    }


    //获得屏幕的逻辑尺寸
    public float getDimen() {

        //获得分辨率
        int widthpx = displayMetrics.widthPixels;
        int heightpx = displayMetrics.heightPixels;
        //获得dpi
        int densitydip = displayMetrics.densityDpi;
        //获得像素比例
        float density = displayMetrics.density;

        //获得逻辑尺寸
        //计算公式逻辑尺寸=sqrt(w*w+h*h)/屏幕密度dpi
        double zh = Math.sqrt(widthpx * widthpx + heightpx * heightpx);
        float dimen = getTwoDecimal(zh / densitydip);
        return dimen;
    }

    /**
     * 获得dp对应的px
     *
     * @param dp dp
     * @return
     */
    public float getDpMapPx(int dp) {
        //获得
        return dp * displayMetrics.density;
    }

    /**
     * 获得px对应的dp
     *
     * @param px dp
     * @return
     */
    public float getPxMapDp(int px) {
        //获得
        return px / displayMetrics.density;
    }


    public float getTwoDecimal(double decimal) {
        DecimalFormat format = new DecimalFormat("0.00");
        return Float.parseFloat(format.format(decimal));
    }


}
