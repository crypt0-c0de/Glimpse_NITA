package nita.krishna.glimpse_nita.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import nita.krishna.glimpse_nita.R;
import nita.krishna.glimpse_nita.SliderAdapter;


public class HomeFragment extends Fragment {

    private int[] images;
    private SliderAdapter adapter;
    private SliderView sliderView;
    private ImageView map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        sliderView = view.findViewById(R.id.sliderView);
        images = new int[] {R.drawable.sl1,R.drawable.sl2,R.drawable.sl3,R.drawable.sl3,R.drawable.sl4,R.drawable.sl5};
        adapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(adapter);

        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP);
        sliderView.startAutoCycle();

        map = view.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        return view;

    }

    private void openMap() {
        Uri uri = Uri.parse("geo:0, 0?q=National Institute of Technology Agartala");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }
}