package com.store.core.activities;

import com.store.core.exceptions.NotEnoughDataException;
import com.store.core.model.Price;
import com.store.infrastructure.output.exceptions.ErrorCode;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class CheckIfDataIsValidToCalculateProductInflationActivity {

    public void execute(List<Price> priceList) {
        if (priceList.isEmpty() || priceList.size() == 1) {
            throw new NotEnoughDataException("There is no enough data of prices to compare", ErrorCode.COD01.name());
        }

        final Price firstPriceModel = priceList.get(0);
        final Price lastPriceModel = priceList.get(priceList.size() - 1);

        if (firstPriceModel.getCreationDate().getMonthValue() == lastPriceModel.getCreationDate().getMonthValue()) {
            throw new NotEnoughDataException("There is no enough data of prices to compare, it's necessary at least 2 months to compare the inflation", ErrorCode.COD02.name());
        }
    }
}
