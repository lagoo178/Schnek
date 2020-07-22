package com.game.schnek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;

public class HowPlay extends AppCompatActivity {
    private ImageView homeButton;
    private Animation compileAnimation;
    private RelativeLayout manualLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_how_play);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        manualLayout = (RelativeLayout) findViewById(R.id.manual_layout);
        initHomeButton();
    }

    private void initHomeButton() {
        homeButton = (ImageView) findViewById(R.id.home);
        compileAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_for_home_button);
        compileAnimation.setDuration(GameSettings.ANIMATION_SHOW_HOME_BUTTON_DURATION);
        compileAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                homeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        Animation animationHide = AnimationUtils.loadAnimation(HowPlay.this, R.anim.reverse_for_home_button);
                        animationHide.setDuration(GameSettings.ANIMATION_HIDE_HOME_BUTTON_DURATION);

                        homeButton.startAnimation(animationHide);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intentMain = new Intent(HowPlay.this, MainMenu.class);
                                intentMain.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(intentMain);

                            }
                        }, GameSettings.START_NEW_ACTIVITY_DURATION);
                    }
                });

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        homeButton.startAnimation(compileAnimation);
    }
    @Override
    public void onBackPressed() {
    }
}