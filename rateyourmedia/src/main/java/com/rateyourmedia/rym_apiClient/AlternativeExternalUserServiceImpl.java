package com.rateyourmedia.rym_apiClient;

import amin.aoulkadi.mychat.dtos.AccountDTO;
import org.springframework.stereotype.Service;

@Service
public class AlternativeExternalUserServiceImpl implements PublicExternalUserServiceIF{
    @Override
    public AccountDTO registerExternalUser(String apiKey, String screenName) {
        System.out.println("No Registration at MyChat possible. Connection could not be established.");
        return null;
    }
}
