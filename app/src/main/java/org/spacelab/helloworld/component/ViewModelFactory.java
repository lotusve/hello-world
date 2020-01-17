package org.spacelab.helloworld.component;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.spacelab.helloworld.data.source.DataRepository;
import org.spacelab.helloworld.ui.gallery.GalleryViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelFactory instance;

    private final DataRepository mDataRepository;

    private ViewModelFactory(DataRepository dataRepository) {
        mDataRepository = dataRepository;
    }

    public static ViewModelFactory getInstance(Context context) {
        if (instance == null) {
            synchronized (ViewModelFactory.class) {
                if (instance == null) {
                    instance = new ViewModelFactory(Injection.proviceDataRepository(context.getApplicationContext()));
                }
            }
        }
        return instance;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GalleryViewModel.class)) {
            return (T) new GalleryViewModel(mDataRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
