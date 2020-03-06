package refresh;

/**
 * RefreshCallBack
 *
 * @author wangxd
 * @data 2019/11/30 @time 14:41
 */
public interface RefreshCallBack {

    void onLeftRefreshing();

    void onRightRefreshing();

    void onRefreshRelease();
}
