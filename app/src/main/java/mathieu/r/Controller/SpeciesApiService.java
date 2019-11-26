package mathieu.r.Controller;

import mathieu.r.Model.Response.SpeciesReponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SpeciesApiService {
    @GET("species/?format=json")
    Call<SpeciesReponse> ListSpecies();
}
