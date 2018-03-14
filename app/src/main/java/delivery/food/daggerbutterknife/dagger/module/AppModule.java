package delivery.food.daggerbutterknife.dagger.module;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    public String provideString(){
        return "Hello World";
    }

}
