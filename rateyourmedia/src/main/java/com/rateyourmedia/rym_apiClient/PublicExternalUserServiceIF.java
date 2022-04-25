package com.rateyourmedia.rym_apiClient;

import amin.aoulkadi.mychat.dtos.AccountDTO;

public interface PublicExternalUserServiceIF {

    AccountDTO registerExternalUser(String apiKey, String screenName);
}
