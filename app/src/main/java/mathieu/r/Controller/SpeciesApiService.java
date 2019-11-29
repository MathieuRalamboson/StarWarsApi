package mathieu.r.Controller;

import mathieu.r.Model.Response.SpeciesReponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpeciesApiService {
    @GET("species/?format=json")
    Call<SpeciesReponse> ListSpecies(@Query("page") int page);
}
