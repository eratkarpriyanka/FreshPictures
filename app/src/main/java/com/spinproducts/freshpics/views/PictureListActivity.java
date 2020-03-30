package com.spinproducts.freshpics.views;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.spinproducts.freshpics.R;
import com.spinproducts.freshpics.models.Picture;
import com.spinproducts.freshpics.models.SpinApiResponse;
import com.spinproducts.freshpics.network.ISpinApi;
import com.spinproducts.freshpics.network.RestClient;
import com.spinproducts.freshpics.uiutils.GridSpacingItemDecoration;
import com.spinproducts.freshpics.uiutils.PictureAdapter;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PictureListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView tvEmptyView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setViews();
    }

    public void setViews(){

       recyclerView = findViewById(R.id.recycler_view);
       tvEmptyView = findViewById(R.id.empty_list);
       progressBar = findViewById(R.id.progress_bar);
       getPics();
    }

    /**
     * If picture list is empty set this view
     */
    private void setEmptyView() {

        tvEmptyView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    public void getPics() {

        showProgressBar();
        ISpinApi iSpinApi = RestClient.createService();
        // handle the reponse in SpinApiCallback
        SpinApiCallback spinApiCallBack = new SpinApiCallback();
        // call api to get picture list
        iSpinApi.getPictureList().enqueue(spinApiCallBack);
    }

    public class SpinApiCallback implements Callback<SpinApiResponse>{

        @Override
        public void onResponse(Call<SpinApiResponse> call, Response<SpinApiResponse> response) {

            hideProgressBar();
            if(response == null && !response.isSuccessful()){
                setEmptyView();
            }
            SpinApiResponse spinApiResponse = response.body();
            ArrayList<Picture> pictureList = spinApiResponse.getPictureArrayList();

            if(pictureList == null || pictureList.size()<=0){
                setEmptyView();
            }else{
                loadView(pictureList);
            }
        }

        @Override
        public void onFailure(Call<SpinApiResponse> call, Throwable throwable) {

            hideProgressBar();
            setEmptyView();
            Log.e("SPIN","Error " + throwable.getMessage());
        }
    }

    private void loadView(ArrayList<Picture> pictureList) {

        tvEmptyView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        PictureAdapter adapter = new PictureAdapter(this,pictureList);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(this, 2, true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void showProgressBar() {
        if( progressBar!=null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgressBar() {
        if( progressBar!=null) {
            progressBar.setVisibility(View.GONE);
        }
    }

}
