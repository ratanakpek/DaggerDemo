package delivery.food.daggerbutterknife.utils.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import delivery.food.daggerbutterknife.R;
import delivery.food.daggerbutterknife.utils.rx.RxBus;

public class PaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_LOG = 0;

    private final List<String> _items = new ArrayList<>();
    private final RxBus _bus;


    public PaginationAdapter(RxBus bus) {
        _bus = bus;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ItemLogViewHolder.create(parent);
    }

    @Override
    public int getItemViewType(int position) {
        return ITEM_LOG;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ItemLogViewHolder) holder).bindContent(_items.get(position));
        boolean lastPositionReached = position == _items.size() - 1;
        if (lastPositionReached) {
            _bus.send(new PageEvent());
        }
    }

    @Override
    public int getItemCount() {
        return  _items.size();
    }

    public void addItems(List<String> items) {
        _items.addAll(items);
    }


    private static class ItemLogViewHolder extends RecyclerView.ViewHolder {
        ItemLogViewHolder(View itemView) {
            super(itemView);
        }

        static ItemLogViewHolder create(ViewGroup parent) {
            return new ItemLogViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_log, parent, false));
        }

        void bindContent(String content) {
            ((TextView) itemView).setText(content);
        }
    }

    public static class PageEvent {}
}
