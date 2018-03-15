package delivery.food.daggerbutterknife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.AndroidInjection;
import delivery.food.daggerbutterknife.data.model.MessageEvent;
import delivery.food.daggerbutterknife.data.model.rx.RxBus;

public class MainActivity extends AppCompatActivity {

    @Inject
    @Named("hi")
    String hi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Toast.makeText(this, "Say : " + hi, Toast.LENGTH_SHORT).show();

        findViewById(R.id.btnSendData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RxBus.getBehaviorSubject().onNext(new MessageEvent("I love you BB!"));
                startActivity(new Intent(MainActivity.this, EventBusActivity.class));

            }
        });


        findViewById(R.id.goNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EventBusActivity.class));
            }
        });
    }


}
