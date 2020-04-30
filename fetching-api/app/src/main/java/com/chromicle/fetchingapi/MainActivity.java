package com.chromicle.fetchingapi;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private static final String API_URL = "https://codeforces.com/api/user.rating?handle=sandeshghanta";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLoaderManager().initLoader(0, null, this);
    }


    static ArrayList<ContestUtils> reverse(ArrayList<ContestUtils> contestList) {
        ArrayList<ContestUtils> newList = new ArrayList<>();
        for (int i = contestList.size() - 1; i >= 0; i--)
            newList.add(contestList.get(i));
        return newList;
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new LoaderRating(this);
    }


    @Override
    public void onLoaderReset(Loader<String> loader) {

    }


    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        ArrayList<ContestUtils> contestList = new ArrayList<ContestUtils>();
        try {
            JSONObject basejsonResponse = new JSONObject(data);
            JSONArray res = basejsonResponse.getJSONArray("result");
            for (int i = 0; i < res.length(); i++) {
                ContestUtils contest = new ContestUtils();
                contest.setContestName(res.getJSONObject(i).getString("contestName"));
                contest.setRank(Integer.parseInt(res.getJSONObject(i).getString("rank")));
                contest.setOldRating(Integer.parseInt(res.getJSONObject(i).getString("oldRating")));
                contest.setNewRating(Integer.parseInt(res.getJSONObject(i).getString("newRating")));
                contest.setChange(contest.getNewRating() - contest.getOldRating());
                contestList.add(contest);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        contestList = reverse(contestList);
        RecyclerView recyclerView = findViewById(R.id.contestsAppeared);
        ContestAdapter adapter = new ContestAdapter(this, contestList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
