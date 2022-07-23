package com.ioserv.done;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.ObjectsCompat;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class UserTasks extends AppCompatActivity {

    ListView tasksListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_tasks);

        tasksListView = findViewById(R.id.tasksListView);

        //DummyData
        ArrayList<Task> arrayList = new ArrayList<>();
        arrayList.add(new Task(R.drawable.feed_pet, "31-12-2022", "5 CAD", "daily"));
        arrayList.add(new Task(R.drawable.dishes, "08-12-2022", "15 CAD", "3 days at week"));
        arrayList.add(new Task(R.drawable.mop_clean, "09-12-2022", "20 CAD", "1 day at week"));

        //custom adapter
        TaskAdapter taskAdapter = new TaskAdapter(this,R.layout.task_row,arrayList);
        tasksListView.setAdapter(taskAdapter);


    }
}