package delivery.food.daggerbutterknife.ui.rx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import delivery.food.daggerbutterknife.R;
import delivery.food.daggerbutterknife.ui.adapter.StockDataAdapter;
import delivery.food.daggerbutterknife.ui.item.HelloItem;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class RxActivity extends AppCompatActivity {

    @BindView(R.id.rvRx)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        ButterKnife.bind(this);

        LinearLayoutManager manager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        StockDataAdapter adapter = new StockDataAdapter();
        recyclerView.setAdapter(adapter);



        Observable.just(new HelloItem("Ratanak", "A"), new HelloItem("Baby", "B"), new HelloItem("Cathy", "C"))
                .observeOn(Schedulers.io())
                .subscribe(x->{ adapter.add(x.getName());});

    }
}
