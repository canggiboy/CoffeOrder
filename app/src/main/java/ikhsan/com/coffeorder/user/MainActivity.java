package ikhsan.com.coffeorder.user;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ikhsan.com.coffeorder.R;
import ikhsan.com.coffeorder.adapter.CoffeeAdapter;
import ikhsan.com.coffeorder.admin.AddMenuActivity;
import ikhsan.com.coffeorder.admin.AdminActivity;
import ikhsan.com.coffeorder.data.DatabaseHelper;

import ikhsan.com.coffeorder.data.CoffeeContract.coffeeEntry;
import ikhsan.com.coffeorder.model.DatabaseModel;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity implements CoffeeAdapter.ListItemClickListener{

    List<DatabaseModel> dbList;
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Toast mToast;

    private DatabaseHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new DatabaseHelper(this);
        //displayDatabaseInfo();

        dbList = new ArrayList<DatabaseModel>();
        dbList = mDbHelper.getDataFromDB();

        mRecyclerView = (RecyclerView)findViewById(R.id.rv_menu_list);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setHasFixedSize(true);

        // specify an adapter (see also next example)
        mAdapter = new CoffeeAdapter(this,dbList,this);
        mRecyclerView.setAdapter(mAdapter);
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

    /*private void displayDatabaseInfo(){

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] project = {
                coffeeEntry._ID,
                coffeeEntry.COLUMN_MENU_NAME,
                coffeeEntry.COLUMN_MENU_PRICE,
                coffeeEntry.COLUMN_MENU_TYPE,
        };

        Cursor cursor = db.query(
                coffeeEntry.TABLE_MENU,
                project,
                null,
                null,
                null,
                null,
                null
        );

        TextView displayView = (TextView) findViewById(R.id.tv_type_order);

        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // pets table in the database).
            displayView.setText("The coffee table constains "+cursor.getCount()+" menu.\n\n");
            displayView.append(coffeeEntry._ID+" - "+coffeeEntry.COLUMN_MENU_NAME+" - "+coffeeEntry.COLUMN_MENU_PRICE
                    +" - "+coffeeEntry.COLUMN_MENU_TYPE+"\n");

            int idColumnIndex = cursor.getColumnIndex(coffeeEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(coffeeEntry.COLUMN_MENU_NAME);
            int priceColumnIndex = cursor.getColumnIndex(coffeeEntry.COLUMN_MENU_PRICE);
            int typeColumnIndex = cursor.getColumnIndex(coffeeEntry.COLUMN_MENU_TYPE);

            while (cursor.moveToNext()){

                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentprice = cursor.getString(priceColumnIndex);
                int currentType = cursor.getInt(typeColumnIndex);
                String typeString;
                if (currentType == 0){
                    typeString = "Makanan";
                }else {
                    typeString = "Minuman";
                }

                displayView.append(("\n"+currentID+" - "+currentName+" - "+currentprice+" - "+typeString));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
    */
}

