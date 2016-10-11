package com.example.dnguyen1214.todo2day;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //For now (Temporary), delete the old database, then create a new one
        this.deleteDatabase(DBHelper.DATABASE_TABLE);

        // Let's make a DBHelper reference:
        DBHelper dbHelper = new DBHelper(this);



        // Let's make a new task and add it to database
        dbHelper.addTask(new Task(1, "Study for CS273 Midterm", 0));
        dbHelper.addTask(new Task(2, "Sleep", 0));
        dbHelper.addTask(new Task(3, "Eat", 0));
        dbHelper.addTask(new Task(4, "Sleep more", 0));
        dbHelper.addTask(new Task(5, "Learn doubly-linked lists", 0));

        // Let's get all the tasks from database and print them with Log.i()
        ArrayList<Task> allTasks = dbHelper.getAllTasks();
        // Loop through each task, print log.i
        for (Task singleTask : allTasks)
        {
            Log.i("DATABASE TASK", singleTask.toString());
        }

    }
}
