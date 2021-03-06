package refresh;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

/**
 * RefreshHeader
 *
 * @author wangxd
 * @data 2019/11/30 @time 14:42
 */
public interface RefreshHeader {

    /**
     * @param dragPosition HorizontalRefreshLayout.START or HorizontalRefreshLayout.END
     */
    void onStart(int dragPosition, View refreshHead);

    /**
     * @param distance
     */
    void onDragging(float distance, float percent, View refreshHead);

    void onReadyToRelease(View refreshHead);

    @NonNull
    View getView(ViewGroup container);

    void onRefreshing(View refreshHead);
}
