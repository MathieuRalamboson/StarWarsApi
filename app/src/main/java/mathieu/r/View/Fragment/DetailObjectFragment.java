package mathieu.r.View.Fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import mathieu.r.Model.Film;
import mathieu.r.R;

import static android.support.constraint.Constraints.TAG;


public class DetailObjectFragment extends Fragment {

    private static final String TAG = "DetailObjectFragment";
    private DetailObjectViewModel mViewModel;

    public static DetailObjectFragment newInstance() {
        return new DetailObjectFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) { // Instanciation object graphique vue etc
        View v = inflater.inflate(R.layout.detail_object_fragment, container, false);

        TextView textView = v.findViewById(R.id.titreView); // Declaration text sur layout
        ImageView imageView = v.findViewById(R.id.imageView); // Declaration image sur layout

        Bundle args = getArguments(); // On les recupere

        if(args.containsKey("film")){ // Differenciation Categorie : Film/People...

            Film film = (Film) args.getSerializable("film"); // On les serialise dans un object film
            if(getArguments() != null) { // Si les arguments recu ne sont pas null
                Log.d(TAG,"Lancement du fragment : " + film.getTitle());

                textView.setText(film.getTitle()); // Setter textLayout

            }
        }




        return v;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DetailObjectViewModel.class);
        // TODO: Use the ViewModel
    }

}