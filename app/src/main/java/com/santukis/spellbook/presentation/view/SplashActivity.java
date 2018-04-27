package com.santukis.spellbook.presentation.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.santukis.spellbook.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_splash);
        RelativeLayout relativeLayout = findViewById(R.id.main_layout);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        animation.setDuration(2000);

        relativeLayout.startAnimation(animation);

        int ANIMATION_DURATION = 3000;

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this,
                    MainActivity.class));
            SplashActivity.this.finish();

        }, ANIMATION_DURATION);
    }
}
