package com.example.dell.racing.DoAPI;

import android.os.AsyncTask;

import com.example.dell.racing.DataModel.Score;
import com.example.dell.racing.DataModel.User;
import com.example.dell.racing.Instance;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.LinkedList;

public class DoGetScore extends AsyncTask<String,Integer,Integer> {
    @Override
    protected Integer doInBackground(String... strings) {

        String urlString = strings[0];
        URL url=null;
        HttpURLConnection httpURLConnection ;
        InputStream inputStream =null;
        String result="" ;

        try {
            url = new URL(urlString);
            httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);

            httpURLConnection.connect();

            inputStream = httpURLConnection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));

            result = reader.readLine();


            JSONArray jsonArray = new JSONArray(result);
            JSONObject jsonObject ;
            //userAccounts = new LinkedList<>();
            //publishProgress(jsonArray.length());
            Instance.topTen.clear();
            for (int i=0; i<jsonArray.length(); i++){
                jsonObject=jsonArray.getJSONObject(i);
                //userAccounts.add(new User(jsonObject.getString("Email"),jsonObject.getString("Name")));
                Instance.topTen.add(new Score(jsonObject.getString("UserAccount"),jsonObject.getInt("Score1")));
            }

            Instance.bestScore = Instance.topTen.get(0).getScore();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
