package refresh.refreshhead;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import horizontal.refresh.refreshLayout.R;
import refresh.RefreshHeader;

/**
 * LoadingRefreshHeader
 * 黑色背景
 * 白色字体
 *
 * @author wangxd
 * @data 2019/11/30 @time 14:40
 */
public class LoadingRefreshHeader implements RefreshHeader {
    private final Context context;
    private OnPercentListener onPercentListener;
    private LinearLayout ll_more;
    private ImageView ivIcon;
    private TextView tvDes;

    public LoadingRefreshHeader(Context context, OnPercentListener onPercentListener) {
        this.context = context;
        this.onPercentListener = onPercentListener;
    }

    @NonNull
    @Override
    public View getView(ViewGroup container) {
        View view = LayoutInflater.from(context).inflate(R.layout.common_loading_refresh_header, container, false);
        ll_more = (LinearLayout) view.findViewById(R.id.ll_more);
        ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
        tvDes = (TextView) view.findViewById(R.id.tv_des);
        ll_more.setVisibility(View.INVISIBLE);
        return view;
    }

    @Override
    public void onStart(int dragPosition, View refreshHead) {
        ivIcon.setVisibility(View.VISIBLE);
        tvDes.setVisibility(View.VISIBLE);
        ll_more.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDragging(float distance, float percent, View refreshHead) {
        if (onPercentListener != null) {
            onPercentListener.onPercent(percent);
        }

        ll_more.setVisibility(View.VISIBLE);
        if (percent > 1) {
            ivIcon.setRotation(180);
            tvDes.setText("释放查看全部内容");
        } else {
            ivIcon.setRotation(360);
            tvDes.setText("滑动查看全部内容");
        }
    }

    @Override
    public void onReadyToRelease(View refreshHead) {

    }

    @Override
    public void onRefreshing(View refreshHead) {
        ivIcon.setVisibility(View.VISIBLE);
        tvDes.setVisibility(View.VISIBLE);
        ll_more.setVisibility(View.VISIBLE);
    }

    public interface OnPercentListener {

        void onPercent(float percent);

    }
}
