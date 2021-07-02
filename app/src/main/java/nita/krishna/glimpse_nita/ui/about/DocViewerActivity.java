package nita.krishna.glimpse_nita.ui.about;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

import nita.krishna.glimpse_nita.R;


public class DocViewerActivity extends AppCompatActivity {

    private String filePath ="";
    private PDFView docView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_viewer);

        docView = findViewById(R.id.docView);

        filePath = getIntent().getStringExtra("pdfPath");
        File file = new File(filePath);
        docView.fromUri(Uri.fromFile(file)).load();

    }
}