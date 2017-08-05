package ikhsan.com.coffeorder.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ikhsan.com.coffeorder.R;

public class TotalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);

        //Mengambil nilai dari main activity dan ditampilkan di activity ini
        TextView textview = (TextView)findViewById(R.id.totalBayar);
        Intent intent = getIntent();
        int strValue = intent.getIntExtra("Total", 0);
        textview.setText("Rp."+strValue);
    }

    //method ketika tombol back ditekan pada device dan memunculkan alert dialog
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Keluar")
                .setMessage("Apakah anda yakin ingin keluar?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("Tidak", null)
                .show();
    }

    /**method untuk melakukan pemesanan kembali yang menghubungkan dari activity sekarang
     *ke LoginActivity
     * @param view
     */
    public void pesanLagi(View view){
        Intent intent = new Intent(getApplicationContext(), UserLoginAcitvity.class);
        startActivity(intent);
        this.finish();
    }

}
