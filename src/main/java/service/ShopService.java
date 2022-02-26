package service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.RecordNotFoundException;
import model.Shop;
import repository.ShopRepository;

@Service
public class ShopService {

    @Autowired
    private ShopRepository repository;

    /*
     * TODO: Get the List of Shops
     */
    public List<Shop> getAllShops(){
        List<Shop> list =  (List<Shop>)repository.findAll();
        return list;
    }
    /*
     * TODO: Get Shop by Id.
     */
    public Shop getShopById(Integer id) throws RecordNotFoundException {

        Optional<Shop> shop = repository.findById(id);
        if(shop!=null) {
            return shop.get();
        }
        else
        {
            throw new RecordNotFoundException("Not found");
        }
    }
    /*
     * TODO: Save into db
     */
    public Shop saveOrUpdateShop(Shop shop) {
        if(shop.getId() == null) {
            return repository.save(shop);}
        else {
            Optional<Shop> sOptional = repository.findById(shop.getId());
            if(sOptional!=null) {
                Shop shop2 = sOptional.get();
                shop2.setOwnerName(shop.getOwnerName());
                shop2.setAddress(shop.getAddress());
                shop2.setShopType(shop.getShopType());
                shop2.setCountry(shop.getCountry());
                shop2.setShopNo(shop.getShopNo());
                shop2 = repository.save(shop2);
                return shop2;
            }
            else {
                shop = repository.save(shop);
                return shop;

            }
        }
    }
    public void deleteShop(Integer id) {
        repository.deleteById(id);
    }
}
