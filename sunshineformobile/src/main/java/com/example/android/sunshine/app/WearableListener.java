package com.example.android.sunshine.app;

import android.util.Log;

import com.example.android.sunshine.app.sync.SunshineSyncAdapter;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.WearableListenerService;

/**
 * Created by sorengoard on 12/12/2016.
 */

public class WearableListener extends WearableListenerService {
    private static final String LOG_TAG = WearableListener.class.getSimpleName().toString();

    private static final String WEARABLE_DATA_PATH = "/weather_data";

    @Override
    public void onDataChanged(DataEventBuffer buffer){
        for (DataEvent event : buffer) {
            if (event.getType() == DataEvent.TYPE_CHANGED) {
                String path = event.getDataItem().getUri().getPath();
                Log.v(LOG_TAG, path);
                if (path.equals(WEARABLE_DATA_PATH)) {
                    SunshineSyncAdapter.syncImmediately(this);
                }
            }
        }
    }
}
