package com.example.dell.racing.DoAPI;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

import com.example.dell.racing.DataModel.User;
import com.example.dell.racing.Instance;
import com.example.dell.racing.MainActivity;
import com.example.dell.racing.R;

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

public class DoGetUser extends AsyncTask<String,Integer,Integer> {
    LinkedList<User> userAccounts;
    private ProgressDialog progressDialog;
    private Activity context;

    public DoGetUser(Activity context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        showProgressDialog();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if(values[0]==1){
            Instance.user=userAccounts.get(0);
            progressDialog.cancel();
            TextView txtvCanhBao = context.findViewById(R.id.txtvSignInCanhBao);
            txtvCanhBao.setText(Instance.user.getEmail());
            //mở màn hình vào app.
            Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
            context.startActivity(intent);
            context.finish();
        }else if(values[0]==0){
            TextView txtvCanhBao = context.findViewById(R.id.txtvSignInCanhBao);
            txtvCanhBao.setText("tên tài khoản hoặc mật khẩu không đúng");
        }
    }

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
            userAccounts = new LinkedList<>();
            publishProgress(jsonArray.length());
            for (int i=0; i<jsonArray.length(); i++){
                jsonObject=jsonArray.getJSONObject(i);
                userAccounts.add(new User(jsonObject.getString("Email"),jsonObject.getString("Name"),jsonObject.getString("Password")));
            }

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
    private void showProgressDialog(){
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }
}
