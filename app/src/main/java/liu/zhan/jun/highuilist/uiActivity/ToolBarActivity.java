package liu.zhan.jun.highuilist.uiActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import liu.zhan.jun.highuilist.R;

public class ToolBarActivity extends AppCompatActivity {

    private static final String TAG = "LOGI";
    private Toolbar toolbar;
    private ImageView img;
    private RoundedBitmapDrawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: " + "返回");
            }
        });

        img = (ImageView) findViewById(R.id.circle_bitmap);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.square);
        drawable = RoundedBitmapDrawableFactory.create(this.getResources(), bitmap);


    }

    public void settingCircle(View view) {
        drawable.setCircular(true);//设置成圆形
        img.setImageDrawable(drawable);
    }

    public void setting20(View view) {
        drawable.setCircular(false);
        drawable.setCornerRadius(20);//设置圆角的半径
        img.setImageDrawable(drawable);

    }


}
