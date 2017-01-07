package awesome.deadlineplanner;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<String> mDatas;
    private Cards mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 在此处添加数据库中的DDL项目 mData

        final TextView textKeepDragging = (TextView) findViewById(R.id.text_keep_dragging);
        final SwipeLayout swipe = (SwipeLayout) findViewById(R.id.layout_main_swipe);
        swipe.addSwipeListener(new SwipeLayout.SwipeListener() {
            private int topOffset = 0;
            final private int triggerOffset = 250;

            @Override
            public void onStartOpen(SwipeLayout layout) {
            }

            @Override
            public void onOpen(SwipeLayout layout) {
                layout.close();
            }

            @Override
            public void onStartClose(SwipeLayout layout) {
            }

            @Override
            public void onClose(SwipeLayout layout) {
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                if (topOffset < triggerOffset) textKeepDragging.setText("继续拉动创建死线\n⬇️");
                else textKeepDragging.setText("松手创建死线\n⬇️");
                this.topOffset = topOffset;
            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                if (topOffset >= triggerOffset) {
                    startActivity(new Intent(MainActivity.this, CreateDeadlineActivity.class));
                    overridePendingTransition(R.animator.fade_in, R.animator.fade_out);
                }
            }
        });

        // 拿到 RecyclerView对象
        mRecyclerView = (RecyclerView) findViewById(R.id.res_recycler_items);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 设置adapter适配器、点击监听
        mAdapter = new Cards(mDatas, MainActivity.this);
        mAdapter.add(new DeadlineEvent("操作系统", 2018, 3, 12, 23, 59));
        mRecyclerView.setAdapter(mAdapter);
    }


}