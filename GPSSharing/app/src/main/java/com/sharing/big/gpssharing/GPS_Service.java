package com.sharing.big.gpssharing;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by filipp on 6/16/2016.
 */
public class GPS_Service extends Service {
    SQLite_Database myDB;
    private LocationListener listener;
    private LocationManager locationManager;
    private String extras;
    private String url = "http://d81bbbc5.ngrok.io//addLocation";
    private int timeRequest = 10000; // miliseconds
    private int distance = 30; //meter



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        extras = intent.getStringExtra("Id");
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        myDB = new SQLite_Database(this);
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Intent i = new Intent("location_update");
                String longitude = "" + location.getLongitude();
                String latitude = "" + location.getLatitude();

                //hàm send location
                sendLocation(longitude, latitude);

                i.putExtra("long", location.getLongitude());
                i.putExtra("last", location.getLatitude());
                sendBroadcast(i);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        };

        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        //noinspection MissingPermission
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, timeRequest, distance, listener);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            //noinspection MissingPermission
            locationManager.removeUpdates(listener);
        }
    }

    private void sendLocation(String longitude, String latitude) {
        String userId = extras;
        System.out.println("BinhId"+userId);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        String date = sdf.format(new Date());
        sendLocationRequest(longitude, latitude,userId, date);
    }


    private void sendLocationRequest(final String longitude, final String latitude, final String userId, final String time) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {


                // Because we are not passing values over the URL, we should have a mechanism to pass the values that can be
                //uniquely separate by the other end.
                //To achieve that we use BasicNameValuePair
                //Things we need to pass with the POST request

                // We add the content that we want to pass with the POST request to as name-value pairs
                //Now we put those sending details to an ArrayList with type safe of NameValuePair
                List<List<NameValuePair>> listLocation = new ArrayList<>();

                List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();

                nameValuePairList.add(new BasicNameValuePair("longitude", longitude));
                nameValuePairList.add(new BasicNameValuePair("latitude", latitude));
                nameValuePairList.add(new BasicNameValuePair("userid", userId));
                nameValuePairList.add(new BasicNameValuePair("date", time));

                listLocation.add(nameValuePairList);

                int rowDB = myDB.numberOfRows();
                if (rowDB != 0) {
                    ArrayList<LocationNode> listDB = myDB.getAllCotacts();
                    for (LocationNode x : listDB) {
                        ArrayList<NameValuePair> nameValuePairListDB = new ArrayList<NameValuePair>();
                        nameValuePairListDB.add(new BasicNameValuePair("longitude", x.getLongitude()));
                        nameValuePairListDB.add(new BasicNameValuePair("latitude", x.getLatitude()));
                        nameValuePairListDB.add(new BasicNameValuePair("userid", x.getUserid()));
                        nameValuePairListDB.add(new BasicNameValuePair("date", x.getTime()));
                        listLocation.add(nameValuePairListDB);
                    }
                }


                try {
                    if (rowDB != 0) {
                        for (List<NameValuePair> x : listLocation) {
                            HttpClient httpClient = new DefaultHttpClient();
                            // In a POST request, we don't pass the values in the URL.
                            //Therefore we use only the web page URL as the parameter of the HttpPost argument
                            HttpPost httpPost = new HttpPost(url);
                            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(x);
                            httpPost.setEntity(urlEncodedFormEntity);
                            HttpResponse httpResponse = httpClient.execute(httpPost);
                            
                        }

                    } else {
                        HttpClient httpClient = new DefaultHttpClient();

                        // In a POST request, we don't pass the values in the URL.
                        //Therefore we use only the web page URL as the parameter of the HttpPost argument
                        HttpPost httpPost = new HttpPost(url);
                        // UrlEncodedFormEntity is an entity composed of a list of url-encoded pairs.
                        //This is typically useful while sending an HTTP POST request.
                        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairList);
                        // setEntity() hands the entity (here it is urlEncodedFormEntity) to the request.
                        httpPost.setEntity(urlEncodedFormEntity);
                        // HttpResponse is an interface just like HttpPost.
                        //Therefore we can't initialize them
                        HttpResponse httpResponse = httpClient.execute(httpPost);
                    }


                } catch (ClientProtocolException cpe) {
                    cpe.printStackTrace();
                } catch (IOException ioe) {
                    myDB.insertData(longitude, latitude, userId, time);
                    System.out.println("Nhập dữ liệu vào");
                }

                return "";
            }

            @Override
            protected void onPostExecute(String result) {

                super.onPostExecute(result);
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();
    }

    public class list implements NameValuePair {

        @Override
        public String getName() {
            return null;
        }

        @Override
        public String getValue() {
            return null;
        }
    }
}
