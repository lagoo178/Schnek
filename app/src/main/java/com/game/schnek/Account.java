package com.game.schnek;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;
import jahirfiquitiva.libs.fabsmenu.FABsMenu;
import jahirfiquitiva.libs.fabsmenu.TitleFAB;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class Account extends AppCompatActivity {
    private final String TAG = "CA/Account";

    // // Will handle all changes happening in database

    private DatabaseReference databaseReference;
    private ValueEventListener userListener;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
   // private static int PICK_IMAGE = 123;
    Uri imagePath;
    private StorageReference storageReference;
    private CircleImageView image;

    // Users data

    private String currentUserId, otherUserId;

    // activity_profile views
    private ImageView profilePicImageView;
    private TextView name, email;
    private FABsMenu menu;
    private TitleFAB button1, button2;
    private ImageView homeButton;
    private Animation compileAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_account);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        //profilePicImageView = findViewById(R.id.profile_pic_imageView);
        name = findViewById(R.id.profile_name);
        firebaseStorage = FirebaseStorage.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        final StorageReference storageReference = firebaseStorage.getReference();
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(getApplicationContext(),Login.class));
        }
        final FirebaseUser user=firebaseAuth.getCurrentUser();
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                 //final String layoutName = dataSnapshot.child("name").getValue().toString();
                //String layoutEmail = dataSnapshot.child("email").getValue().toString();
                //final String layoutImage = dataSnapshot.child("image").getValue().toString();
                email = findViewById(R.id.profile_email);

                //name.setText(dataSnapshot.child("users").child("name").getValue().toString());
                email.setText(user.getEmail());
                // Get the image stored on Firebase via "User id/Images/Profile Pic.jpg".
                //storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                   // @Override
                   // public void onSuccess(Uri uri) {
                        // Using "Picasso" (http://square.github.io/picasso/) after adding the dependency in the Gradle.
                        // ".fit().centerInside()" fits the entire image into the specified area.
                        // Finally, add "READ" and "WRITE" external storage permissions in the Manifest.
                    //    Picasso.get().load(uri).fit().centerInside().into(profilePicImageView);
            }


            @Override
            public void onCancelled( DatabaseError databaseError) {
                Toast.makeText(Account.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        };
        databaseReference.addValueEventListener(postListener);
        initHomeButton();

    }

    @Override
    protected void onResume()
    {
        super.onResume();

        FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId).setValue(ServerValue.TIMESTAMP);
    }


    //@Override
    //protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    //    super.onActivityResult(requestCode, resultCode, data);

    //    if(requestCode == 1 && resultCode == RESULT_OK) {
            //Uploading selected picture

    //        StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic"); //User id/Images/Profile Pic.jpg
    //        UploadTask uploadTask = imageReference.putFile(imagePath);
    //        uploadTask.addOnFailureListener(new OnFailureListener() {
    //            @Override
    //            public void onFailure(@NonNull Exception e) {
    //                Toast.makeText(Account.this, "Error: Uploading profile picture", Toast.LENGTH_SHORT).show();
    //            }
    //        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
    //            @Override
    //            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
    //                Toast.makeText(Account.this, "Profile picture uploaded", Toast.LENGTH_SHORT).show();
    //            }
    //        });
    //    }

    //}

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);

        switch(item.getItemId())
        {
            case R.id.menuLogout:
                AlertDialog.Builder logoutBuilder = new AlertDialog.Builder(Account.this);
                logoutBuilder.setTitle("Logout");
                logoutBuilder.setMessage("Are you sure you want to logout?");
                logoutBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        //FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("online").setValue(ServerValue.TIMESTAMP);

                        FirebaseAuth.getInstance().signOut();

                        Intent welcomeIntent = new Intent(Account.this, Login.class);
                        startActivity(welcomeIntent);
                        finish();
                    }
                });
                logoutBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.dismiss();
                    }
                });
                AlertDialog logoutDialog = logoutBuilder.create();
                logoutDialog.show();
                return true;
            case R.id.menuAbout:
                AlertDialog.Builder aboutBuilder = new AlertDialog.Builder(Account.this);
                aboutBuilder.setTitle("SCHNEK");
                aboutBuilder.setMessage("Schnek is a mobile game made for completing Mobile Programming Course \n\n Sutan Daffa SH, 2020. All Rights Reserved.");
                aboutBuilder.setNegativeButton("Close", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.dismiss();
                    }
                });
                AlertDialog aboutDialog = aboutBuilder.create();
                aboutDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

                        Animation animationHide = AnimationUtils.loadAnimation(Account.this, R.anim.reverse_for_home_button);
                        animationHide.setDuration(GameSettings.ANIMATION_HIDE_HOME_BUTTON_DURATION);

                        homeButton.startAnimation(animationHide);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intentMain = new Intent(Account.this, MainMenu.class);
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