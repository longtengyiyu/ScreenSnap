package snap.test.com.screensnap.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

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
public class SnapUtils {

    public static Bitmap getSnapBitmap(View v){
        v.setDrawingCacheEnabled(true);
        v.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec
                .UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View
                .MeasureSpec.UNSPECIFIED));
        v.layout(0, 0, v.getMeasuredWidth(), v
                .getMeasuredHeight());
        v.buildDrawingCache(true);
        Bitmap snapBitmap = Bitmap.createBitmap(v.getWidth(), v
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(snapBitmap);
        v.draw(c);
        return  snapBitmap;
    }
}
