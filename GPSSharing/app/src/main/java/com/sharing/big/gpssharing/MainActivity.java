package com.sharing.big.gpssharing;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private EditText edUserName, edPassword;
    private Button btnLogin, btnExit, btnRegister;
    private String responseServer = "";
    private String url = "http://d81bbbc5.ngrok.io/loginMobile";
    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_main);
        mapping();
        actionExit();
        login();
        register();

    }

    private void register() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.google.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        actionExit();
        login();
    }

    //ánh xạ
    private void mapping() {
        edUserName = (EditText) findViewById(R.id.edUserName);
        edPassword = (EditText) findViewById(R.id.edPassword);
        edUserName.setText("trankhanh@gmail.com");
        edPassword.setText("khanh1");

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnExit = (Button) findViewById(R.id.btnExit);
        btnRegister = (Button) findViewById(R.id.btnRegister);
    }

    private void actionExit() {
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Exit!");
                builder.setMessage("Are you sure?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }

    private void login() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidate()) {
                    try {
                        if (checkUser()) {
                            Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                            String email = edUserName.getText().toString();
                            intent.putExtra("account",responseServer);
                            startActivity(intent);
                        }
                    } catch (ExecutionException e) {

                    } catch (InterruptedException e) {

                    }
                }

            }
        });
    }

    private boolean checkUser() throws ExecutionException, InterruptedException {
        String email = edUserName.getText().toString();
        String password = edPassword.getText().toString();
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(email, password);

        responseServer = sendPostReqAsyncTask.get();

        if (responseServer.equalsIgnoreCase("Login failed")) {
            return false;
        }else if(responseServer.equalsIgnoreCase("")|| responseServer.contains("<html>"))
            return false;
        return true;
    }

    private boolean checkValidate() {
        boolean result = true;
        edUserName.setError(null);
        edPassword.setError(null);
        if (edUserName.getText().length() == 0) {
            edUserName.setError("Username can be blank!");
            edUserName.requestFocus();
            result = false;
        }
        else if (edPassword.getText().length() < 6) {
            edPassword.setError("Password must be longer than 6!");
            edPassword.requestFocus();
            result = false;
        }
        return result;
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
            nameValuePairList.add(new BasicNameValuePair("username", params[0]));
            nameValuePairList.add(new BasicNameValuePair("password", params[1]));

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
            if (result.equalsIgnoreCase("") || result.contains("<html>"))
                Toast.makeText(getApplicationContext(), "Connection failed", Toast.LENGTH_LONG).show();
            else if (!(result.equalsIgnoreCase("Login failed")))
                Toast.makeText(getApplicationContext(), "Login succeed!", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "You must have an account!", Toast.LENGTH_LONG).show();

            super.onPostExecute(result);
        }

    }


}
