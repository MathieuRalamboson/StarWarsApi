package mathieu.r;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class DetailObjectFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Fragment ","onCreateView");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_object, container, false);

        return view;
    }
}
