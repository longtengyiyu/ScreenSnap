package snap.test.com.screensnap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
    }

    private void setUpViews(){
        iv = (ImageView) findViewById(R.id.snap_iv);
        findViewById(R.id.snap_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createScreenSnap();
            }
        });
    }

    private void createScreenSnap(){
        ScreenSnapView ssv = new ScreenSnapView(MainActivity.this);
        ssv.setDrawingCacheEnabled(true);
        ssv.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        ssv.layout(0, 0, ssv.getMeasuredWidth(), ssv.getMeasuredHeight());
        ssv.buildDrawingCache(true);
        Bitmap snapBitmap = Bitmap.createBitmap(ssv.getWidth(), ssv.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(snapBitmap);
        ssv.draw(c);
        iv.setImageBitmap(snapBitmap);
    }
}
