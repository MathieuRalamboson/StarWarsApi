package mathieu.r.View.Activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import mathieu.r.Controller.PeopleApiService;
import mathieu.r.Model.People;
import mathieu.r.Model.Response.PeopleReponse;
import mathieu.r.R;
import mathieu.r.View.Adaptater.ListPeopleAdaptater;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeopleActivity extends AppCompatActivity {

    private static final String TAG = "People";
    private Retrofit retrofit;
    private RecyclerView recyclerView;

    private ListPeopleAdaptater listPeopleAdaptater;

    private int nbrPageParcouru;
    private boolean verif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);      // Configuration RecycleView
        listPeopleAdaptater = new ListPeopleAdaptater(this);
        recyclerView.setAdapter(listPeopleAdaptater);
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
        PeopleApiService service = retrofit.create(PeopleApiService.class);
        Call<PeopleReponse> PeopleReponseCall = service.ListPeople(nbrPageParcouru);

        PeopleReponseCall.enqueue(new Callback<PeopleReponse>() {
            @Override
            public void onResponse(Call<PeopleReponse> call, Response<PeopleReponse> response) {
                verif = true;
                if(response.isSuccessful()) {                               // Si il y a réponse utilisable/bonne forme

                    PeopleReponse PeopleReponse = response.body();              // L'objet PeopleRepnse est remplie par le JSON
                    ArrayList<People> listPeople = PeopleReponse.getResults();    // On remplie ArrayList avec la réponse

                    listPeopleAdaptater.add(listPeople);

                    // Test récupération titre Peoples
//                    for (int i = 0; i < listPeople.size(); i++) {
//                        People People = listPeople.get(i);
//                        Log.i(TAG, "People " + i + " : " + People.getTitle());
//                    }

                }else{                                                      // Sinon
                    Log.e(TAG, "Erreur Reponse : " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PeopleReponse> call, Throwable t) {
                verif = true;
                Log.e(TAG, "Erreur Pas de Reponse : " + t.getMessage());
            }
        });

    }
}
