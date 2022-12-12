package com.example.shoppinglist.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppinglist.db.AppDatabase;
import com.example.shoppinglist.db.Category;
import com.example.shoppinglist.db.Items;

import java.util.List;

public class ShowItemListActivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<Items>>  listOfItems;
    private AppDatabase appDatabase;

    public ShowItemListActivityViewModel(@NonNull Application application) {
        super(application);
        listOfItems = new MutableLiveData<>();

        appDatabase = AppDatabase.getDbInstance(getApplication().getApplicationContext());
    }

    public MutableLiveData<List<Items>> getItemsListObserver() {
        return listOfItems;
    }

    public void getAllItemsList(int categoryID) {
        List<Items> itemsList = appDatabase.shoppingListDAO().getAllItemsList(categoryID);

        if (itemsList.size() > 0) {
            listOfItems.postValue(itemsList);
        } else {
            listOfItems.postValue(null);
        }
    }

    public void insertItems(Items items) {
        appDatabase.shoppingListDAO().insertItems(items);
        getAllItemsList(items.categoryId);
    }

    public void updateItems(Items items) {
        appDatabase.shoppingListDAO().updateItems(items);
        getAllItemsList(items.categoryId);
    }

    public void deleteItems(Items items) {
        appDatabase.shoppingListDAO().deleteItems(items);
        getAllItemsList(items.categoryId);
    }
}
