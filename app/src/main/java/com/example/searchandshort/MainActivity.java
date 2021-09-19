package com.example.searchandshort;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import androidx.appcompat.widget.SearchView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListView listView;
    private ArrayList<DBHelper> arrayList = new ArrayList<>();
    private ArrayList<DBHelper> arrayListBackup  = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList.add(new DBHelper(12,"Apple"));
        arrayList.add(new DBHelper(162,"Blackberry"));
        arrayList.add(new DBHelper(112,"Apricot"));
        arrayList.add(new DBHelper(132,"Avocado"));
        arrayList.add(new DBHelper(512,"Banana"));

        listView = new ListView(arrayList,this);
        recyclerView.setAdapter(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listView.getFilter().filter(newText);
                return false;
            }
        });
        MenuItem menuItem1 = menu.findItem(R.id.menu_switch);
        SwitchCompat switchCompat =(SwitchCompat) menuItem1.getActionView();
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true){
                    arrayListBackup.addAll(arrayList);
                    Collections.sort(arrayList, new Comparator<DBHelper>() {
                        @Override
                        public int compare(DBHelper o1, DBHelper o2) {
                            return o1.getName().compareTo(o2.getName());
                        }

                    });
                    listView.notifyDataSetChanged();
                }else {
                    arrayList.clear();
                    arrayList.addAll(arrayListBackup);
                    arrayListBackup.clear();
                    listView.notifyDataSetChanged();
                }
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}