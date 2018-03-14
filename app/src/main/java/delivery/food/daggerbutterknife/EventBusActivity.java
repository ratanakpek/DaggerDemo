package delivery.food.daggerbutterknife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import delivery.food.daggerbutterknife.data.model.MessageEvent;
import delivery.food.daggerbutterknife.data.model.rx.RxBus;
import io.reactivex.observers.DisposableObserver;

public class EventBusActivity extends AppCompatActivity {
    TextView tvResult;
    private DisposableObserver<MessageEvent> disposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);

        tvResult = findViewById(R.id.tvResult);

        disposable = RxBus.getBehaviorSubject().
                subscribeWith(new DisposableObserver<MessageEvent>() {


                    @Override
                    public void onNext(MessageEvent o) {
                        if (o instanceof MessageEvent) {
                            Log.d("tag", o.getMessage());
                            tvResult.setText(o.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(disposable!=null){
            disposable.dispose();
        }
    }
}
