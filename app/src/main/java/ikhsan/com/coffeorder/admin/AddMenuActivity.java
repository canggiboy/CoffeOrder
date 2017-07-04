package ikhsan.com.coffeorder.admin;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import ikhsan.com.coffeorder.R;
import ikhsan.com.coffeorder.data.CoffeeContract;
import ikhsan.com.coffeorder.data.CoffeeContract.coffeeEntry;
import ikhsan.com.coffeorder.data.DatabaseHelper;

import static android.text.style.TtsSpan.GENDER_MALE;

/**
 * Created by ikhsan on 20/06/17.
 */

public class AddMenuActivity extends AppCompatActivity {

    private EditText mNameEditText;

    private EditText mPriceEditText;

    private Spinner mTypeSpinner;

    private int mType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_menu);

        mNameEditText = (EditText) findViewById(R.id.edit_name);
        mPriceEditText = (EditText) findViewById(R.id.edit_price);
        mTypeSpinner = (Spinner) findViewById(R.id.spinner_type);

        setupSpinner();
    }

    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter typeSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_type_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        typeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mTypeSpinner.setAdapter(typeSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.type_foods))) {
                        mType = coffeeEntry.TYPE_FOODS;
                    } else {
                        mType = coffeeEntry.TYPE_DRINKS;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent)  {
                mType = 0; // Unknown
            }
        });
    }

    private void insertMenu(){

        String nameString = mNameEditText.getText().toString().trim();
        String priceString = mPriceEditText.getText().toString().trim();

        DatabaseHelper mDbHelper = new DatabaseHelper(this);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(coffeeEntry.COLUMN_MENU_NAME, nameString);
        values.put(coffeeEntry.COLUMN_MENU_PRICE, priceString);
        values.put(coffeeEntry.COLUMN_MENU_TYPE, mType);

        long newRowId = db.insert(coffeeEntry.TABLE_MENU, null, values);

        if(newRowId == -1){
            Toast.makeText(this, "Error with saving text", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Pet saved with row id = "+newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_tambah, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                String name = mNameEditText.getText().toString();
                String price = mPriceEditText.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    mNameEditText.setError("Nama tidak boleh kosong");
                } else if (TextUtils.isEmpty(price)) {
                    mPriceEditText.setError("Harga tidak boleh kosong");
                } else {
                    insertMenu();
                    finish();
                }
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
