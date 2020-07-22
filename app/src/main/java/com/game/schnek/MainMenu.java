package com.game.schnek;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {

    private RelativeLayout snakeLayout;
    private Animation compileAnim;
    private ImageView classicBtn;
    private ImageView noWallsBtn;
    private ImageView bombBtn;
    private ImageView settingsBtn;
    private TextView titleLeft;
    private TextView titleMiddle;
    private TextView titleRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        snakeLayout = (RelativeLayout) findViewById(R.id.snake_layout);

        //hide action bar

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        initClassic();
        initNoWalls();
        initBomb();
        initTitle();
        initSettings();

    }
    private void initClassic(){
        classicBtn = (ImageView) findViewById(R.id.classic);
        compileAnim = AnimationUtils.loadAnimation(MainMenu.this,R.anim.anim_for_classic_button);
        compileAnim.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);
        compileAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                classicBtn.setImageResource(R.mipmap.classic);
                classicBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentClassic = new Intent(MainMenu.this,ClassicSnake.class);
                        intentClassic.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intentClassic);
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        classicBtn.startAnimation(compileAnim);
    }

    private void initNoWalls(){
        noWallsBtn = (ImageView) findViewById(R.id.no_walls);
        compileAnim = AnimationUtils.loadAnimation(MainMenu.this,R.anim.anim_for_no_button);
        compileAnim.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);
        compileAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                noWallsBtn.setImageResource(R.mipmap.no_walls);
                noWallsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentNoWalls = new Intent(MainMenu.this,NoWallsSnake.class);
                        intentNoWalls.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intentNoWalls);
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        noWallsBtn.startAnimation(compileAnim);
    }

    private void initBomb(){
        bombBtn = (ImageView) findViewById(R.id.bomb);
        compileAnim = AnimationUtils.loadAnimation(MainMenu.this,R.anim.anim_for_bomb_button);
        compileAnim.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);
        compileAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bombBtn.setImageResource(R.mipmap.bombsnake);
                bombBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent bombSnakeIntent = new Intent(MainMenu.this,BombSnake.class);
                        bombSnakeIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(bombSnakeIntent);
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        bombBtn.startAnimation(compileAnim);
    }


    private void initTitle(){
        titleLeft = (TextView) findViewById(R.id.snake_left);
        titleMiddle = (TextView) findViewById(R.id.snake_middle);
        titleRight = (TextView) findViewById(R.id.snake_right);


        //Set up animation for title left
        compileAnim = AnimationUtils.loadAnimation(MainMenu.this,R.anim.back_anim_for_title_left);
        compileAnim.setDuration(GameSettings.ANIMATION_HIDE_TITLE_DURATION);
        compileAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        titleLeft.startAnimation(compileAnim);

        //Set up animation for title middle
        compileAnim = AnimationUtils.loadAnimation(MainMenu.this,R.anim.back_anim_for_title_middle);
        compileAnim.setDuration(GameSettings.ANIMATION_HIDE_TITLE_DURATION);
        compileAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        titleMiddle.startAnimation(compileAnim);


        //Set up animation for title right
        compileAnim = AnimationUtils.loadAnimation(MainMenu.this,R.anim.back_anim_for_title_right);
        compileAnim.setDuration(GameSettings.ANIMATION_HIDE_TITLE_DURATION);
        compileAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        titleRight.startAnimation(compileAnim);
    }

    private void initSettings(){
        settingsBtn  = (ImageView) findViewById(R.id.settings);
        compileAnim = AnimationUtils.loadAnimation(MainMenu.this,R.anim.anim_for_settings_button);
        compileAnim.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);
        compileAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                settingsBtn.setImageResource(R.mipmap.settings);
                settingsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        settingsBtn.setImageResource(R.mipmap.menu_options);
                        classicBtn.setImageResource(R.mipmap.menu_options);
                        noWallsBtn.setImageResource(R.mipmap.menu_options);
                        bombBtn.setImageResource(R.mipmap.menu_options);

                        Animation animation = AnimationUtils.loadAnimation(MainMenu.this,R.anim.reverse_for_classic_button);
                        animation.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);

                        Animation animation2 = AnimationUtils.loadAnimation(MainMenu.this,R.anim.reverse_for_no_button);
                        animation2.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);

                        Animation animation3 = AnimationUtils.loadAnimation(MainMenu.this,R.anim.reverse_for_bomb_button);
                        animation3.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);

                        Animation animation4 = AnimationUtils.loadAnimation(MainMenu.this,R.anim.reverse_for_settings_button);
                        animation4.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);

                        Animation animationTitleLeft = AnimationUtils.loadAnimation(MainMenu.this,R.anim.anim_for_title_left);
                        animationTitleLeft.setDuration(GameSettings.ANIMATION_SHOW_TITLE_DURATION);

                        Animation animationTitleMiddle = AnimationUtils.loadAnimation(MainMenu.this,R.anim.anim_for_title_middle);
                        animationTitleMiddle.setDuration(GameSettings.ANIMATION_SHOW_TITLE_DURATION);

                        Animation animationTitleRight = AnimationUtils.loadAnimation(MainMenu.this,R.anim.anim_for_title_right);
                        animationTitleRight.setDuration(GameSettings.ANIMATION_SHOW_TITLE_DURATION);

                        classicBtn.startAnimation(animation);
                        noWallsBtn.startAnimation(animation2);
                        bombBtn.startAnimation(animation3);
                        settingsBtn.startAnimation(animation4);
                        titleLeft.startAnimation(animationTitleLeft);
                        titleMiddle.startAnimation(animationTitleMiddle);
                        titleRight.startAnimation(animationTitleRight);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent settingsIntent = new Intent(MainMenu.this,Settings.class);
                                settingsIntent.putExtra("userid", FirebaseAuth.getInstance().getCurrentUser().getUid());
                                settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(settingsIntent);
                            }
                        },GameSettings.START_NEW_ACTIVITY_DURATION);

                    }
                });

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        settingsBtn.setAnimation(compileAnim);
    }

    @Override
    public void onStart()
    {
        super.onStart();



        if(FirebaseAuth.getInstance().getCurrentUser() == null)
        {
            // If no logged in user send them to login/register

            Intent welcomeIntent = new Intent(MainMenu.this, Login.class);
            startActivity(welcomeIntent);
            finish();
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if(currentUser != null)
        {
            FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser.getUid());
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if(currentUser != null)
        {
            FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser.getUid()).setValue(ServerValue.TIMESTAMP);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainMenu.this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to close the application?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }



}