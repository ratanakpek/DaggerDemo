package delivery.food.daggerbutterknife.ui.rx.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import delivery.food.daggerbutterknife.R;

public class StockDataAdapter extends RecyclerView.Adapter<StockDataAdapter.StockViewHolder> {

    private final List<String> data  = new ArrayList<>();


    @NonNull
    @Override
    public StockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_rx_item, parent, false);
        StockViewHolder holder = new StockViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StockViewHolder holder, int position) {
        holder.stock.setText(data.get(position));
    }

    public void add(String stock){
        this.data.add(stock);
        notifyItemInserted(this.data.size()-1);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class StockViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvResultTest)
        TextView stock;

        public StockViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
