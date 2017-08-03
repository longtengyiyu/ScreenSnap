package snap.test.com.screensnap.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import snap.test.com.screensnap.R;

/**
 * Description:屏幕快照
 * Author:    Oscar
 * Version    V1.0
 * Date:      2017/5/3
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/5/3         Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class ScreenSnapView extends LinearLayout {
    public ScreenSnapView(Context context) {
        this(context, null);
    }

    public ScreenSnapView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScreenSnapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.item_screen_snap, this, true);
    }
}
