package com.bakcell.nomre.factory;

import com.bakcell.nomre.enums.PhoneNumberQueryType;
import com.bakcell.nomre.model.response.PhoneNumberResponse;
import com.bakcell.nomre.util.RepositoryMethodSelector;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

/**
 * @author Javidan Alizada
 */
@Component
@RequiredArgsConstructor
@Getter
public class PhoneNumberResponseFactory {

    private final RepositoryMethodSelector repositoryMethodSelector;
    private String msisdn;
    private String prefix;
    private String categoryName;

    public PhoneNumberResponseFactory msisdn(String msisdn) {
        this.msisdn = msisdn;
        return this;
    }

    public PhoneNumberResponseFactory prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public PhoneNumberResponseFactory categoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    private Map<List<Boolean>, PhoneNumberQueryType> getPhoneNumberQueryTypeMap() {
        return Map.of(
                List.of(true, true, true), PhoneNumberQueryType.PREFIX_MSISDN_CATEGORY,
                List.of(true, true, false), PhoneNumberQueryType.PREFIX_MSISDN,
                List.of(true, false, true), PhoneNumberQueryType.PREFIX_CATEGORY,
                List.of(true, false, false), PhoneNumberQueryType.PREFIX,
                List.of(false, true, true), PhoneNumberQueryType.MSISDN_CATEGORY,
                List.of(false, false, true), PhoneNumberQueryType.CATEGORY,
                List.of(false, true, false), PhoneNumberQueryType.MSISDN
        );
    }


    private PhoneNumberQueryType getPhoneNumberQueryType(List<Boolean> methodCheckerKeys) {
        Map<List<Boolean>, PhoneNumberQueryType> phoneNumberQueryTypeMap = this.getPhoneNumberQueryTypeMap();
        return phoneNumberQueryTypeMap
                .entrySet()
                .stream()
                .filter(listListEntry -> listListEntry.getKey().equals(methodCheckerKeys))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }

    public PhoneNumberResponse getPhoneNumberResponse() {
        PhoneNumberQueryType type = this.getPhoneNumberQueryType(this.getNullableControlsOfFields());
        switch (type) {
            case MSISDN:
                return this.repositoryMethodSelector.byMsisdn(this.getMsisdn());
            case PREFIX:
                return this.repositoryMethodSelector.byPrefix(this.getPrefix());
            case CATEGORY:
                return this.repositoryMethodSelector.byCategory(this.getCategoryName());
            case PREFIX_MSISDN:
                return this.repositoryMethodSelector.byPrefixAndMsisdn(this.getPrefix(), this.getMsisdn());
            case MSISDN_CATEGORY:
                return this.repositoryMethodSelector.byMsisdnAndCategory(this.getMsisdn(), this.getCategoryName());
            case PREFIX_CATEGORY:
                return this.repositoryMethodSelector.byPrefixAndCategory(this.getPrefix(), this.getCategoryName());
            case PREFIX_MSISDN_CATEGORY:
                return this.repositoryMethodSelector.byPrefixAndMsisdnAndCategoryName(this.getPrefix(), this.getMsisdn(), this.getCategoryName());
            default:
                return PhoneNumberResponse.builder().build();
        }
    }

    private List<Boolean> getNullableControlsOfFields() {
        return List.of(nonNull(this.prefix), nonNull(this.msisdn), nonNull(this.categoryName));
    }
}
