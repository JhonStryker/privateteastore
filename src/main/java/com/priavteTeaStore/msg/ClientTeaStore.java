package com.priavteTeaStore.msg;

import com.priavteTeaStore.domain.TeaStoreCardProduct;
import com.priavteTeaStore.domain.TeaStoreInfo;

/**
 * Created by Thoughtworks on 16/6/14.
 */
public class ClientTeaStore {

    TeaStoreInfo teaStoreInfo;

    public ClientTeaStore() {
    }

    public ClientTeaStore(TeaStoreCardProduct teaStoreCardProduct) {
        this.teaStoreInfo = teaStoreCardProduct.getTeaStore();
    }

    public static ClientTeaStore convert(TeaStoreCardProduct teaStoreCardProduct) {
        return new ClientTeaStore(teaStoreCardProduct);
    }

    public TeaStoreInfo getTeaStoreInfo() {
        return teaStoreInfo;
    }
}
