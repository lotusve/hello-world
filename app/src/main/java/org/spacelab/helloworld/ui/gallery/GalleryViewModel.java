package org.spacelab.helloworld.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.spacelab.helloworld.data.source.DataRepository;
import org.spacelab.helloworld.data.source.DataSource;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    private DataRepository dataRepository;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");

        dataRepository = DataRepository.getInstance();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void getData(){

        /*dataRepository.getData(new DataSource.GetDataCallback() {
            @Override
            public void onDataLoaded() {

            }

            @Override
            public void onDataNotAvailable() {

            }
        });*/

        dataRepository.getData();

    }
}