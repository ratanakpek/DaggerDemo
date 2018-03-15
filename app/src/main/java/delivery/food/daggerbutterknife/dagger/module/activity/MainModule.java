package delivery.food.daggerbutterknife.dagger.module.activity;


import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    @Named("hi")
    public String provideHi(){
        return "Hi";
    }
}
