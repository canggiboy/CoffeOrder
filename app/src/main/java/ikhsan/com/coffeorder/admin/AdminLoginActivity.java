package ikhsan.com.coffeorder.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ikhsan.com.coffeorder.R;
import ikhsan.com.coffeorder.user.UserLoginAcitvity;
import ikhsan.com.coffeorder.data.DatabaseHelper;

public class AdminLoginActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
    }

    //method untuk mengaktifkan tombol back pada device
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AdminLoginActivity.this, UserLoginAcitvity.class);
        startActivity(intent);
        this.finish();
        super.onBackPressed();
    }

    //method untuk login ke admin activity dengan username dan password
    public void loginButton(View v) {
        if (v.getId() == R.id.adminLoginButton) {
            EditText a = (EditText) findViewById(R.id.usernameET);
            String string = a.getText().toString();
            EditText b = (EditText) findViewById(R.id.passwordET);
            String pass = b.getText().toString();

            if ((string.equals("admin"))&&(pass.equals("admin321"))){
                Intent intent = new Intent(AdminLoginActivity.this, AdminActivity.class);
                intent.putExtra("username", string);
                startActivity(intent);
                this.finish();
            } else if (TextUtils.isEmpty(string)) {
                a.setError("Username tidak boleh kosong");
                return;
            } else if (TextUtils.isEmpty(pass)) {
                b.setError("Password tidak boleh kosong");
                return;
            } else {
                Toast toast = Toast.makeText(AdminLoginActivity.this, "Username dan Password tidak sesuai!", Toast.LENGTH_SHORT);
                toast.show();
            }

            /*String password = helper.searchPass(string);
            if (pass.equals(password)) {
                Intent intent = new Intent(AdminLoginActivity.this, AdminActivity.class);
                intent.putExtra("username", string);
                startActivity(intent);
                this.finish();
            } else if (TextUtils.isEmpty(string)) {
                a.setError("Username tidak boleh kosong");
                return;
            } else if (TextUtils.isEmpty(pass)) {
                b.setError("Password tidak boleh kosong");
                return;
            } else {
                Toast toast = Toast.makeText(AdminLoginActivity.this, "Username dan Password tidak sesuai!", Toast.LENGTH_SHORT);
                toast.show();
            }*/
        }
    }

}
