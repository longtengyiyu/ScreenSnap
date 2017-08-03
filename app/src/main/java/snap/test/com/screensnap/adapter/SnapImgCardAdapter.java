package snap.test.com.screensnap.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import snap.test.com.screensnap.R;
import snap.test.com.screensnap.util.Utils;

import static com.bumptech.glide.request.target.Target.SIZE_ORIGINAL;

/**
 * Description:
 * Author:    Oscar
 * Version    V1.0
 * Date:      2017/3/18
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/3/18         Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class SnapImgCardAdapter extends BaseRecyclerViewAdapter<String> {

    public SnapImgCardAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_snap_img, parent, false), this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            Glide.with(mContext).load(mDataList.get(position)).into( ((ViewHolder) holder).snapImg);
        }
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView snapImg;
        private View snapView;

        public ViewHolder(View itemView, SnapImgCardAdapter adapter) {
            super(itemView);
            snapImg = (ImageView) itemView.findViewById(R.id.item_snap_iv);
            snapView = itemView.findViewById(R.id.item_snap_v);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) snapImg.getLayoutParams();
            int w = Utils.getWidth(adapter.mContext) - Utils.dip2px(adapter.mContext, 32);
            params.width = w;
            params.height = (int)(0.66 * w);
            snapImg.setLayoutParams(params);
        }
    }

}
