package mathieu.r.Controller;

import mathieu.r.Model.Response.PlanetsReponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlanetsApiService {

    @GET("planets/?format=json")
    Call<PlanetsReponse> ListPlanets(@Query("page") int page);
}
