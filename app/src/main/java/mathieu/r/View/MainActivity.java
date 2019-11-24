package mathieu.r.View;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import mathieu.r.Controller.FilmApiService;
import mathieu.r.Model.Film;
import mathieu.r.Model.FilmReponse;
import mathieu.r.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "FILM";
    private Retrofit retrofit;
    private Object FilmApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Début Url
        retrofit = new Retrofit.Builder()
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

                    for (int i = 0; i < listFilm.size(); i++) {
                        Film film = listFilm.get(i);
                        Log.i(TAG, "Film " + i + " : " + film.getTitle());
                    }

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
}
