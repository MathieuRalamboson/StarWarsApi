package mathieu.r.Controller;

import mathieu.r.Model.Response.VehiclesReponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VehiclesApiService {

    @GET("vehicles/?format=json")
    Call<VehiclesReponse> ListVehicles(@Query("page") int page);
}
