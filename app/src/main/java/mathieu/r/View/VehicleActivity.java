package mathieu.r.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import mathieu.r.Controller.VehicleApiService;
import mathieu.r.Model.Vehicle;
import mathieu.r.Model.VehicleReponse;
import mathieu.r.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VehicleActivity extends AppCompatActivity {

    private static final String TAG = "Vehicle";
    private Retrofit retrofit;
    private RecyclerView recyclerView;

    private ListVehicleAdaptater listVehicleAdaptater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);      // Configuration RecycleView
        listVehicleAdaptater = new ListVehicleAdaptater(this);
        recyclerView.setAdapter(listVehicleAdaptater);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2); // Choix du nombre de colonne
        recyclerView.setLayoutManager(layoutManager);


        retrofit = new Retrofit.Builder()                                   //Configuration URL
                .baseUrl("https://swapi.co/api/")                           // Url request
                .addConverterFactory(GsonConverterFactory.create())         // Convertie la réponse
                .build();
        DataRequest();
    }

    private void DataRequest() { // Appel vers Api
        VehicleApiService service = retrofit.create(VehicleApiService.class);
        Call<VehicleReponse> VehicleReponseCall = service.ListVehicle();

        VehicleReponseCall.enqueue(new Callback<VehicleReponse>() {
            @Override
            public void onResponse(Call<VehicleReponse> call, Response<VehicleReponse> response) {
                if(response.isSuccessful()) {                               // Si il y a réponse utilisable/bonne forme

                    VehicleReponse VehicleReponse = response.body();              // L'objet VehicleRepnse est remplie par le JSON
                    ArrayList<Vehicle> listVehicle = VehicleReponse.getResults();    // On remplie ArrayList avec la réponse

                    listVehicleAdaptater.add(listVehicle);

                    // Test récupération titre Vehicles
//                    for (int i = 0; i < listVehicle.size(); i++) {
//                        Vehicle Vehicle = listVehicle.get(i);
//                        Log.i(TAG, "Vehicle " + i + " : " + Vehicle.getTitle());
//                    }

                }else{                                                      // Sinon
                    Log.e(TAG, "Erreur Reponse : " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<VehicleReponse> call, Throwable t) {
                Log.e(TAG, "Erreur Pas de Reponse : " + t.getMessage());
            }
        });

    }
}
