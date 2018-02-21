package com.club.jalvara2.polarclub2;

import android.app.FragmentManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.wear.ambient.AmbientMode;
import android.support.wear.widget.drawer.WearableNavigationDrawerView;
import android.support.wearable.activity.WearableActivity;

import com.club.jalvara2.polarclub2.fragments.HeartFragment;
import com.club.jalvara2.polarclub2.fragments.SesionFragment;
import com.club.jalvara2.polarclub2.utils.DrawerItem;

import java.util.ArrayList;

public class MainActivity extends WearableActivity implements
        AmbientMode.AmbientCallbackProvider,
        WearableNavigationDrawerView.OnItemSelectedListener{

    public final static int ITEM_MENU_STEP_COUNTER = 0;
    public final static int ITEM_MENU_HEART_RATE = 1;

    private WearableNavigationDrawerView mNavigationDrawer;
    private ArrayList<DrawerItem> mDrawerItem;
    private int mSelectedScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerItem = initializeScreenSystem();
        mSelectedScreen = 0;

        SesionFragment hf = new SesionFragment();
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, hf).commit();

        mNavigationDrawer = (WearableNavigationDrawerView) findViewById(R.id.topNavigation);
        mNavigationDrawer.setAdapter(
                new WearableNavigationDrawerView.WearableNavigationDrawerAdapter(){

                    @Override
                    public int getCount() {

                        return mDrawerItem.size();
                    }

                    @Override
                    public String getItemText(int pos) {
                        return mDrawerItem.get(pos).getName();
                    }

                    @Override
                    public Drawable getItemDrawable(int pos) {

                        String navigationIcon = mDrawerItem.get(pos).getNavigationIcon();
                        int drawableNavigationIconId = getResources().getIdentifier(navigationIcon, "drawable", getPackageName());
                        return getDrawable(drawableNavigationIconId);
                    }
                });
        mNavigationDrawer.getController().peekDrawer();
        mNavigationDrawer.addOnItemSelectedListener(this);
    }


    /*ATENCION A LA PALABRA USADA DE DYFYTYPE*/
    private ArrayList<DrawerItem> initializeScreenSystem() {
        ArrayList<DrawerItem> screens = new ArrayList<DrawerItem>();
        String[] FragmentArrayNames = getResources().getStringArray(R.array.screens);
        System.out.println("Mis valores son: " + FragmentArrayNames.length);

        for (int i = 0; i < FragmentArrayNames.length; i++) {
            String fragmentName = FragmentArrayNames[i];
            System.out.println("Nombre del fragment es: " + fragmentName);
            int FragmentResourceId =
                    getResources().getIdentifier(fragmentName, "array", getPackageName());
            String[] fragmentInformation = getResources().getStringArray(FragmentResourceId);
            System.out.println("numero de id " + FragmentResourceId);
            screens.add(new DrawerItem(fragmentInformation[0], fragmentInformation[1]));
        }
        return screens;
    }


    @Override
    public void onItemSelected(int pos) {
        mSelectedScreen = pos;
        switch (mSelectedScreen){
            case ITEM_MENU_HEART_RATE:
                HeartFragment hf = new HeartFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, hf)
                        .commit();
                break;
        }
    }

    @Override
    public AmbientMode.AmbientCallback getAmbientCallback() {return null;}
}
