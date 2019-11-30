package mathieu.r.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import mathieu.r.Model.Film;
import mathieu.r.R;
import mathieu.r.View.Fragment.DetailObjectFragment;

public class DetailObjectActivity extends AppCompatActivity {
    private static final String TAG = "DetailObjectActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_object_activity);

        //Recuperer les data de l'activity parent
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        Film film = (Film) bundle.getSerializable("film"); //Recuperation de l'object film

        Log.d(TAG,"Lancement de l'activity : " + film.getTitle());
        Toast.makeText(this,"Clicked on film : " + film.getTitle() ,Toast.LENGTH_SHORT).show();

        if (savedInstanceState == null) { // Lancement du fragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, DetailObjectFragment.newInstance())
                    .commitNow();
        }
    }
}
