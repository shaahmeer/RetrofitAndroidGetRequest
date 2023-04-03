package com.example.jsonplaceholderapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView text;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())

                .build();

      Api apiclass = retrofit.create(Api.class);
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("userId","10");
        stringMap.put("_order","desc");
        stringMap.put("_sort","id");

        Call<List<Model>> listCall = apiclass.model(stringMap);
        Call<List<Comments>> listCall1 = apiclass.model2("https://jsonplaceholder.typicode.com/comments?postId=1&_sort=id&_order=desc");
        listCall.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
            if(!response.isSuccessful()){
                text.setText("Code" +response.code());
            }
            List<Model> model =response.body();
                for (Model model1: model ) {


            String content = "";


            content += "userID: " + model1.getUserId() + "\n";
            content += "Id: " + model1.getId() + "\n";
            content += "email: " + model1.getBody()+ "\n";
            content += "name: " + model1.getTitle() + "\n";
            content += "Body: " + model1.getBody() + "\n";

            //text.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {

            }
        });

        listCall1.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                if(!response.isSuccessful()){
                    text.setText(response.code());
                }
                List<Comments> commentsList = response.body();

                for (Comments comments:commentsList) {

                    String tv ="";

                    tv += " PostID: "  + comments.getPostId()+"\n";
                    tv += "Id: " + comments.getId() + "\n";
                    tv += " name: " +comments.getName() + "\n";
                    tv += "Email: " + comments.getName() + "\n";
                    tv += "Body: " + comments.getBody() + "\n";

                    text.append(tv);


                }
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {

            }
        });

    }
}