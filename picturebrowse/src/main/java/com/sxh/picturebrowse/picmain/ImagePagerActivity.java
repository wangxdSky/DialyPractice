package com.sxh.picturebrowse.picmain;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.liulishuo.filedownloader.FileDownloader;
import com.sxh.picturebrowse.R;
import com.sxh.picturebrowse.picutils.AllUtils;
import com.sxh.picturebrowse.picutils.ToastAlert;
import com.sxh.picturebrowse.viewdialog.PictureSpinView;

import java.util.ArrayList;

import refresh.HorizontalRefreshLayout;
import refresh.RefreshCallBack;
import refresh.refreshhead.LoadingRefreshHeader;

/**
 * by sxh pictureBrowse
 */
public class ImagePagerActivity extends AppCompatActivity implements ImageDetailFragment.OnLoadListener, RefreshCallBack {
    private ArrayList<String> mViewPositions = new ArrayList<>();
    private ArrayList<String> mImgs = new ArrayList<>();
    private ImagePagerAdapter mAdapter;
    private PictureSpinView mProgress;
    private TextView mIndicatorText;
    private TextView mFailText;
    private ViewPager mPager;
    private int mPagerPosition;
    private TextView tvBg;
    private boolean flag = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将window扩展至全屏，并且不会覆盖状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //避免在状态栏的显示状态发生变化时重新布局
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        //初始化文件下载，toast
        initApplication();

        setContentView(R.layout.image_detail_page);
        tvBg = findViewById(R.id.tv_bg);

        mPagerPosition = getIntent().getIntExtra("image_index", 0);
        mViewPositions = getIntent().getStringArrayListExtra("positions");
        if (savedInstanceState != null) {
            mPagerPosition = savedInstanceState.getInt("STATE_POSITION", 0);
        }
        initView(savedInstanceState);
    }

    public void initApplication() {
        FileDownloader.setupOnApplicationOnCreate(getApplication());
        ToastAlert.init(getApplicationContext());
    }


    private void initView(Bundle savedInstanceState) {
        mImgs = getIntent().getStringArrayListExtra("image_urls");
        mPager = (ViewPager) findViewById(R.id.pager);
        mIndicatorText = (TextView) findViewById(R.id.indicator);
        mProgress = (PictureSpinView) findViewById(R.id.loadingIcon);
        mFailText = (TextView) findViewById(R.id.fail_text);
        HorizontalRefreshLayout horizontalScrollView = (HorizontalRefreshLayout) findViewById(R.id.refrshe);

        horizontalScrollView.setRefreshCallback(this);
        horizontalScrollView.setRefreshHeader(new LoadingRefreshHeader(this, new LoadingRefreshHeader.OnPercentListener() {
            @Override
            public void onPercent(float percent) {
            }
        }), HorizontalRefreshLayout.RIGHT);

        mPager.setOffscreenPageLimit(mImgs.size());
        mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), mImgs, mPager, mViewPositions, mPagerPosition);
        CharSequence text = getString(R.string.viewpager_indicator, 1, mPager.getAdapter().getCount());
        mIndicatorText.setText(text);
        // 更新下标
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int arg0) {
                if (AllUtils.getBackListeners() != null) {
                    AllUtils.getBackListeners().backMethod(arg0);
                }
                ImageDetailFragment fragment = (ImageDetailFragment) mAdapter.getItem(mPager.getCurrentItem());
                fragment.updateFrom(mPager.getCurrentItem());
                fragment.setViewPager(mPager);
                CharSequence text = getString(R.string.viewpager_indicator,
                        mPager.getCurrentItem() + 1, mPager.getAdapter().getCount());
                mIndicatorText.setText(text);
            }

        });
        mPager.setCurrentItem(mPagerPosition);

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("STATE_POSITION", mPager.getCurrentItem());
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onBackPressed() {
        ImageDetailFragment fragment = (ImageDetailFragment) mAdapter.getItem(mPager.getCurrentItem());
        fragment.onBackPressed();
    }

    @Override
    public void onLoadStart() {
        if (mProgress != null) {
            mProgress.setVisibility(View.VISIBLE);
        }
        if (mFailText != null) {
            mFailText.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoadSuccess() {
        if (mProgress != null) {
            mProgress.setVisibility(View.GONE);
        }
        if (mFailText != null) {
            mFailText.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoadFailed() {
        if (mProgress != null) {
            mProgress.setVisibility(View.GONE);
        }
        if (mFailText != null) {
            mFailText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onScroolDistance(int distance) {
        int abs = Math.abs(distance);
        int alpha = 0;
        if (abs < 255) {
            alpha = 255 - abs;
        } else {
            alpha = 0;
            flag = true;
        }

        if (flag) return;
        tvBg.getBackground().setAlpha(alpha);
        Log.e("ImagePagerActivity", "滚动了多少" + distance + "abs / 200" + abs / 200 + "setAlpha" + (1 - abs / 200));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.get(this).clearMemory();
    }

    @Override
    public void onLeftRefreshing() {

    }

    @Override
    public void onRightRefreshing() {

    }

    @Override
    public void onRefreshRelease() {

    }
}
