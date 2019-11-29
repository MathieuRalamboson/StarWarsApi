package mathieu.r.Controller;

import mathieu.r.Model.Response.PeopleReponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PeopleApiService {

    @GET("people/?format=json")
    Call<PeopleReponse> ListPeople(@Query("page") int page);

}
