package liu.zhan.jun.highuilist.recyclerview;

import android.view.View;

/**
 * Created by 刘展俊 on 2016/6/7.
 */
public interface OnRecyclerItemClick {
    public void onClick(View veiw, int position);
    public void LongPress(View veiw, int position);
}
