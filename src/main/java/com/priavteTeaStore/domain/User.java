package com.priavteTeaStore.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.priavteTeaStore.domain.inter.Present;
import com.priavteTeaStore.domain.inter.PresentOwner;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Entity
public class User implements PresentOwner {
    @Id
    @GeneratedValue
    Long userId;

    String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner",fetch = FetchType.LAZY)
    List<TeaStoreCard> teaStoreCardList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner",fetch = FetchType.LAZY)
    List<TeaProduct> teaProductList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    List<TeaStoreCard> historyTeaStoreCardList;

    String md5_key;
    String password;

    public User() {
    }

    public User(String md5_key, String password, String phone_num) {
        this.md5_key = md5_key;
        this.password = password;
        this.name = phone_num;
    }

    public String getName() {
        return name;
    }

    @Override
    public Long getId() {
        return userId;
    }


    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && (obj instanceof User) && ((User) obj).getId().equals(getId());
    }

    @Override
    public void removeFromPrivateGoodList(Present present) {
        if (present instanceof TeaProduct) {
            removeFromList(present, TeaProduct.class, teaProductList);
        } else {
            removeFromList(present, TeaStoreCard.class, teaStoreCardList);
            addToHistory((TeaStoreCard) present);
        }
    }

    @Override
    public void addToPrivateGoodList(Present present) {
        if (present instanceof TeaProduct) {
            addToList(present, TeaProduct.class, teaProductList);
        } else {
            addToList(present, TeaStoreCard.class, teaStoreCardList);
        }
    }


    private void addToList(Object obj, Class type, List list) {
        list.add(type.cast(obj));
    }

    //使用java8的新特性
    private void removeFromList(Object obj, Class type, List list) {
        Predicate<?> pre = item -> item.equals(type.cast(obj));
        list.removeIf(pre);
    }

    /**
     * 将庄主卡放到历史列表中
     *
     * @param card
     */
    public void addToHistory(TeaStoreCard card) {
        historyTeaStoreCardList.add(card);
    }

    public List<TeaStoreCard> getTeaStoreCardList() {
        return teaStoreCardList;
    }

    public void activeTeaStoreCard(Long teaStoreCardId) {
        for (TeaStoreCard card : teaStoreCardList) {
            if (card.getId().equals(teaStoreCardId)) {
                card.active();
            }
        }
    }
}
