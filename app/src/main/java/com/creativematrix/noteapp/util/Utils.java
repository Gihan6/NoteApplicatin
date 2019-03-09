package com.creativematrix.noteapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Utils {

    public static final String ATTRIBUTE_TTF_KEY = "ttf_name";

    public static final String ATTRIBUTE_SCHEMA = "http://schemas.android.com/apk/lib/com.hitesh_sahu.retailapp.util";
    public final static String MANAGE_PROFILE_TAG = "ManageProfileFragment";

    public final static String SHOPPING_LIST_TAG = "SHoppingListFragment";
    public final static String PLACE_ORDER_FRAGMENT = "PlaceOrderFragment";
    public final static String BROWSE_PHARMACIES_FRAGMENT = "BrowsePharmaciesFragment";
    public final static String MY_LOCATIONS_FRAGMENT = "MyLocationsFragment";


    public static final String PRODUCT_OVERVIEW_FRAGMENT_TAG = "ProductListFragment";
    public static final String OFFER_OVERVIEW_FRAGMENT_TAG = "OffersDetailsFragment";
    public static final String MY_CART_FRAGMENT = "MyCartFragment";
    public static final String MY_ORDERS_FRAGMENT = "MYOrdersFragment";
    public static final String HOME_FRAGMENT = "HomeFragment";
    public static final String SEARCH_FRAGMENT_TAG = "SearchFragment";
    public static final String OTP_LOGIN_TAG = "OTPLogingFragment";
    public static final String CONTACT_US_FRAGMENT = "ContactUs";
    public static final String RECORD_FRAGMENT = "CurrentRecordFragment";
    public static final String ADD_LOCATION_FRAGMENT = "AddLocationFragment";
    public static final String ABOUT_US_FRAGMENT = "AboutUsFragment";
    public static final String RATE_UP_FRAGMENT = "RateUpFragment";

    public static final String FRAGMENT_MAIN_ORDERS = "FragmentMainOrders";
    public static final String VIEWAllPROJECTSFRAGGMENT = "ViewAllProjectsFragment";
    public static final String VIEWAllGROUPSFRAGGMENT = "ViewAllGroupsFragment";
    public static final String VIEWAllTASKSFRAGGMENT = "ViewAllTasksFragment";
    public static final String ADDNEWGROUPFRAGMENT = "AddNewGroupFragment";
    public static final String ADDNEWTASKFRAGMENT = "AddNewTaskFragment";
    private static String CURRENT_TAG = null;
    private static Map<String, Typeface> TYPEFACE = new HashMap<String, Typeface>();

    public static int getToolbarHeight(Context context) {
        int height = (int) context.getResources().getDimension(
                R.dimen.abc_action_bar_default_height_material);
        return height;
    }


    public static String encodeImage(String selectedPath) {


        byte[] audioBytes;
        try {
            // Just to check file size.. Its is correct i-e; Not Zero
            File audioFile = BitmapHelper.saveBitmapToFile(new File(selectedPath));
            long fileSize = audioFile.length();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileInputStream fis = new FileInputStream(audioFile);
            byte[] buf = new byte[1024];
            int n;
            while (-1 != (n = fis.read(buf)))
                baos.write(buf, 0, n);
            audioBytes = baos.toByteArray();

            // Here goes the Base64 string
            String FileBase64 = Base64.encodeToString(audioBytes, Base64.DEFAULT);
            return FileBase64;

        } catch (Exception e) {
            // DiagnosticHelper.writeException(e);
            return null;
        }

    }

    public interface DelayCallback{
        void afterDelay();
    }
    public static String formatDecimal(Double value) {
        DecimalFormat df = new DecimalFormat("#,###.##");
        return df.format(Double.valueOf(value));
    }
    public static Drawable tintMyDrawable(Drawable drawable, int color) {
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, color);
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        return drawable;
    }

    public static String getRealPathFromURI(Uri contentUri, Context mContext) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(mContext, contentUri, proj,
                null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Convert milliseconds into time hh:mm:ss
     *
     * @param milliseconds
     * @return time in String
     */
    public static String getDuration(long milliseconds) {
        long sec = (milliseconds / 1000) % 60;
        long min = (milliseconds / (60 * 1000)) % 60;
        long hour = milliseconds / (60 * 60 * 1000);

        String s = (sec < 10) ? "0" + sec : "" + sec;
        String m = (min < 10) ? "0" + min : "" + min;
        String h = "" + hour;

        String time = "";
        if (hour > 0) {
            time = h + ":" + m + ":" + s;
        } else {
            time = m + ":" + s;
        }
        return time;
    }

    public static int dpToPx(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    public static void switchFragmentWithAnimation(int id, Fragment fragment, FragmentActivity activity, String TAG, AnimationType transitionStyle) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (transitionStyle != null) {
            switch (transitionStyle) {
                case SLIDE_DOWN:

                    // Exit from down
                    fragmentTransaction.setCustomAnimations(R.anim.slide_up,
                            R.anim.slide_down);

                    break;

                case SLIDE_UP:

                    // Enter from Up
                    fragmentTransaction.setCustomAnimations(R.anim.slide_in_up,
                            R.anim.slide_out_up);

                    break;

                case SLIDE_LEFT:

                    // Enter from left
                    fragmentTransaction.setCustomAnimations(R.anim.slide_left,
                            R.anim.slide_out_left);

                    break;

                // Enter from right
                case SLIDE_RIGHT:
                    fragmentTransaction.setCustomAnimations(R.anim.slide_right,
                            R.anim.slide_out_right);

                    break;

                case FADE_IN:
                    fragmentTransaction.setCustomAnimations(R.anim.fade_in,
                            R.anim.fade_out);

                case FADE_OUT:
                    fragmentTransaction.setCustomAnimations(R.anim.fade_in,
                            R.anim.donot_move);

                    break;

                case SLIDE_IN_SLIDE_OUT:

                    fragmentTransaction.setCustomAnimations(R.anim.slide_left,
                            R.anim.slide_out_left);

                    break;

                default:
                    break;
            }
        }


        if (transitionStyle == null) {
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left,
                    R.anim.slide_out_right, R.anim.slide_in_right);
        }
        CURRENT_TAG = TAG;
        fragmentTransaction.replace(id, fragment);
        fragmentTransaction.addToBackStack(TAG);
        fragmentTransaction.commit();
    }

/*
    public static void switchContent(int id, String TAG, FragmentActivity baseActivity, AnimationType transitionStyle) {

        Fragment fragmentToReplace = null;
        FragmentManager fragmentManager = baseActivity
                .getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // If our current fragment is null, or the new fragment is different, we
        // need to change our current fragment
        if (CURRENT_TAG == null || !TAG.equals(CURRENT_TAG)) {
            if (transitionStyle != null) {
                switch (transitionStyle) {
                    case SLIDE_DOWN:
                        // Exit from down
                        transaction.setCustomAnimations(R.anim.slide_up,
                                R.anim.slide_down);

                        break;
                    case SLIDE_UP:
                        // Enter from Up
                        transaction.setCustomAnimations(R.anim.slide_in_up,
                                R.anim.slide_out_up);
                        break;
                    case SLIDE_LEFT:
                        // Enter from left
                        transaction.setCustomAnimations(R.anim.slide_left,
                                R.anim.slide_out_left);
                        break;
                    // Enter from right
                    case SLIDE_RIGHT:
                        transaction.setCustomAnimations(R.anim.slide_right,
                                R.anim.slide_out_right);
                        break;
                    case FADE_IN:
                        transaction.setCustomAnimations(R.anim.fade_in,
                                R.anim.fade_out);
                    case FADE_OUT:
                        transaction.setCustomAnimations(R.anim.fade_in,
                                R.anim.donot_move);
                        break;
                    case SLIDE_IN_SLIDE_OUT:
                        transaction.setCustomAnimations(R.anim.slide_left,
                                R.anim.slide_out_left);
                        break;
                    default:
                        break;
                }
            }
            if (transitionStyle == null) {
                transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left,
                        R.anim.slide_out_right, R.anim.slide_in_right);
            }
            // Try to find the fragment we are switching to
            Fragment fragment = fragmentManager.findFragmentByTag(TAG);

            // If the new fragment can't be found in the manager, create a new
            // one
            if (fragment == null) {
                if (TAG.equals(PLACE_ORDER_FRAGMENT)) {
                    fragmentToReplace = new PlaceOrderFragment();
                } else if (TAG.equals(SHOPPING_LIST_TAG)) {
                    fragmentToReplace = new MyCartFragment();
                } else if (TAG.equals(HOME_FRAGMENT)) {
                    fragmentToReplace = new HomeFragment();
                } else if (TAG.equals(FRAGMENT_MAIN_ORDERS)) {
                    fragmentToReplace = new FragmentMainOrders();
                } else if (TAG.equals(PRODUCT_OVERVIEW_FRAGMENT_TAG)) {
                    fragmentToReplace = new ProductListFragment();
                }
                else if (TAG.equals(OFFER_OVERVIEW_FRAGMENT_TAG)) {
                    fragmentToReplace = new OffersDetailsFragment();
                }
                else if (TAG.equals(SHOPPING_LIST_TAG)) {
                    fragmentToReplace = new MyCartFragment();
                }
            } else

            {
                if (TAG.equals(HOME_FRAGMENT)) {
                    // fragmentToReplace = (CategoryFragment) fragment;
                } else if (TAG.equals(SHOPPING_LIST_TAG)) {
                    fragmentToReplace = (MyCartFragment) fragment;
                } else if (TAG.equals(PRODUCT_OVERVIEW_FRAGMENT_TAG)) {
                    fragmentToReplace = (ProductListFragment) fragment;
                }

                else if (TAG.equals(OFFER_OVERVIEW_FRAGMENT_TAG)) {
                    fragmentToReplace = new OffersDetailsFragment();
                }
            }
        }

        CURRENT_TAG = TAG;

        // Replace our current fragment with the one we are changing to
        transaction.replace(id, fragmentToReplace, TAG);
        //transaction.addToBackStack(TAG);
        transaction.commit();


    }
*/

    public static void vibrate(Context context) {
        // Get instance of Vibrator from current Context and Vibrate for 400
        // milliseconds
        ((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE))
                .vibrate(100);
    }

    public static String getVersion(Context context) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            return String.valueOf(pInfo.versionCode) + " " + pInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            return "1.0.1";
        }
    }

    public static Typeface getFonts(Context context, String fontName) {
        Typeface typeface = TYPEFACE.get(fontName);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), "font/"
                    + fontName);
            TYPEFACE.put(fontName, typeface);
        }
        return typeface;
    }

    public enum AnimationType {
        SLIDE_LEFT, SLIDE_RIGHT, SLIDE_UP, SLIDE_DOWN, FADE_IN, SLIDE_IN_SLIDE_OUT, FADE_OUT
    }


    private static Toast mToast;

    public static void showStringToast(Context context, String stringMsg) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(context, stringMsg, Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void showResToast(Context context, int stringID) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(context, stringID, Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static String getLang() {
        String companyLang;
        companyLang = Constant.EN_LANG;

        /*if (isLangArabic()) {
            companyLang = Constant.AR_LANG;
        } else {
            companyLang = Constant.EN_LANG;
        }*/
        return companyLang;
    }

    public static boolean isLangArabic() {
        return Locale.getDefault().getDisplayLanguage().equals("العربية");
    }

    public static boolean isFieldEmpty(String field) {
        return TextUtils.isEmpty(field);
    }


}
