package com.ihrm.social.service;

import com.ihrm.domain.social_security.CityPaymentItem;
import com.ihrm.domain.social_security.PaymentItem;
import com.ihrm.social.dao.CityPaymentItemDao;
import com.ihrm.social.dao.PaymentItemDao;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentItemService {
	
    @Autowired
    private PaymentItemDao paymentItemDao;
	
    @Autowired
    private CityPaymentItemDao cityPaymentItemDao;

    /**
     * 根据城市id获取参保项目
     * @param id    城市id
     * @return  城市对应获取参保项目
     */
    public List<CityPaymentItem> findAllByCityId(String id) {
        return cityPaymentItemDao.findAllByCityId(id);
    }
}
