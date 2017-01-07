package awesome.deadlineplanner;

/**
 * Created by chumhoo on 16/12/14.
 */

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Created by chumhoo on 16/12/14.
 */

class Cards extends RecyclerView.Adapter<Cards.MyViewHolder> {
    Context context;
    MyViewHolder holder;
    ArrayList<DeadlineEvent> deadlines;
    int count = 0;

    public Cards(ArrayList<String> data, Context context) {
        this.context = context;
        deadlines = new ArrayList<>();
    }

    public void add(DeadlineEvent deadline) {
        deadlines.add(deadline);
        notifyDataSetChanged();
    }

    public void delete() {

    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        holder = new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.swipe_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final MyViewHolder mHolder = holder;
        final Date endTime = deadlines.get(position).date;

        holder.textEndTime.setText(endTime.getYear() + "-" + endTime.getMonth() + "-" + endTime.getDay());
        holder.textTaskInfo.setText(deadlines.get(position).getEventName());
        // 刷新时间绑定
        holder.timeHandler = new Handler() {
            @Override
            public void publish(LogRecord logRecord) {
                if (logRecord.getMessage() == "time") {
                    Time t = new Time(deadlines.get(position).date);
                    mHolder.textTimeCountDown.setText(t.getRemainDay() + "天" + t.getRemainHour() + "时");
                }
            }
            @Override
            public void flush() {}
            @Override
            public void close() throws SecurityException {}
        };
    }

    @Override
    public int getItemCount() {
        return deadlines.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textTaskInfo;
        TextView textEndTime;
        TextView textTimeCountDown;

        private Handler timeHandler;

        public MyViewHolder(View view) {
            super(view);
            textTaskInfo = (TextView) view.findViewById(R.id.res_text_task_title);
            textEndTime = (TextView) view.findViewById(R.id.res_text_task_dead_time);
            textTimeCountDown = (TextView) view.findViewById(R.id.res_card_text_time_countdown);

            final SwipeLayout swipe = (SwipeLayout) view.findViewById(R.id.res_card);
            swipe.setEnabled(true);
            swipe.setDragEdge(SwipeLayout.DragEdge.Right);

            Button btnDelete = (Button) view.findViewById(R.id.btn_card_delete);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    swipe.close();
                }
            });

            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.layout_new_deadline_form);
            dialog.create();
            Button btnEdit = (Button) swipe.findViewById(R.id.btn_card_edit);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.show();
                }
            });

            startCountDown();
            count++;
        }

        private void startCountDown() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    while (true) {
                        try {
                            Thread.sleep(1000); // sleep 1000ms
                            timeHandler.publish(new LogRecord(Level.ALL, "time"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
}
