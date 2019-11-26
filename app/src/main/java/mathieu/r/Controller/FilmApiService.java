package mathieu.r.Controller;

import mathieu.r.Model.Response.FilmReponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FilmApiService {

    @GET("films/?format=json")
    Call<FilmReponse> ListFilm();
}
