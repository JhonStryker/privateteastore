package com.priavteTeaStore.service;

import com.priavteTeaStore.domain.PresentOrder;
import com.priavteTeaStore.domain.ProductType;
import com.priavteTeaStore.domain.TeaStoreCard;
import com.priavteTeaStore.domain.User;
import com.priavteTeaStore.exception.ToClientException;
import com.priavteTeaStore.repository.TeaStoreCardRepository;
import com.priavteTeaStore.util.ERR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Service
@Transactional
public class TeaStoreCardService {

    @Autowired
    PresentOrderService presentOrderService;

    @Autowired
    TeaStoreCardRepository teaStoreCardRepository;

    /**
     * 激活茶庄卡
     * @param teaStoreCardId
     */
    public void activeTeaStoreCard(User user, Long teaStoreCardId){
        user.activeTeaStoreCard(teaStoreCardId);
    }

    /**
     * 赠送茶庄卡
     */
    public PresentOrder presentTeaStoreCard(Long teaStoreCardId, User user){
        return presentOrderService.sendPresent(getTeaStoreCard(teaStoreCardId), user, ProductType.TeaStoreCard);
    }

    /**
     * 获取茶庄卡
     * @param teaStoreCardId
     * @return
     */
    private TeaStoreCard getTeaStoreCard(Long teaStoreCardId) {
        TeaStoreCard card = teaStoreCardRepository.getOne(teaStoreCardId);
        if (card == null){
            throw new ToClientException(ERR.no_such_card);
        }
        return card;
    }

}
