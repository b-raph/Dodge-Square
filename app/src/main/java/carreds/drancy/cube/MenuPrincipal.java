package carreds.drancy.cube;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


public class MenuPrincipal extends ActionBarActivity {

    Button medium, hard;

    private InterstitialAd interstitial;
    boolean pub = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        interstitial = new InterstitialAd(MenuPrincipal.this);
        interstitial.setAdUnitId("ca-app-pub-1186689335977616/1838994088");
        AdRequest adRequestInterstitial = new AdRequest.Builder().build();
        interstitial.loadAd(adRequestInterstitial);


            medium = (Button) findViewById(R.id.btn_normal);
            hard = (Button) findViewById(R.id.btn_hard);

            medium.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent medium = new Intent(MenuPrincipal.this, LevelMedium.class);
                    startActivity(medium);
                }
            });

            hard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent hard = new Intent(MenuPrincipal.this, MainActivity.class);
                    startActivity(hard);
                }
            });


        if (pub == true) {
            // Prepare an Interstitial Ad Listener
            interstitial.setAdListener(new AdListener() {
                public void onAdLoaded() {
                    // Call displayInterstitial() function
                    displayInterstitial();
                }
            });
            pub = false;
        }

        AdView mAdView = (AdView) findViewById(R.id.adView1);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest1);

    }

    public void displayInterstitial() {
        // If Ads are loaded, show Interstitial else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        this.startActivity(intent);

        super.onBackPressed();
    }
}
