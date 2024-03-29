package mathieu.r.View.Activity;

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

import mathieu.r.Controller.FilmApiService;
import mathieu.r.Model.Film;
import mathieu.r.Model.Response.FilmReponse;
import mathieu.r.R;
import mathieu.r.View.Adaptater.ListFilmAdaptater;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmActivity extends AppCompatActivity {

    private static final String TAG = "FILM";
    private Retrofit retrofit;
    private RecyclerView recyclerView;

    private ListFilmAdaptater listFilmAdaptater;
    private ArrayList<Film> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);      // Configuration RecycleView
        listFilmAdaptater = new ListFilmAdaptater(this);
        recyclerView.setAdapter(listFilmAdaptater);
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
        FilmApiService service = retrofit.create(FilmApiService.class);
        Call<FilmReponse> FilmReponseCall = service.ListFilm();

        FilmReponseCall.enqueue(new Callback<FilmReponse>() {
            @Override
            public void onResponse(Call<FilmReponse> call, Response<FilmReponse> response) {
                if(response.isSuccessful()) {                               // Si il y a réponse utilisable/bonne forme

                    FilmReponse filmReponse = response.body();              // L'objet FilmRepnse est remplie par le JSON
                    ArrayList<Film> listFilm = filmReponse.getResults();    // On remplie ArrayList avec la réponse

                    listFilmAdaptater.add(listFilm);

                }else{                                                      // Sinon
                    Log.e(TAG, "Erreur Reponse : " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<FilmReponse> call, Throwable t) {
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
                listFilmAdaptater.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }
}
