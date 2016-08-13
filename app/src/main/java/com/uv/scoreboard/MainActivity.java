package com.uv.scoreboard;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_HOLES = "KEY_HOLES";
    private static final String KEY_STROKECOUNT = "key_strokecount";
    private Hole[] mHoles=new Hole[18];;

    private static final String PREFS_FILE = "com.uv.Scoreboard.preferences";
    // Interface for accessing and modifying preference data
    private SharedPreferences mSharedPreferences;
    // Object for reading and writing preferences
    private SharedPreferences.Editor mEditor;
    private HoleAdapter mHoleAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", " onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        // Create a new Editor for these preferences
        mEditor = mSharedPreferences.edit();

        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerView);

        // Initialize holes
        int strokes = 0;
        for (int i = 0; i < mHoles.length; i++) {
            // get value from preferences. default set to 0
            strokes = mSharedPreferences.getInt(KEY_STROKECOUNT + i, 0);
            mHoles[i] = new Hole("Hole " + (i + 1) + " :", strokes);
        }

        mHoleAdapter= new HoleAdapter(this,mHoles);
        mRecyclerView.setAdapter(mHoleAdapter);
        // LayoutManager determines when list items are no longer visible and can be reused
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    protected void onPause() {
        Log.d("MainActivity", " onPause");
        super.onPause();
        // save key-value pair of mEditText's text
        for (int i = 0; i < mHoles.length; i++) {
            /* going to make 18 different keys, like:
            "key_strokecount1"
            "key_strokecount2"
            "key_strokecount3"
            */
            mEditor.putInt(KEY_STROKECOUNT + i, mHoles[i].getStrokes());
        }
        mEditor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("MainActivity","onCreateOptionsMenu");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("MainActivity","onOptionsItemSelected");
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear_strokes) {
            // clear preferences file
            mEditor.clear();
            mEditor.apply();

            // reset holes strokes
            for (Hole hole: mHoles) {
                hole.setStrokes(0);
            }

            // tell our adapter, that changes happened.
            // will be reinitialized.
            mHoleAdapter.notifyDataSetChanged();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /* Lifecycle Logging */

    @Override
    protected void onStart() {
        Log.d("MainActivity", " onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("MainActivity"," onResume");
        super.onResume();
    }

    // when activity is no longer visible
    @Override
    protected void onStop() {
        Log.d("MainActivity", " onStop");
        super.onStop();
    }

    // called after onStop(), if activity recovers. followed by onStart()
    @Override
    protected void onRestart() {
        Log.d("MainActivity", " onRestart");
        super.onRestart();
    }

    // Not guaranteed to be called when adnroid terminates activity
    @Override
    protected void onDestroy() {
        Log.d("MainActivity"," onDestroy");
        super.onDestroy();
    }

}
