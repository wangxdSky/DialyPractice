package com.dong.dialypractice.itemtouchhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dong.pratice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemTouchHelperActivity extends AppCompatActivity {
    @BindView(R.id.tv_item_touch_helper)
    TextView tvItemTouchHelper;
    @BindView(R.id.tv_item_touch_helper2)
    TextView tvItemTouchHelper2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_touch_helper);
        ButterKnife.bind(this);

        tvItemTouchHelper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemTouchHelperActivity.this, ItemTouchTestActivity.class);
                startActivity(intent);
            }
        });

    }
}
