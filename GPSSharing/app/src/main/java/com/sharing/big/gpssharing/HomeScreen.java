package com.sharing.big.gpssharing;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HomeScreen extends FragmentActivity implements OnMapReadyCallback {


    private TextView txtEmail;
    private Button btnStart;
    private BroadcastReceiver broadcastReceiver;
    private Bundle extras;
    private GoogleMap mMap;
    private Account account = null;
    private ListAccount listAccount = null;
    private String url = "http://d81bbbc5.ngrok.io/getListFriend";


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        mapping();

        extras = getIntent().getExtras();


        try {
            account = new Account(extras.getString("account"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(account.getId());
        try {
            String listFriend = sendPostReqAsyncTask.get();
            listAccount = new ListAccount(listFriend);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem history = new PrimaryDrawerItem().withIdentifier(1).withName("Location History").withIcon(FontAwesome.Icon.faw_list);
        PrimaryDrawerItem listFriend = new PrimaryDrawerItem().withIdentifier(1).withName("List Friends").withIcon(FontAwesome.Icon.faw_list_ul);
        PrimaryDrawerItem shareLocation = new PrimaryDrawerItem().withIdentifier(1).withName("Share Location").withIcon(FontAwesome.Icon.faw_location_arrow);
        SecondaryDrawerItem closeItem = new SecondaryDrawerItem().withIdentifier(2).withName("Logout").withIcon(GoogleMaterial.Icon.gmd_exit_to_app);
        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName(account.getFirstname() + " " + account.getLastname()).withEmail(account.getEmail()).withIcon(R.drawable.avatar1)
                )
                .build();

        final List<IDrawerItem> listDrawerItem = new ArrayList<IDrawerItem>();
        listDrawerItem.add(new PrimaryDrawerItem().withName(account.getDateofbirth())
                .withIcon(GoogleMaterial.Icon.gmd_cake));
        listDrawerItem.add(new PrimaryDrawerItem().withName(account.getUsername()).withIcon(FontAwesome.Icon.faw_user_o));
        listDrawerItem.add(new PrimaryDrawerItem().withName(account.getGender()).withIcon(FontAwesome.Icon.faw_venus_mars));
        listDrawerItem.add(new PrimaryDrawerItem().withName(account.getAddress()).withIcon(FontAwesome.Icon.faw_home));
        listDrawerItem.add(new PrimaryDrawerItem().withName(account.getPhone()).withIcon(FontAwesome.Icon.faw_mobile));

        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withActivity(this)
                .withDrawerItems(listDrawerItem)
                .addDrawerItems(
                        new DividerDrawerItem(),
                        listFriend, //7
                        shareLocation,//8
                        new DividerDrawerItem(),
                        closeItem
                ).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (position == 10) {
                            new MaterialDialog.Builder(HomeScreen.this)
                                    .content("Do you want to logout")
                                    .positiveText("Yes")
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                            Intent i = new Intent(getApplicationContext(), GPS_Service.class);
                                            stopService(i);
                                            onBackPressed();
                                        }
                                    })
                                    .negativeText("Cancel")
                                    .show();
                        } else if (position == 7) {
                            SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
                            sendPostReqAsyncTask.execute(account.getId());
                            try {
                                String listFriend = sendPostReqAsyncTask.get();
                                listAccount = new ListAccount(listFriend);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            List<String> listFriendItem = new ArrayList<String>();
                            for (Account x : listAccount.list) {
                                listFriendItem.add(x.getUsername());
                            }

                            new MaterialDialog.Builder(HomeScreen.this)
                                    .title("List Friend")
                                    .items(listFriendItem)
                                    .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                                        @Override
                                        public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {

                                            return false;
                                        }
                                    })
                                    .positiveText("Unfriend")
                                    .negativeText("Close")

                                    .show();

                        }
                        return false;
                    }
                })
                .withCloseOnClick(true)
                .build();

        if (!runtime_permissions()) {
            enable_buttons();
        }
    }

    private void mapping() {
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        btnStart = (Button) findViewById(R.id.btnStart);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    private void enable_buttons() {

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeScreen.this, GPS_Service.class);
                i.putExtra("Id", account.getId());
                HomeScreen.this.startService(i);
                btnStart.setEnabled(false);

            }
        });

    }

    private boolean runtime_permissions() {
        if (Build.VERSION.SDK_INT >= 23
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);

            return true;
        }
        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                enable_buttons();
            } else {
                runtime_permissions();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (broadcastReceiver == null) {
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                            new LatLng((double) intent.getExtras().get("last"), (double) intent.getExtras().get("long")), 13));

                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(new LatLng((double) intent.getExtras().get("last"), (double) intent.getExtras().get("long")))      // Sets the center of the map to location user
                            .zoom(12)                   // Sets the zoom
                            .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                            .build();                   // Creates a CameraPosition from the builder
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
            };
        }
        registerReceiver(broadcastReceiver, new IntentFilter("location_update"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //noinspection MissingPermission
        mMap.setMyLocationEnabled(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new MaterialDialog.Builder(HomeScreen.this)
                    .content("Do you want to logout")
                    .positiveText("Yes")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            Intent i = new Intent(getApplicationContext(), GPS_Service.class);
                            stopService(i);
                            onBackPressed();
                        }
                    })
                    .negativeText("Cancel")
                    .show();
        }
        return super.onKeyDown(keyCode, event);
    }

    class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpClient httpClient = new DefaultHttpClient();

            // In a POST request, we don't pass the values in the URL.
            //Therefore we use only the web page URL as the parameter of the HttpPost argument
            HttpPost httpPost = new HttpPost(url);

            // Because we are not passing values over the URL, we should have a mechanism to pass the values that can be
            //uniquely separate by the other end.
            //To achieve that we use BasicNameValuePair
            //Things we need to pass with the POST request

            // We add the content that we want to pass with the POST request to as name-value pairs
            //Now we put those sending details to an ArrayList with type safe of NameValuePair
            List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
            nameValuePairList.add(new BasicNameValuePair("Requestuserid", params[0]));

            try {
                // UrlEncodedFormEntity is an entity composed of a list of url-encoded pairs.
                //This is typically useful while sending an HTTP POST request.
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairList);

                // setEntity() hands the entity (here it is urlEncodedFormEntity) to the request.
                httpPost.setEntity(urlEncodedFormEntity);

                try {
                    // HttpResponse is an interface just like HttpPost.
                    //Therefore we can't initialize them
                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    // According to the JAVA API, InputStream constructor do nothing.
                    //So we can't initialize InputStream although it is not an interface
                    InputStream inputStream = httpResponse.getEntity().getContent();

                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    StringBuilder stringBuilder = new StringBuilder();

                    String bufferedStrChunk = null;

                    while ((bufferedStrChunk = bufferedReader.readLine()) != null) {
                        stringBuilder.append(bufferedStrChunk);
                    }

                    return stringBuilder.toString();

                } catch (ClientProtocolException cpe) {
                    cpe.printStackTrace();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

            } catch (UnsupportedEncodingException uee) {
                uee.printStackTrace();
            }

            return "";

        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }

    }
}
