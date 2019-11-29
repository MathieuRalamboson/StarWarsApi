package mathieu.r.Controller;

import mathieu.r.Model.Response.StarshipsReponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StarshipsApiService {

    @GET("starships/?format=json")
    Call<StarshipsReponse> ListStarships(@Query("page") int page);
}
