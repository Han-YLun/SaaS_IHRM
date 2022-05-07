package com.ihrm.social.service;

import com.ihrm.domain.social_security.CityPaymentItem;
import com.ihrm.social.dao.CityPaymentItemDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PaymentItemService {
	
    @Resource
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
