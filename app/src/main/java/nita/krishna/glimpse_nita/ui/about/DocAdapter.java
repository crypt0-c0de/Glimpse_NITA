package nita.krishna.glimpse_nita.ui.about;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

import nita.krishna.glimpse_nita.R;

public class DocAdapter extends RecyclerView.Adapter<DocAdapter.PdfViewHolder> {

    private Context context;
    private List<File> pdfFiles;

    public DocAdapter(Context context, List<File> list) {
        this.context = context;
        this.pdfFiles = list;
    }


    @NonNull
    @NotNull
    @Override
    public PdfViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pdf_item_layout, parent , false);

        return new PdfViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DocAdapter.PdfViewHolder holder, int position) {

    holder.pdfName.setText(pdfFiles.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DocViewerActivity.class);
                intent.putExtra("pdfPath", pdfFiles.get(position).getAbsolutePath());
                context.startActivity(intent);


//                File file = new File(pdfFiles.get(position).getAbsolutePath());
//                Intent intent=new Intent(Intent.ACTION_VIEW);
//                Uri uri = Uri.fromFile(file);
//                intent.setDataAndType(uri, "application/pdf");
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                context.startActivity(intent);

//                Toast.makeText(context, "Selected", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return pdfFiles.size();
    }

    public class PdfViewHolder extends RecyclerView.ViewHolder {

        private TextView pdfName;
        private CardView pdfCardView;

        public PdfViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            pdfName = itemView.findViewById(R.id.pdfName);
            pdfCardView = itemView.findViewById(R.id.pdfCardView);
        }
    }
}
