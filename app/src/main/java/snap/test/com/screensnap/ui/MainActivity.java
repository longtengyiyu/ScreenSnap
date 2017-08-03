package snap.test.com.screensnap.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import snap.test.com.screensnap.R;
import snap.test.com.screensnap.adapter.SnapImgCardAdapter;
import snap.test.com.screensnap.util.FullyLinearLayoutManager;
import snap.test.com.screensnap.util.SnapUtils;
import snap.test.com.screensnap.util.Utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ScrollView scrollView;
    private TextView bottomTv;
    private boolean isFirstAnim;
    private Animation animationOut;
    private boolean isShow;
    private int mDownY;
    private SnapImgCardAdapter snapImgCardAdapter;
    private RecyclerView recyclerView;
    private List<String> imgData = new ArrayList<>();
    private RelativeLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
        setListData();
        setUpViewsData();
    }

    private void setUpViews(){
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        bottomTv = (TextView) findViewById(R.id.snap_bottom_tv);
        recyclerView = (RecyclerView) findViewById(R.id.snap_img_rv);
        snapImgCardAdapter = new SnapImgCardAdapter(this);
        recyclerView.setAdapter(snapImgCardAdapter);
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
        rootView = (RelativeLayout) findViewById(R.id.root_view);
        ViewGroup.LayoutParams layoutParams = rootView.getLayoutParams();
        layoutParams.width = Utils.getWidth(this);
        layoutParams.height = RecyclerView.LayoutParams.WRAP_CONTENT;
        rootView.setLayoutParams(layoutParams);
        bottomTv.setOnClickListener(this);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction( ) ) {
                    case MotionEvent.ACTION_SCROLL:
                    case MotionEvent.ACTION_MOVE:
                        if (isFirstAnim && bottomTv.getVisibility() == View.VISIBLE){
                            isFirstAnim = false;
                            if (animationOut == null){
                                animationOut = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bottom_down);
                            }
                            animationOut.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    bottomTv.setVisibility(View.GONE);
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                            bottomTv.startAnimation(animationOut);
                        }
                        if (Math.abs((int)event.getRawY() - mDownY) > 0){
                            isShow = false;
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
                        mDownY = (int)event.getRawY();
                        isShow = true;
                        isFirstAnim = true;
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        if (isShow && bottomTv.getVisibility() == View.GONE){
                            bottomTv.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.bottom_up));
                            bottomTv.setVisibility(View.VISIBLE);
                        }
                        break;
                }
                return false;
            }
        });
    }

    private void createScreenSnap(){
        WebView webView = new WebView(this);
        webView.loadUrl("http://www.weather.com.cn/weather15d/101020100.shtml");
        webView.setDrawingCacheEnabled(true);
        webView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        webView.layout(0, 0, webView.getMeasuredWidth(), webView.getMeasuredHeight());
        webView.buildDrawingCache(true);
        Bitmap snapBitmap = Bitmap.createBitmap(webView.getWidth(), webView
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(snapBitmap);
        webView.draw(c);
    }

    private void setListData(){
        imgData.add("http://pic20.nipic.com/20120422/9898810_094312238122_2.jpg");
        imgData.add("http://img4.3lian.com/img2005/07/07/107.jpg");
        imgData.add("http://www.imagewa.com/PhotoPreview/230/230_15489.jpg");
        imgData.add("http://pic18.nipic.com/20120109/5222036_100646305154_2.jpg");
        imgData.add("http://pic10.nipic.com/20100902/5289115_165723059486_2.jpg");
        imgData.add("http://tupian.enterdesk.net/2013/0503/20130503050511327.jpg");
        imgData.add("http://pic.nipic.com/2007-11-25/20071125205218466_2.jpg");
    }

    private void setUpViewsData(){
        snapImgCardAdapter.addItems(imgData);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.snap_bottom_tv:
                Bitmap bitmap = SnapUtils.getSnapBitmap(scrollView);  ///获取快照

                break;
        }
    }
}
