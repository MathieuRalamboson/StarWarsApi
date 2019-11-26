package mathieu.r.Controller;

import mathieu.r.Model.StarshipsReponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface StarshipsApiService {

    @GET("starships/?format=json")
    Call<StarshipsReponse> ListStarships();
}
