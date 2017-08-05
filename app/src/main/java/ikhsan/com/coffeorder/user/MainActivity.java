package ikhsan.com.coffeorder.user;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import ikhsan.com.coffeorder.R;
import ikhsan.com.coffeorder.adapter.CoffeeAdapter;
import ikhsan.com.coffeorder.data.DatabaseHelper;

import ikhsan.com.coffeorder.model.DatabaseModel;

public class MainActivity extends AppCompatActivity implements CoffeeAdapter.ListItemClickListener{

    ArrayList<DatabaseModel> model;
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private DatabaseHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new DatabaseHelper(this);
        //displayDatabaseInfo();

        model = new ArrayList<DatabaseModel>();
        model = mDbHelper.getDataFromDB();

        mRecyclerView = (RecyclerView)findViewById(R.id.rv_menu_list);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setHasFixedSize(true);

        // specify an adapter (see also next example)
        mAdapter = new CoffeeAdapter(this,model,this);
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tvOrder = (TextView) findViewById(R.id.tv_tot_order);
                TextView tvPrice = (TextView) findViewById(R.id.tv_list_price);
                int intOrder = Integer.parseInt(tvOrder.getText().toString());
                int intPrice = Integer.parseInt(tvPrice.getText().toString());
                int total = intOrder*intPrice;
                Intent i = new Intent(MainActivity.this, TotalActivity.class);
                i.putExtra("Total", total);
                startActivity(i);
            }
        });

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_keluar:
                Intent intent = new Intent(MainActivity.this, UserLoginAcitvity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //method ketika tombol back ditekan pada device dan memunculkan alert dialog
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Keluar")
                .setMessage("Apakah anda yakin ingin keluar?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, UserLoginAcitvity.class);
                        startActivity(intent);
                        finish();
                    }

                })
                .setNegativeButton("Tidak", null)
                .show();

    }
}

