package liu.zhan.jun.highuilist.uiActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import liu.zhan.jun.highuilist.R;

public class AppBarLayoutActivity extends AppCompatActivity {

    private Toolbar head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_layout);
        head= (Toolbar) findViewById(R.id.headbar);
        setSupportActionBar(head);
    }
}
