package com.summer.and.diana.adventuretime.ui;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.summer.and.diana.adventuretime.R;
import com.summer.and.diana.adventuretime.adapters.GearAdapter;
import com.summer.and.diana.adventuretime.models.Gear;
import com.summer.and.diana.adventuretime.models.User;

import java.util.ArrayList;

public class MyGearActivity extends ListActivity {
    private SharedPreferences mPreferences;
    private User mUser;
    private EditText mItemEditText, mDescriptionEditText;
    private Button mAddGearButton;
    private String mCurrentUser;
    private ListView mListView;
    private ArrayList<Gear> mGearList;
    private GearAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_gear);

        mPreferences = getApplicationContext().getSharedPreferences("adventuretime", Context.MODE_PRIVATE);
        mAddGearButton = (Button) findViewById(R.id.addGearButton);
        mItemEditText = (EditText) findViewById(R.id.itemEditText);
        mDescriptionEditText = (EditText) findViewById(R.id.descriptionEditText);
        mCurrentUser = mPreferences.getString("username", null);
        mListView = getListView();
        mGearList = (ArrayList) Gear.allFromUser(mUser);

        mAdapter = new GearAdapter(this, mGearList);
        setListAdapter(mAdapter);

        mAddGearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = mItemEditText.getText().toString().trim();
                String description = mDescriptionEditText.getText().toString().trim();
                Gear newGear = new Gear(mCurrentUser, item, description);
                newGear.save();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_gear, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}