package course.labs.todomanager;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class ToDoManagerActivity extends ListActivity {

    private ToDoListAdapter toDoListAdapter;
    private final static int ADD_TODO_ITEM_REQUEST = 0;
    private final static String TAG = "Lab-UserInterface";
    private static final String FILE_NAME = "TodoManagerActivityData.txt";

    // IDs for menu items
    private static final int MENU_DELETE = Menu.FIRST;
    private static final int MENU_DUMP = Menu.FIRST + 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a new TodoListAdapter for ToDoManagerActivity's ListView
        toDoListAdapter = new ToDoListAdapter(getApplicationContext());
        // Put divider between ToDoItems and FooterView
        getListView().setFooterDividersEnabled(true);

        TextView footerView = (TextView) getLayoutInflater().inflate(R.layout.footer_view, null);
        ListView listView = getListView();
        listView.addFooterView(footerView);

        footerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ToDoManagerActivity.this, AddToDoActivity.class);
                startActivityForResult(intent, ADD_TODO_ITEM_REQUEST);
            }
        });

        // Attach the adapter to this ListActivity's ListView
        getListView().setAdapter(toDoListAdapter);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check result code and request code
        if(resultCode == RESULT_OK && requestCode == ADD_TODO_ITEM_REQUEST){
            Bundle bundle = data.getExtras();
            ToDoItem toDoItem = new ToDoItem(data);
            toDoListAdapter.add(toDoItem);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (toDoListAdapter.getCount() == 0)
            loadItems();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveItems();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(Menu.NONE, MENU_DELETE, Menu.NONE, "Delete all");
        menu.add(Menu.NONE, MENU_DUMP, Menu.NONE, "Dump to log");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_DELETE:
                toDoListAdapter.clear();
                return true;
            case MENU_DUMP:
                dump();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void dump() {
        for (int i = 0; i < toDoListAdapter.getCount(); i++) {
            String data = ((ToDoItem) toDoListAdapter.getItem(i)).toLog();
            Log.i(TAG,	"Item " + i + ": " + data.replace(ToDoItem.ITEM_SEP, ","));
        }

    }

    // Load stored ToDoItems
    private void loadItems() {
        BufferedReader reader = null;
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            reader = new BufferedReader(new InputStreamReader(fis));

            String title = null;
            String priority = null;
            String status = null;
            Date date = null;

            while (null != (title = reader.readLine())) {
                priority = reader.readLine();
                status = reader.readLine();
                date = ToDoItem.FORMAT.parse(reader.readLine());
                toDoListAdapter.add(new ToDoItem(title, ToDoItem.Priority.valueOf(priority),
                        ToDoItem.Status.valueOf(status), date));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Save ToDoItems to file
    private void saveItems() {
        PrintWriter writer = null;
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    fos)));

            for (int idx = 0; idx < toDoListAdapter.getCount(); idx++) {

                writer.println(toDoListAdapter.getItem(idx));

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != writer) {
                writer.close();
            }
        }
    }
}
