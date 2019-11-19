package com.example.retrofitpractice5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private final String BASEURL = "http://jsonplaceholder.typicode.com/";
    private TextView textViewResult;

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        createPost();
    }

    private void createPost() {
        // 첫 번째 방식에 사용한 내용
/*
        Post post = new Post(23, "new title", "new text");
        Call<Post> call = jsonPlaceHolderApi.createPost(post);
*/

        // 두 번째 방식(@Field) 에서 사용한 내용
/*        Call<Post> call = jsonPlaceHolderApi.createPost(30
                , "formUrlEncoded 적용한 제목 필드 부분"
                , "formUrlEncoded 적용한 내용 필드 부분");
                */

        // 세 번째 방식(@FieldMap") 에서 사용한 내용
        Map<String, String> map = new HashMap<>();
        map.put("userId", "25");
        map.put("title", "FieldMap 적용한 제목 필드 부분");
        map.put("body", "FieldMap 적용한 제목 필드 부분");

        Call<Post> call = jsonPlaceHolderApi.createPost(map);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("code: " + response.code());
                    return;
                }

                Post postResponse = response.body();

                String content = "";
                content += "Code : " + response.code() + "\n";
                content += "Id: " + postResponse.getId() + "\n";
                content += "User Id: " + postResponse.getUserId() + "\n";
                content += "Title: " + postResponse.getTitle() + "\n";
                content += "Text: " + postResponse.getText() + "\n";

                textViewResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
