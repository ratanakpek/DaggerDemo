package delivery.food.daggerbutterknife;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import delivery.food.daggerbutterknife.utils.adapter.PaginationAdapter;
import delivery.food.daggerbutterknife.utils.rx.RxBus;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.processors.PublishProcessor;

public class PaginationActivity extends AppCompatActivity {

    RecyclerView _pagingList;

    ProgressBar _progressBar;

    private PaginationAdapter _adapter;
    private RxBus _rxBus;

    private CompositeDisposable _disposables;
    private PublishProcessor<Integer> _paginator;

    private boolean _requestUnderWay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagination);

        _pagingList = findViewById(R.id.list_paging);
        _progressBar = findViewById(R.id.progress_paging);

        _rxBus = getRxBusSingleton();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _pagingList.setLayoutManager(layoutManager);

        _adapter = new PaginationAdapter(_rxBus);
        _pagingList.setAdapter(_adapter);

        _paginator = PublishProcessor.create();


        _disposables = new CompositeDisposable();

        Disposable d2 =
                _paginator
                        .onBackpressureDrop()
                        .doOnNext(
                                i -> {
                                    _requestUnderWay = true;
                                    _progressBar.setVisibility(View.VISIBLE);
                                })
                        .concatMap(this::_itemsFromNetworkCall)
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(
                                items -> {
                                    _adapter.addItems(items);
                                    _adapter.notifyDataSetChanged();

                                    return items;
                                })
                        .doOnNext(
                                i -> {
                                    _requestUnderWay = false;
                                    _progressBar.setVisibility(View.INVISIBLE);
                                })
                        .subscribe();

        // I'm using an RxBus purely to hear from a nested button click
        // we don't really need Rx for this part. it's just easy ¯\_(ツ)_/¯

        Disposable d1 =
                _rxBus.asFlowable()
                        .filter(o -> !_requestUnderWay)
                        .subscribe(
                                event -> {
                                    if (event instanceof PaginationAdapter.PageEvent) {

                                        // trigger the paginator for the next event
                                        int nextPage = _adapter.getItemCount();
                                        _paginator.onNext(nextPage);
                                    }
                                });

        _disposables.add(d1);
        _disposables.add(d2);

        _paginator.onNext(0);

    }

    public RxBus getRxBusSingleton() {
        if (_rxBus == null) {
            _rxBus = new RxBus();
        }

        return _rxBus;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onStop() {
        super.onStop();
        _disposables.clear();
    }

    /**
     * Fake Observable that simulates a network call and then sends down a list of items
     */
    private Flowable<List<String>> _itemsFromNetworkCall(int pageStart) {
        return Flowable.just(true)
                .observeOn(AndroidSchedulers.mainThread())
                .delay(2, TimeUnit.SECONDS)
                .map(
                        dummy -> {
                            List<String> items = new ArrayList<>();
                            for (int i = 0; i < 10; i++) {
                                items.add("Item " + (pageStart + i));
                            }
                            return items;
                        });
    }
}
