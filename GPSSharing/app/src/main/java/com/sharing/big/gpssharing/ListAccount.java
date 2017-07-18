package com.sharing.big.gpssharing;

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by binhn on 16/07/2017.
 */

public class ListAccount {
    List<Account> list = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.N)
    public ListAccount(String Json) throws JSONException {
        JSONArray jsonArray = new JSONArray(Json);
        for(int i = 0 ; i  <jsonArray.length();i++){
            list.add(new Account(jsonArray.getJSONObject(i)+""));
        }
    }
}
