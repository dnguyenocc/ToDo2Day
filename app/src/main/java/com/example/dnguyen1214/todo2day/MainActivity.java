package com.example.dnguyen1214.todo2day;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DBHelper database;
    private List<Task> taskList;
    private TaskListAdapter taskListAdapter;

    private EditText taskEditText;
    private ListView taskListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //For now (Temporary), delete the old database, then create a new one
        //this.deleteDatabase(DBHelper.DATABASE_TABLE);

        taskEditText = (EditText) findViewById(R.id.taskEditText);
        taskListView = (ListView) findViewById(R.id.taskListView);

        // Let's make a DBHelper reference:
        database = new DBHelper(this);


        // Let's fill the list with Tasks from the database
        taskList = database.getAllTasks();

        // Let's create our custom task list adapter
        // (We want to associate the adapter with context, the layout, and the List)
        taskListAdapter = new TaskListAdapter(this, R.layout.task_item, taskList);

        // Associate the adapter with the list view
        taskListView.setAdapter(taskListAdapter);


    }

    public void addTask (View view)
    {
        String description = taskEditText.getText().toString();
        if (description.isEmpty())
        {
            Toast.makeText(this,"Task description cannot be empty.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            // Let's make a new Task
            Task newTask = new Task(description,0);

            // Let's add the Task to the database
            database.addTask(newTask);

            // Let's add the new Task to the Adapter
            taskListAdapter.add(newTask);

            taskEditText.setText("");
        }
    }

    public void changeTaskStatus(View view)
    {
        if (view instanceof CheckBox)
        {
            CheckBox selectedCheckbox = (CheckBox) view;

            Task selectedTask = (Task) selectedCheckbox.getTag();

            selectedTask.setIsDone(selectedCheckbox.isChecked() ? 1 : 0);

            database.updateTask(selectedTask);
        }

    }

    public void clearAllTasks(View view)
    {
        // Clear the list
        taskList.clear();
        // Delete all the records (Tasks) in the database
        database.deleteAllTasks();
        // Tell the TaskListAdapter to update itself.
        taskListAdapter.notifyDataSetChanged();
    }
}
