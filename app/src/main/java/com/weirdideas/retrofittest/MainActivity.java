package com.weirdideas.retrofittest;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        ArrayAdapter<Question> arrayAdapter =
                new ArrayAdapter<Question>(this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        new ArrayList<Question>());
        setListAdapter(arrayAdapter);
        setProgressBarIndeterminateVisibility(true);
        setProgressBarVisibility(true);

        Call<StackOverflowQuestions> call = RestApiClient.getInstance().getApi().loadQuestion("ios");

        call.enqueue(new Callback<StackOverflowQuestions>() {
            @Override
            public void onResponse(Response<StackOverflowQuestions> response, Retrofit retrofit) {
                setProgressBarIndeterminateVisibility(false);
                ArrayAdapter<Question> adapter = (ArrayAdapter<Question>) getListAdapter();
                adapter.clear();
                adapter.addAll(response.body().items);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
