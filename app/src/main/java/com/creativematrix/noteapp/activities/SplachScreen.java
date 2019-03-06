package com.creativematrix.noteapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;


import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.fragments.CompanyLoginFragment;
import com.creativematrix.noteapp.fragments.CompanyRegisterFragment;
import com.creativematrix.noteapp.util.PreferenceHelper;
import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;


import java.util.Locale;


public class SplachScreen extends AwesomeSplash
{



    @Override
    public void initSplash(ConfigSplash configSplash)
    {


            /* you don't have to override every property */
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.white); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(1000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.drawable.logo);

        //or any other drawable
        configSplash.setAnimLogoSplashDuration(1000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.FadeInLeft); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


        //Customize Title
        configSplash.setTitleSplash("Note App");
        configSplash.setTitleTextColor(R.color.color_blue_primary);
        configSplash.setTitleTextSize(30f); //float value
        configSplash.setAnimTitleDuration(2000);
        configSplash.setAnimTitleTechnique(Techniques.FadeInRight);
        configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(400); //in relation to your svg (path) resource



    }

    @Override
    public void animationsFinished()
    {
        if(PreferenceHelper.getPrefernceHelperInstace().isUserLoggedIn(SplachScreen.this)){
            Intent intent = new Intent(this, NoteHomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else {
            startActivity(new Intent(SplachScreen.this,BeforeLoginActivity.class));
            finish();
        }


    }

    /**
     * Change the app lang
     * @param context
     */


}
