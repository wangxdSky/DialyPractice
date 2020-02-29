package com.dong.dialypractice.itemtouchhelper;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dong.dialypractice.modle.QQMessage;
import com.dong.dialypractice.utils.DataUtils;
import com.dong.pratice.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemTouchTestActivity extends AppCompatActivity implements StartDragListener {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_touch_test);
        ButterKnife.bind(this);

        List<QQMessage> list = DataUtils.init();

        //recyclerview使用
        MessageAdapter adapter = new MessageAdapter(list, this);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);


        ItemTouchHelper.Callback callback = new MessageItemTouchCallback(adapter);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerview);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }
}
