package mathieu.r.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import mathieu.r.Controller.PlanetsApiService;
import mathieu.r.Model.Planets;
import mathieu.r.Model.PlanetsReponse;
import mathieu.r.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlanetsActivity extends AppCompatActivity {

    private static final String TAG = "Planets";
    private Retrofit retrofit;
    private RecyclerView recyclerView;

    private ListPlanetsAdaptater listPlanetsAdaptater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);      // Configuration RecycleView
        listPlanetsAdaptater = new ListPlanetsAdaptater(this);
        recyclerView.setAdapter(listPlanetsAdaptater);
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
        PlanetsApiService service = retrofit.create(PlanetsApiService.class);
        Call<PlanetsReponse> PlanetsReponseCall = service.ListPlanets();

        PlanetsReponseCall.enqueue(new Callback<PlanetsReponse>() {
            @Override
            public void onResponse(Call<PlanetsReponse> call, Response<PlanetsReponse> response) {
                if(response.isSuccessful()) {                               // Si il y a réponse utilisable/bonne forme

                    PlanetsReponse PlanetsReponse = response.body();              // L'objet PlanetsRepnse est remplie par le JSON
                    ArrayList<Planets> listPlanets = PlanetsReponse.getResults();    // On remplie ArrayList avec la réponse

                    listPlanetsAdaptater.add(listPlanets);

                    // Test récupération titre Planetss
                    for (int i = 0; i < listPlanets.size(); i++) {
                        Planets Planets = listPlanets.get(i);
                        Log.i(TAG, "Planets " + i + " : " + Planets.getName());
                    }

                }else{                                                      // Sinon
                    Log.e(TAG, "Erreur Reponse : " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PlanetsReponse> call, Throwable t) {
                Log.e(TAG, "Erreur Pas de Reponse : " + t.getMessage());
            }
        });

    }
}
