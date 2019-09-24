package com.zhangyc.framedemo.mvvm;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.zhangyc.framedemo.R;
import com.zhangyc.framedemo.databinding.ActivityMvvmBinding;
import com.zhangyc.framedemo.entity.Student;

public class MVVMActivity extends AppCompatActivity {

    public static final String TAG = MVVMActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: ");
        ActivityMvvmBinding activityMvvmBinding =  DataBindingUtil.setContentView(this, R.layout.activity_mvvm);
        Student student = new Student();
        student.setStudentName("YD");
        activityMvvmBinding.setStudent(student);

        /*activityMvvmBinding.textName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClickListener: textName.");
            }
        });*/
    }


    public static class HandlerClickEvent {

        public void onClickListener(View view) {
            Log.d(TAG, "onClickListener: view");
            /*switch (view.getId()) {
                case R.id.text_name:
                    Log.e(TAG, "onClickListener: ");
                    break;
            }*/
        }

    }

}
