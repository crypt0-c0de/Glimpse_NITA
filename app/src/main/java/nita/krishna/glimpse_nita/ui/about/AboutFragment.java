package nita.krishna.glimpse_nita.ui.about;


import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import nita.krishna.glimpse_nita.R;

public class AboutFragment extends Fragment {

    private RecyclerView pdfRecyclerView;
    private List<File> pdfList;
    private DocAdapter adapter;
    private ProgressBar pdfProgressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        pdfRecyclerView = view.findViewById(R.id.pdfRecyclerView);
        pdfProgressBar = view.findViewById(R.id.pdfProgressBar);


        pdfRecyclerView.setHasFixedSize(true);
        pdfRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pdfList = new ArrayList<>();


        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                pdfList.addAll(findPdf(Environment.getExternalStorageDirectory()));


                //Post Execute
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new DocAdapter(getContext() , pdfList);
                        pdfRecyclerView.setAdapter(adapter);
                        pdfProgressBar.setVisibility(View.GONE);
                    }
                });
            }
        });

        return view;
    }

    public ArrayList<File> findPdf(File file) {

        ArrayList<File> arrayList = new ArrayList<>();

        File[] files = file.listFiles();

        for(File singleFile : files) {
            if(singleFile.isDirectory() && !singleFile.isHidden()) {
                arrayList.addAll(findPdf(singleFile));
            }else {
                if(singleFile.getName().endsWith(".pdf")) {
                    arrayList.add(singleFile);
                }
            }
        }

        return arrayList;

    }

}