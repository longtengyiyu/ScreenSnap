package snap.test.com.screensnap.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Description:
 * Author:    Oscar
 * Version    V1.0
 * Date:      2017/8/3
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/8/3         Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class Utils {
    public static int getWidth(Context ctx) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (ctx instanceof Activity) {
            ((Activity) ctx).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.widthPixels;
        } else {
            return ctx.getResources().getDisplayMetrics().widthPixels;
        }
    }
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
