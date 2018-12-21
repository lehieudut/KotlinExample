package jp.bizloco.kotlinexample.service;


import jp.bizloco.kotlinexample.service.response.ImagesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {
    @GET()
    Call<ImagesResponse> getQuotes(@Url String url);
}
