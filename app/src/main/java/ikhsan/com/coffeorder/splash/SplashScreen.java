package ikhsan.com.coffeorder.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import ikhsan.com.coffeorder.user.UserLoginAcitvity;
import ikhsan.com.coffeorder.R;

public class SplashScreen extends AppCompatActivity {

    //set waktu lama splashscreen
    private static int splashInterval = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, UserLoginAcitvity.class);
                startActivity(i); // menghubungkan activity splashscreen ke main activity dengan intent

                //jeda selesai splashscreen
                this.finish();
            }

            private void finish() {

            }
        }, splashInterval);
    }

    ;
}
