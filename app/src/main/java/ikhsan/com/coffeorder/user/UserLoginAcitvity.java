package ikhsan.com.coffeorder.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import ikhsan.com.coffeorder.R;
import ikhsan.com.coffeorder.admin.AdminLoginActivity;
import ikhsan.com.coffeorder.data.DatabaseHelper;

public class UserLoginAcitvity extends AppCompatActivity {

    DatabaseHelper myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitvity);
        myDatabase = new DatabaseHelper(this);
    }

    //Method action ketika menekan login button
    public void loginButton(View view) {
        EditText editText = (EditText) findViewById(R.id.nama);
        String strEditText = editText.getText().toString();
        EditText editText1 = (EditText) findViewById(R.id.noMeja);
        String strEditText1 = editText1.getText().toString();

        if (TextUtils.isEmpty(strEditText)) {
            editText.setError("Nama tidak boleh kosong");
            return;
        } else if (TextUtils.isEmpty(strEditText1)) {
            editText1.setError("No. meja tidak boleh kosong");
            return;
        } else {
            Intent intent = new Intent(UserLoginAcitvity.this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

    //method untuk menampilkan alert dialog ketika menekan tombol back pada device
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Keluar")
                .setMessage("Apakah anda yakin ingin keluar?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("Tidak", null)
                .show();
    }

    //Method menuju ke login admin
    public void linkAdmin(View view) {
        Intent intent = new Intent(UserLoginAcitvity.this, AdminLoginActivity.class);
        startActivity(intent);
        this.finish();
    }


}
