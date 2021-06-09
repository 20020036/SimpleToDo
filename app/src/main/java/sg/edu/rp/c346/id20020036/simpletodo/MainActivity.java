package sg.edu.rp.c346.id20020036.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etTask;
    Button btnAdd, btnDelete, btnClear;
    ListView lv;
    Spinner spinner;
    ArrayList<String> tasks;
    ArrayAdapter<String> AA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.etTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDelete = findViewById(R.id.buttonDelete);
        spinner = findViewById(R.id.spinner);
        btnClear = findViewById(R.id.buttonClear);
        lv = findViewById(R.id.listView);

        tasks = new ArrayList<>();

        AA = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        lv.setAdapter(AA);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etTask.getText().toString();
                tasks.add(task);
                AA.notifyDataSetChanged();
                etTask.setText("");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasks.clear();
                AA.notifyDataSetChanged();
                etTask.setText("");
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tasks.size() > 0)
                {
                    Integer index = Integer.parseInt(etTask.getText().toString());
                    if(index < tasks.size())
                    {
                        tasks.remove(index);
                        AA.notifyDataSetChanged();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_LONG).show();
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position)
                {
                    case 0:
                        etTask.setHint("Type in a new task here");
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;
                    case 1:
                        etTask.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}