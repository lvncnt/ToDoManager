package course.labs.todomanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ToDoListAdapter extends ArrayAdapter {

    private final Context context;
    private final List<ToDoItem> mItems = new ArrayList<>();

    public ToDoListAdapter(Context context) {
        super(context, R.layout.todo_item);
        this.context = context;

    }

   // Add a ToDoItem to the adapter Notify observers that the data set has changed
    public void add(ToDoItem item){
        mItems.add(item);
        notifyDataSetChanged();
    }

    @Override
    /* Create a View for the ToDoItem at specified position */
    public View getView(int position, View convertView, ViewGroup parent) {

        final ToDoItem toDoItem = mItems.get(position);

        RelativeLayout itemLayout = null;
        if(convertView == null ){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemLayout = (RelativeLayout) inflater
                    .inflate(R.layout.todo_item, parent, false);
        }else{
            itemLayout = (RelativeLayout) convertView;
        }

        // Fill in specific ToDoItem data
        final TextView titleView = (TextView) itemLayout.findViewById(R.id.titleView);
        titleView.setText(toDoItem.getTitle());

        // Set up Status CheckBox
        final CheckBox statusView = (CheckBox) itemLayout.findViewById(R.id.statusCheckBox);
        statusView.setChecked(toDoItem.getStatus() == ToDoItem.Status.DONE);
        statusView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){ // if the checkbox is now marked as DONE
                    toDoItem.setStatus(ToDoItem.Status.DONE); // change the status of the item itself
                    statusView.setChecked(true); // change the appearance of the the checkbox
                }else{
                    toDoItem.setStatus(ToDoItem.Status.NOTDONE);
                    statusView.setChecked(false);
                }
            }
        });

        // Display Priority in a TextView
        final TextView priorityView = (TextView) itemLayout.findViewById(R.id.priorityView);
        priorityView.setText(": " + toDoItem.getPriority().toString());

        // Display Time and Date.
        final TextView dateView = (TextView) itemLayout.findViewById(R.id.dateView);
        dateView.setText(" " + ToDoItem.FORMAT.format(toDoItem.getDate()));

        return itemLayout;
    }

    public void clear(){ mItems.clear(); notifyDataSetChanged(); } // Clears the list adapter of all items

    @Override
    public int getCount() {
        return mItems.size();
    } // Returns the number of ToDoItems

    @Override
    public Object getItem(int i) {
        return mItems.get(i);
    } // Retrieve the number of ToDoItems

    @Override
    public long getItemId(int i) {
        return i;
    } // Get the ID for the ToDoItem

}
