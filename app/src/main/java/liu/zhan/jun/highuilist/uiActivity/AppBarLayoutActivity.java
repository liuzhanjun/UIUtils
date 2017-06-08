package liu.zhan.jun.highuilist.uiActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.support.design.widget.CheckableImageButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import liu.zhan.jun.highuilist.R;

public class AppBarLayoutActivity extends AppCompatActivity {

    private Toolbar head;
    private CheckableImageButton cbx;
    private ValueAnimator valueAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_layout);
        head= (Toolbar) findViewById(R.id.headbar);
        setSupportActionBar(head);
        cbx= (CheckableImageButton) findViewById(R.id.cbx);
        		/**
				 * 平抛运动
		 * x:匀速
		 * y:加速度   y=1/2*g*t*t
		 * 使用估值器最好实现。
		 */
		valueAnimator = new ValueAnimator();
		valueAnimator.setDuration(4000);
		valueAnimator.setObjectValues(new PointF(0, 0),new PointF(500,500));
		//估值器---定义计算规则
		valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {

			@Override
			public PointF evaluate(float fraction, PointF startValue,
					PointF endValue) {
				//拿到每一个时间点的坐标
				//x=v*t (s秒)
				PointF pointF = new PointF();
				pointF.x = 100f*(fraction*4);//初始速度*(执行的百分比*4)
//				pointF.y = 0.5f*9.8f*(fraction*4)*(fraction*4);
				pointF.y = 0.5f*150f*(fraction*4)*(fraction*4);
				return pointF;
			}
		});

		valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				//得到此时间点的坐标
				PointF pointF = (PointF) animation.getAnimatedValue();
				Log.i("LOGI", "onAnimationUpdate: "+pointF.x);
				cbx.setX(pointF.x);
				cbx.setY(pointF.y);
			}
		});
    }

    public void startAnimator(View view){
        valueAnimator.start();
    }
}
