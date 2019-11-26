package mathieu.r.Controller;

import mathieu.r.Model.VehicleReponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface VehicleApiService {

    @GET("vehicles/?format=json")
    Call<VehicleReponse> ListVehicle();
}
