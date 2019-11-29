package mathieu.r.View;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import mathieu.r.Controller.SpeciesApiService;
import mathieu.r.Model.Species;
import mathieu.r.Model.Response.SpeciesReponse;
import mathieu.r.R;
import mathieu.r.View.Adaptater.ListSpeciesAdaptater;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpeciesActivity extends AppCompatActivity {

    private static final String TAG = "Species";
    private Retrofit retrofit;
    private RecyclerView recyclerView;

    private ListSpeciesAdaptater listSpeciesAdaptater;

    private int nbrPageParcouru;
    private boolean verif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);      // Configuration RecycleView
        listSpeciesAdaptater = new ListSpeciesAdaptater(this);
        recyclerView.setAdapter(listSpeciesAdaptater);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this,2); // Choix du nombre de colonne
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // Check si il y a scroll , si oui on passe à la page suivante
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) { //Check nbr d'object deja vu
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if(verif) { //Si on touche la fin de la page , plus besoin de verif , passage a une nouvelle page
                        if( (visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, "C'est la fin de la page ! ");
                            verif = false;
                            nbrPageParcouru ++;
                            DataRequest(nbrPageParcouru);
                        }
                    }

                }

            }
        });


        retrofit = new Retrofit.Builder()                                   //Configuration URL
                .baseUrl("https://swapi.co/api/")                           // Url request
                .addConverterFactory(GsonConverterFactory.create())         // Convertie la réponse
                .build();

        verif = false;
        nbrPageParcouru = 1;
        DataRequest(nbrPageParcouru);
    }

    private void DataRequest(int nbrPageParcouru ) { // Appel vers Api
        SpeciesApiService service = retrofit.create(SpeciesApiService.class);
        Call<SpeciesReponse> SpeciesReponseCall = service.ListSpecies(nbrPageParcouru);

        SpeciesReponseCall.enqueue(new Callback<SpeciesReponse>() {
            @Override
            public void onResponse(Call<SpeciesReponse> call, Response<SpeciesReponse> response) {
                verif = true;
                if(response.isSuccessful()) {                               // Si il y a réponse utilisable/bonne forme

                    SpeciesReponse SpeciesReponse = response.body();              // L'objet SpeciesRepnse est remplie par le JSON
                    ArrayList<Species> listSpecies = SpeciesReponse.getResults();    // On remplie ArrayList avec la réponse

                    listSpeciesAdaptater.add(listSpecies);

                    // Test récupération titre Speciess
//                    for (int i = 0; i < listSpecies.size(); i++) {
//                        Species Species = listSpecies.get(i);
//                        Log.i(TAG, "Species " + i + " : " + Species.getTitle());
//                    }

                }else{                                                      // Sinon
                    Log.e(TAG, "Erreur Reponse : " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<SpeciesReponse> call, Throwable t) {
                verif = true;
                Log.e(TAG, "Erreur Pas de Reponse : " + t.getMessage());
            }
        });

    }
}
