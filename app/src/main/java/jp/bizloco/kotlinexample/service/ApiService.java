package jp.bizloco.kotlinexample.service;


import jp.bizloco.kotlinexample.service.response.QuotesResponse;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {
    @GET()
    Call<QuotesResponse> getQuotes(@Url String url);
}
