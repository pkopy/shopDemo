package pl.pkopy.shopDemo.models.services;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.pkopy.shopDemo.models.BarcodeEntity;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BasketService {
    private List<BarcodeEntity> barcodeEntityList;

    public BasketService() {
        barcodeEntityList = new ArrayList<>();
    }

    public List<BarcodeEntity> getBarcodeEntityList() {
        return barcodeEntityList;
    }

    public void setBarcodeEntityList(List<BarcodeEntity> barcodeEntityList) {
        this.barcodeEntityList = barcodeEntityList;
    }

    public int basketLength() {
        return barcodeEntityList.size();
    }

    public void addProductToBasket(BarcodeEntity barcodeEntity){
        barcodeEntityList.add(barcodeEntity);
    }

    public void removeProductFromBasket(int id){
//        for(BarcodeEntity entity : barcodeEntityList){
//            if(entity.getId() == id) {
//                System.out.println(entity.getId());
//                barcodeEntityList.remove(entity);
//            }
//        }
        for(int i = 0; i < barcodeEntityList.size(); i++){
            if(barcodeEntityList.get(i).getId() == id){
                barcodeEntityList.remove(i);
            }
        }
    }

    public boolean isInBasket(int id){
        return barcodeEntityList.stream().anyMatch(s -> s.getId() == id);
    }
}
