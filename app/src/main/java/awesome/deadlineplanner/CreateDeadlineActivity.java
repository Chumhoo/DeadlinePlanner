package awesome.deadlineplanner;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

/**
 * Created by chumhoo on 17/1/7.
 */

public class CreateDeadlineActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_new_deadline_form);

        final Dialog dialogDatePicker = new Dialog(CreateDeadlineActivity.this);
        dialogDatePicker.setContentView(R.layout.layout_dialog_date_pick);
        dialogDatePicker.create();
        final Dialog dialogTimePicker = new Dialog(CreateDeadlineActivity.this);
        dialogTimePicker.setContentView(R.layout.dialog_time_pick);
        dialogTimePicker.create();

        Button btnSetDate = (Button)findViewById(R.id.button_pick_date);
        btnSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDatePicker.show();
            }
        });

        Button btnConfirmDate = (Button)dialogDatePicker.findViewById(R.id.confirm_date);
        btnConfirmDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDatePicker.hide();
                dialogTimePicker.show();
                DatePicker dp = (DatePicker)dialogDatePicker.findViewById(R.id.date_picker);
                System.out.println(dp.getMonth());
            }
        });
        Button btnCancelDate = (Button)dialogDatePicker.findViewById(R.id.cancel_date);
        btnCancelDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDatePicker.hide();
            }
        });
        Button btnConfirmTime = (Button)dialogTimePicker.findViewById(R.id.confirm_time);
        btnConfirmTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogTimePicker.hide();
            }
        });
        Button btnCancelTime = (Button)dialogTimePicker.findViewById(R.id.cancel_time);
        btnCancelTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogTimePicker.hide();
            }
        });

    }
}
