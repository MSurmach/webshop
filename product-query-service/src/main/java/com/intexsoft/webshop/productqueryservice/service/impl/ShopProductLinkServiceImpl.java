package com.intexsoft.webshop.productqueryservice.service.impl;

import com.intexsoft.webshop.messagecommon.event.shop.impl.ShopProductLinkCreatedEvent;
import com.intexsoft.webshop.productqueryservice.mapper.ShopEventMapper;
import com.intexsoft.webshop.productqueryservice.model.ShopProductLink;
import com.intexsoft.webshop.productqueryservice.repository.ShopProductLinkRepository;
import com.intexsoft.webshop.productqueryservice.service.ShopProductLinkService;
import com.intexsoft.webshop.productqueryservice.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopProductLinkServiceImpl implements ShopProductLinkService {
    private final ShopProductLinkRepository shopProductLinkRepository;
    private final ShopEventMapper shopEventMapper;

    @Override
    public void saveShopProductLinkFromShopProductLinkCreatedEvent(ShopProductLinkCreatedEvent shopProductLinkCreatedEvent) {
        ShopProductLink shopProductLinkFromEvent = shopEventMapper.toShopProductLink(shopProductLinkCreatedEvent);
        log.info("IN: trying to save new link between shop and product = {} from {}",
                JsonUtils.getAsString(shopProductLinkFromEvent), shopProductLinkCreatedEvent.getClass().getSimpleName());
        ShopProductLink savedShopProductLink = shopProductLinkRepository.save(shopProductLinkFromEvent);
        log.info("OUT: link between shop with id = {} and product with id = {} saved successfully",
                savedShopProductLink.getShopId(), savedShopProductLink.getProductId());
    }
}