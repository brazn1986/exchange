package com.service.exchange.controller;

import com.service.exchange.dto.ConvertCurrencyResponseDto;
import com.service.exchange.dto.ConvertCurrencyRequestDto;
import com.service.exchange.model.ConvertCurrencyInfo;
import com.service.exchange.model.CurrencyPair;
import com.service.exchange.service.ConvertCurrencyService;
import com.service.exchange.service.validator.CurrencyNameValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import static com.service.exchange.utils.CurrencyNameUtil.*;


@RestController
@RequestMapping("/convert")
public class CurrencyPriceInfoController {

    @Autowired
    private ConvertCurrencyService convertService;

    @Autowired
    private CurrencyNameValidator currencyNameValidator;

    @PostMapping
    public @ResponseBody ConvertCurrencyResponseDto convert(@RequestBody @Valid ConvertCurrencyRequestDto convertionRequestDto) {

        currencyNameValidator.validate(convertionRequestDto.getCurrencyIn(), convertionRequestDto.getCurrencyOut());

        CurrencyPair currencyPair = getCurrencyPair(convertionRequestDto.getCurrencyIn(), convertionRequestDto.getCurrencyOut());
        ConvertCurrencyInfo info = convertService.convert(convertionRequestDto.getUserId(), currencyPair, convertionRequestDto.getAmount());

        ConvertCurrencyResponseDto responseDto = new ConvertCurrencyResponseDto();
        responseDto.setOutAmount(info.getOutAmount().toString());
        responseDto.setCurrencyOut(convertionRequestDto.getCurrencyOut());
        responseDto.setCurrencyIn(convertionRequestDto.getCurrencyIn());
        responseDto.setPrice(info.getPrice().toString());
        responseDto.setInAmount(info.getInAmount().toString());
        return responseDto;
    }


}
