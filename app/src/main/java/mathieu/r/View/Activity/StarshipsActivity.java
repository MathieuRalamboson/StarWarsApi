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

import mathieu.r.Controller.StarshipsApiService;
import mathieu.r.Model.Starships;
import mathieu.r.Model.Response.StarshipsReponse;
import mathieu.r.R;
import mathieu.r.View.Adaptater.ListStarshipsAdaptater;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StarshipsActivity extends AppCompatActivity {

    private static final String TAG = "Starships";
    private Retrofit retrofit;
    private RecyclerView recyclerView;

    private ListStarshipsAdaptater listStarshipsAdaptater;
    private ArrayList<Starships> dataset;

    private int nbrPageParcouru;
    private boolean verif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);      // Configuration RecycleView
        listStarshipsAdaptater = new ListStarshipsAdaptater(this);
        recyclerView.setAdapter(listStarshipsAdaptater);
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
        StarshipsApiService service = retrofit.create(StarshipsApiService.class);
        Call<StarshipsReponse> StarshipsReponseCall = service.ListStarships(nbrPageParcouru);

        StarshipsReponseCall.enqueue(new Callback<StarshipsReponse>() {
            @Override
            public void onResponse(Call<StarshipsReponse> call, Response<StarshipsReponse> response) {
                verif = true;
                if(response.isSuccessful()) {                               // Si il y a réponse utilisable/bonne forme

                    StarshipsReponse StarshipsReponse = response.body();              // L'objet StarshipsRepnse est remplie par le JSON
                    ArrayList<Starships> listStarships = StarshipsReponse.getResults();    // On remplie ArrayList avec la réponse

                    listStarshipsAdaptater.add(listStarships);

                    // Test récupération titre Starshipss
//                    for (int i = 0; i < listStarships.size(); i++) {
//                        Starships Starships = listStarships.get(i);
//                        Log.i(TAG, "Starships " + i + " : " + Starships.getTitle());
//                    }

                }else{                                                      // Sinon
                    Log.e(TAG, "Erreur Reponse : " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<StarshipsReponse> call, Throwable t) {
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
                listStarshipsAdaptater.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

}
