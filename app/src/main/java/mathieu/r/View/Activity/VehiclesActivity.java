package mathieu.r.View.Activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import java.util.ArrayList;

import mathieu.r.Controller.VehiclesApiService;
import mathieu.r.Model.Vehicles;
import mathieu.r.Model.Response.VehiclesReponse;
import mathieu.r.R;
import mathieu.r.View.Adaptater.ListVehiclesAdaptater;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VehiclesActivity extends AppCompatActivity {

    private static final String TAG = "Vehicles";
    private Retrofit retrofit;
    private RecyclerView recyclerView;

    private ListVehiclesAdaptater listVehiclesAdaptater;
    private ArrayList<Vehicles> dataset;

    private int nbrPageParcouru;
    private boolean verif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);      // Configuration RecycleView
        listVehiclesAdaptater = new ListVehiclesAdaptater(this);
        recyclerView.setAdapter(listVehiclesAdaptater);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this,2); // Choix du nombre de colonne
        recyclerView.setLayoutManager(layoutManager);

        retrofit = new Retrofit.Builder()                                   //Configuration URL
                .baseUrl("https://swapi.co/api/")                           // Url request
                .addConverterFactory(GsonConverterFactory.create())         // Convertie la réponse
                .build();

        verif = false;
        nbrPageParcouru = 1;
        do {
            DataRequest(nbrPageParcouru);
            nbrPageParcouru++;
        } while (nbrPageParcouru < 20);
    }

    private void DataRequest(int nbrPageParcouru ) { // Appel vers Api
        VehiclesApiService service = retrofit.create(VehiclesApiService.class);
        Call<VehiclesReponse> VehiclesReponseCall = service.ListVehicles(nbrPageParcouru);

        VehiclesReponseCall.enqueue(new Callback<VehiclesReponse>() {
            @Override
            public void onResponse(Call<VehiclesReponse> call, Response<VehiclesReponse> response) {
                verif = true;
                if(response.isSuccessful()) {                               // Si il y a réponse utilisable/bonne forme

                    VehiclesReponse VehiclesReponse = response.body();              // L'objet VehiclesRepnse est remplie par le JSON
                    ArrayList<Vehicles> listVehicles = VehiclesReponse.getResults();    // On remplie ArrayList avec la réponse

                    listVehiclesAdaptater.add(listVehicles);

                    // Test récupération titre Vehicless
//                    for (int i = 0; i < listVehicles.size(); i++) {
//                        Vehicles Vehicles = listVehicles.get(i);
//                        Log.i(TAG, "Vehicles " + i + " : " + Vehicles.getTitle());
//                    }

                }else{                                                      // Sinon
                    Log.e(TAG, "Erreur Reponse : " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<VehiclesReponse> call, Throwable t) {
                verif = true;
                Log.e(TAG, "Erreur Pas de Reponse : " + t.getMessage());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listVehiclesAdaptater.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

}
