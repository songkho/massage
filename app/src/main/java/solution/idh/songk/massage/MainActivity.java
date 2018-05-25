
package solution.idh.songk.massage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private InterstitialAd interstitial;

    Vibrator vib;
    TextView sharetv;
    TextView shareEnglish;
    LinearLayout sharelay;
    ToggleButton tb1;


    private final long FINSH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;


    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINSH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
            vib.cancel();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "'뒤로'버튼을한번더누르시면종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharetv = (TextView) findViewById(R.id.sharetv);

        shareEnglish = (TextView) findViewById(R.id.shareE);


        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/jua.ttf");  //asset > fonts 폴더 내 폰트파일 적용

        sharetv.setTypeface(typeFace);

        shareEnglish.setTypeface(typeFace);

        ImageView img3 = (ImageView) findViewById(R.id.imageView3);

        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId(getResources().getString(R.string.ad_unit_id));

        sharelay = (LinearLayout) findViewById(R.id.sharelay);

        sharelay.setOnClickListener(this);


        AdRequest adRequests = new AdRequest.Builder().addTestDevice("2298F494689D958B354FB388C17F2CB0").build();

        interstitial.loadAd(adRequests);
        interstitial.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
// TODO Auto-generated method stub
                super.onAdLoaded();
                interstitial.show();
            }
        });

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
                .addTestDevice("2298F494689D958B354FB388C17F2CB0")  // An example device ID
                .build();
        mAdView.loadAd(adRequest);

        final ToggleButton tb1 = (ToggleButton) this.findViewById(solution.idh.songk.massage.R.id.tb);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        tb1.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {



                long[] pattern = {0, 500, 0, 500};
                vib.vibrate(pattern, 0);

                if (tb1.isChecked()) {


                    tb1.setBackgroundDrawable(getResources().getDrawable(solution.idh.songk.massage.R.drawable.on2)

                    );
                }  else {



                    vib.cancel();

                    tb1.setBackgroundDrawable(getResources().getDrawable(solution.idh.songk.massage.R.drawable.off2)

                    );
                } // end if


            } // end onClick()
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.sharelay:
                Intent msg = new Intent(Intent.ACTION_SEND);
                msg.addCategory(Intent.CATEGORY_DEFAULT);
                msg.putExtra(Intent.EXTRA_SUBJECT, "Health Massager https://play.google.com/store/apps/details?id=solution.idh.songk.massage&hl=ko");
                msg.putExtra(Intent.EXTRA_TEXT, "");
                msg.putExtra(Intent.EXTRA_TITLE, "제목");
                msg.setType("text/plain");
                startActivity(Intent.createChooser(msg, "공유"));
                break;


        }

    }
}
