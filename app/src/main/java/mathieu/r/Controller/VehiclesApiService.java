package mathieu.r.Controller;

import mathieu.r.Model.VehiclesReponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface VehiclesApiService {

    @GET("vehicles/?format=json")
    Call<VehiclesReponse> ListVehicles();
}
