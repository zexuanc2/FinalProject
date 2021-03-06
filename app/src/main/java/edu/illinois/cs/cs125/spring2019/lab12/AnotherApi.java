package edu.illinois.cs.cs125.spring2019.lab12;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AnotherApi extends AsyncTask<Void, Void, Void> {
    private String firstParsed, secondParsed;
    String data = "";
    @Override
    protected Void doInBackground(final Void... voids) {
        try {
            URL url = new URL("https://api.myjson.com/bins/lewzk");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String cnmgp = "";
            while (cnmgp != null) {
                cnmgp = bufferedReader.readLine();
                data = data + cnmgp;
            }

            JSONArray jA = new JSONArray(data);
            JSONObject jO = (JSONObject) jA.get(2);
            firstParsed = "status: " + jO.getString("status");
            secondParsed = "risk: " + jO.getString("risk");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(final Void aVoid) {
        super.onPostExecute(aVoid);

        SecondActivity.fetchedData.setText(this.firstParsed + ".  " + this.secondParsed);
    }
}