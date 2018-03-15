package delivery.food.daggerbutterknife.dagger.module.activity;


import android.content.Intent;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    public Intent provideIntent(){
        return new Intent();
    }
}
